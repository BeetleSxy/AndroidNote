# Handler 使用

这篇文章是自己用来对所学知识的总结和回忆,并完善自己的知识体系:

# 创建

Handler 的创建只需要 `new Handler()` 就可以创建一个 Handler
,这里需要明确的是,Handler 只用来接受和发送操作信息,而具体的发送方式可参看我另外一篇 [Handler机制]() ;

### 方法一:

```java
Handler mHandler = new Handler(){
    public void handleMessage(Message msg) {  
          //TODO 修改 UI 逻辑
    };  
};
```

### 方法二:

```java
Handler mHandler = new Handler(new Handler.Callback() {  
    @Override  
    public boolean handleMessage(Message msg) {  
        return false;  
    }  
});
```

### 方法三:

```java
Handler MHandler = new Handler();//只适用于 Post 方式用来发送消息;
```

其中 `handleMessage(Message msg)` 方法是用来接收由子线程发来的 Message 信息,具体会在接收时说;

# 发送

在 Handler 中发送主要有两类方法: postXXX 和 eandXXX 方法

eandXXX() 方法来发送 Message 到消息队列中，这一过程中会自动将业务逻辑和 Message 绑定在一起。

postXXX() 方法来发送使用 Runnable 来实现业务逻辑 不需要去获取任何 Message 对象,该方法会自动将 Runnable 包装成 Message 。
## PostXXX()

使用 post 可以使用第三种创建方式;使用 `Handler.Post(Runnable r);` 进行发送信息,会在在主线程中调用传递进去的 Runnable 方法  

#### Handler.post() 和 View.post()方法的区边

View.post 其实内部是获取到了 view 所在线程（即 ui 线程）的 handler，并且调用了 handler 的 post 方法

方法|使用
-|-
Handler.post(Runnable r)|发送一个 Runnable 到消息队列中，该 Runnable 会被封装到 Message 对象中。
postAtTime(Runnable r, long uptimeMillis)|发送一个指定处理时间点的 Runnable 到消息队列中，该 Runnable 会被封装到 Message 对象中。
postAtTime(Runnable r, Object token, long uptimeMillis)|同上，唯一的区别就是多了个token参数，该参数最后赋值给了Message.obj
postDelayed(Runnable r, long delayMillis)|发送一个指定延时处理的 Runnable 到消息队列中，该 Runnable 会被封装到 Message 对象中。
postAtFrontOfQueue(Runnable r)|发送一个 Runnable 到消息队列的最前端，该 Runnable 会被封装到 Message 对象中。

## eandXXX()

使用 eandXXX() 方法时需要注意,必须使用方法一或者方法二来创建 Handler 因为封装的 Message 信息最后会传递到  handler.sendEmptyMessage 或者 Handler.Callback 中的 handleMessage 方法

方法|使用
-|-
sendMessage(Message msg)|发送一个指定的消息到消息队列的尾部。
sendEmptyMessage(int what)|发送一个指定 what 属性的空消息，如果你发送的消息不需要带任何的数据，那你可以调用该方法发送一个只有 what 属性的 Message。
sendEmptyMessageDelayed(int what, long delayMillis)|发送一个指定 what 属性且延迟处理的空消息。
sendEmptyMessageAtTime(int what, long uptimeMillis)|发送一个指定 what 属性和处理时间点的空消息。
sendMessageDelayed(Message msg, long delayMillis)|发送一个指定延时处理的消息到消息队列中。
sendMessageAtTime(Message msg, long uptimeMillis)|发送一个指定处理时间点的消息到消息队列中。
sendMessageAtFrontOfQueue(Message msg)|发送一个消息到消息队列的最前端，该消息会在下一次循环的时候被处理。

# 接收

这里需要知道消息的回调会被封装为 Message 对象,它负责保存我们的业务逻辑和相关的数据，并在消息队列中等待被处理;

> **注意**
正确获取Message对象的方法是通过 Handler.obtainMessage(...) 方法从消息池中申请一个空的 Message 对象,并不是去 new 一个新的对象, Handler 会委会一个消息池,并会维护这个消息池会将消息池中没用的 Message 对象回收.如果是去 new Message 这样如果创建很多的 Message 对象,而 GC 并不能每次在 Message 没用的时候及时回收它,这样会造成资源的浪费.

我们在看一看 Message 携带的数据:

* `what`
该属性用于标识不同的 Message 对象，因为在消息队列中经常会同时存在多个 Message，在提取并处理消息的时候我们必须有个标识来识别不同的 Message，以便做不同的业务操作。

* `arg1`和`arg2`
这里两个属性可以用于存储简单的整形数据;

* `obj`
该属性用于存储一个对象，你可以用它来保存任何的对象，例如一个 Java Bean。

* `public void setData(Bundle data)`
如果你要存储的数据比较复杂，则你可以考虑用Bundle来保存数据。

方法|使用
-|-
public final Message obtainMessage()|从消息池中获取一个空的 Message 对象。
public final Message obtainMessage(int what)|从消息池中获取一个指定了 what 属性的 Message 对象。
public final Message obtainMessage(int what, Object obj)|从消息池中获取一个指定了 what 和 obj 属性的 Message 对象。
public final Message obtainMessage(int what, int arg1, int arg2)|从消息池中获取一个指定了 what、arg1 和 arg2 属性的 Message 对象。
public final Message obtainMessage(int what, int arg1, int arg2, Object obj)|从消息池中获取一个指定了 what、arg1、arg2 和 obj 属性的 Message 对象。

---

# 删除

说过了如何接受和发送消息,那么我们看看如何去删除在消息队列中的消息

方法|使用
-|-
public final void removeMessages(int what)|从消息队列中移除指定 what 的 Message。
public final void removeMessages(int what, Object object)|从消息队列中移除指定 what 和 obj的Message。
public final void removeCallbacks(Runnable r)|从消息队列中移除指定的 Runnable。
public final void removeCallbacks(Runnable r, Object token)|从消息队列中移除带有指定 token 的 Runnable。
public final void removeCallbacksAndMessages(Object token)|从消息队列中移除带有指定 token（Message.obj）的 Runnable 和 Message，如果 token 为 null，则移除所有的消息。