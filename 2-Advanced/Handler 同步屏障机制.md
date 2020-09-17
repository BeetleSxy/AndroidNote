# 同步屏障机制就是什么？

同步屏障机制就是插入一个同步屏障信息到Looper的队列头部，当Looper调用next获取信息时候，发现队列头部是一个同步屏障信息，就会跳过所有同步消息，寻找为异步的消息执行

# 为什么要这样呢？

用于实时

因为屏幕刷新是每16ms一次

假如队列信息是这样

| a     | b     | 刷新 |
| ----- | ----- | ---- |
| 200ms | 120ms |      |

这样这个刷新信息要经过320ms才能执行，相等于丢了20帧

而用同步屏障，刷新消息会先执行，执行完成后，再执行a和b

刷新消息是由接收到系统的VSYNC信号才刷新的，VSYNC信号是需要手动注册的

常见当我们调用requestLayout或者postFrameCallBack都会请求监听下一次的VSYNC信号

所以app并不是因为每隔16ms都会刷新一次，而是我们主动监听下一次的VSYNC信号，得到系统通知才会刷新

所以在a和b的这段时间内，假如我们没有请求监听，那么a和b会顺序执行

否则会再次进行同步屏障，先执行刷新信息

但是同步屏障的时机是Queue的next方法，所以假如某个耗时方法正在执行的话

会需要等待这个耗时方法执行完成后，才能开启同步屏障

# 如何使用？

方法一：

```java
public Handler(boolean async) {
    this(null, async);
}
```

只要async参数为true，所有的消息都将是异步消息。

```java
public Handler(boolean async);
public Handler(Callback callback, boolean async);
public Handler(Looper looper, Callback callback, boolean async);
```

方法二：

```java
Message msg = mHandler.obtainMessage(...);
msg.setAsynchronous(true);

mHandler.sendToTarget();
```

# 如何办到？

调用`MessageQueue#postSyncBarrier()`方法即可开启同步障碍，因为`MessageQeueu`绑定在`Looper`上，而`Looper`依附在`Handler`上，所以正常情况下，源码中是这样开启同步障碍的：

```java
mHandler.getLooper().getQueue().postSyncBarrier();
```

实现代码：

```java
public int postSyncBarrier() {
    return postSyncBarrier(SystemClock.uptimeMillis());
}


private int postSyncBarrier(long when) {

  final int token = mNextBarrierToken++;
  final Message msg = Message.obtain();

  msg.markInUse();
  msg.when = when;
  msg.arg1 = token;

  Message prev = null;
  Message p = mMessages;

  if (when != 0) {
    while (p != null && p.when <= when) {
      prev = p;
      p = p.next;
    }
  }

  if (prev != null) {
    msg.next = p;
    prev.next = msg;
  } else {
    msg.next = p;
    mMessages = msg;
  }
  return token;
}
```

可以看出，在实例化`Message`对象的时候并没有设置它的`target`成员变量的值，然后随即就根据执行时间把它放到链表的某个位置了，实际上就是链表的开始位置。也就是说，当在消息队列中放入一个`target`为空的`Message`的时候，当前`Handler`的这一套消息机制就开启了同步阻断。

当开启同步障碍后，它是如何生效的呢？我们知道，`Looper#loop()`方法最终还是调用了`MessageQueue#next()`方法来获取队列中的消息，现在我们来看看该方法的代码：

```java
Message next() {
    //...
    int pendingIdleHandlerCount = -1; // -1 only during first iteration
    int nextPollTimeoutMillis = 0;
    for (;;) {
        //...
        synchronized (this) {
            // Try to retrieve the next message.  Return if found.
            final long now = SystemClock.uptimeMillis();
            Message prevMsg = null;
            Message msg = mMessages;
            if (msg != null && msg.target == null) {//碰到同步屏障
                // Stalled by a barrier.  Find the next asynchronous message in the queue.
                // do while循环遍历消息链表
                // 跳出循环时，msg指向离表头最近的一个异步消息
                do {
                    prevMsg = msg;
                    msg = msg.next;
                } while (msg != null && !msg.isAsynchronous());
            }
            if (msg != null) {
                if (now < msg.when) {
                    //...
                } else {
                    // Got a message.
                    mBlocked = false;
                    if (prevMsg != null) {
                        //将msg从消息链表中移除
                        prevMsg.next = msg.next;
                    } else {
                        mMessages = msg.next;
                    }
                    msg.next = null;
                    if (DEBUG) Log.v(TAG, "Returning message: " + msg);
                    msg.markInUse();
                    //返回异步消息
                    return msg;
                }
            } else {
                // No more messages.
                nextPollTimeoutMillis = -1;
            }

            //...
        }

        //...
    }
}
```

可以看到，当设置了同步屏障之后，next函数将会忽略所有的同步消息，返回异步消息。换句话说就是，设置了同步屏障之后，Handler只会处理异步消息。再换句话说，同步屏障为Handler消息机制增加了一种简单的优先级机制，异步消息的优先级要高于同步消息。

