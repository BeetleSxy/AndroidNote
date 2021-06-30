# Handler 同步屏障机制
### 同步屏障是什么

首先们需要知道为什么需要使用同步屏障，字面意思就阻碍队列同步消息的屏障。那么我们为什么需要同步屏障呢？

我们知道消息机制中有一个重要的类 MessageQueue，望文生义就是消息队列的意思，一般的情况下，MessageQueue 对于当前线程是同步的，那么什么是当前线程呢？就是实例化 MessageQueue 的线程，在消息机制这个完整的机制中，MessageQueue 是在 Looper 的构造方法中被实例化。也就是说，MessageQueue 正常情况是同步处理消息的，明白这一点就可以让同步屏障入场了。

#### 发送异步消息的方法

第一种发送异步消息的方式，使用 Handler 包含 async 参数的构造方法，例如下面这个。
```
public Handler(boolean async) {
    this(null, async);
}
```
只要 async 参数为 true，所有的消息都将是异步消息。
```
Handler mHandler = new Handler(true);
...
Message msg = mHandler.obtainMessage(...);
mHandler.sendMessageAtTime(msg, dueTime);
```
第二种方法，显示设置 Message 为异步消息
```
Message msg = mHandler.obtainMessage(...);
msg.setAsynchronous(true);
mHandler.sendToTarget();
```
其实第一种方式也是在我们发送 Message 时，Handler 内部帮我们调用了 `Message#setAsynchronous (boolean)`，且参数传了 **true**，因此这两使用方式了解即可。

我们知道，上面的 Message 的 target 都是非空的，而在 `MessageQueue#next ()` 方法中，target 非空的 Message 都会被正常处理（下面会有相关代码），因此在这个时候同步消息和异步消息并没有什么不同。如果同学们思路没有断的话，应该能想到，此时同步障碍就需要登场了。

#### 开启同步障碍
现在我们要贴一点 framework/base 下的代码，都以 oreo-release 分支为准，因为不同版本 SDK 这些代码所在的为止略有不同。

如果相让异步消息起作用，就得开启同步障碍，同步障碍会阻碍同步消息，只允许通过异步消息，如果队列中没有异步消息，此时的 loop () 方法将被 Linux epoll 机制所阻塞。

开启同步障碍也很简单，调用 MessageQueue#postSyncBarrier () 方法即可，因为 MessageQeueu 绑定在 Looper 上，而 Looper 依附在 Handler 上，所以正常情况下，源码中是这样开启同步障碍的：
```
mHandler.getLooper().getQueue().postSyncBarrier();
```
它的代码也很简单
```
public int postSyncBarrier() {
    return postSyncBarrier(SystemClock.uptimeMillis());
}
```

```
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
可以看出，在实例化 Message 对象的时候并没有设置它的 target 成员变量的值，然后随即就根据执行时间把它放到链表的某个位置了，实际上就是链表的开始位置。也就是说，当在消息队列中放入一个 target 为空的 Message 的时候，当前 Handler 的这一套消息机制就开启了同步阻断。

当开启同步障碍后，它是如何生效的呢？我们知道，`Looper#loop ()` 方法最终还是调用了 `MessageQueue#next ()` 方法来获取队列中的消息，现在我们来看看该方法的代码：
```
for (;;) {
  nativePollOnce(ptr, nextPollTimeoutMillis);

  synchronized (this) {
    final long now = SystemClock.uptimeMillis();
    Message prevMsg = null;
    Message msg = mMessages;

    // 如果当前开启了同步障碍
    if (msg != null && msg.target == null) {
      // 处理同步障碍，获取队列中的下一个异步消息
      do {
        prevMsg = msg;
        msg = msg.next；

        // 如果这个消息是同步的，那么继续向下找异步的
      } while (msg != null && !msg.isAsynchronous());
    }

    if (msg != null) {
      if (now < msg.when) {
        // 如果当前消息的执行时间没到，让它沉睡到下个消息的执行时间
        nextPollTimeoutMillis = msg.when - now;
      } else {
        ...
        return msg; // 执行时间到了，执行它
      }
    } else {
      // 当前没有任何消息需执行，一直沉睡
      nextPollTimeoutMillis = -1;
    }
  }
}
```
可以看出来，当开启了同步障碍时，Looper 在获取下一个要执行的消息时，会在链表中寻找第一个要执行的异步消息，如果没有找到异步消息，就让当前线程沉睡。

> `nativeWake ()` 方法和 `nativePollOnce ()` 方法采用了 Linux 的 epoll 机制，其中 `nativePollOnce ()` 的第二个值，当它是 - 1 时会一直沉睡，直到被主动唤醒为止，当它是 0 时不会沉睡，当它是大于 0 的值时会沉睡传入的值那么多的毫秒时间。epoll 机制实质上是让 CPU 沉睡，来保障当前线程一直在运行而不中断或者卡死，这也是 `Looper#loop ()` 死循环为什么不会导致住县城 ANR 的根本原因。

### 同步障碍的应用
那么同步障碍有什么用呢？在我们日常的应用层开发中极少用到它，读了 framework 的代码后我们会发现，在 ViewRootImpl.java 中有使用它：
```
void scheduleTraversals() {
  if (!mTraversalScheduled) {
    mTraversalScheduled = true;
    // 开启当前Handler的同步屏障
    mTraversalBarrier = mHandler.getLooper().getQueue().postSyncBarrier();
    // 发送一条异步消息
    mChoreographer.postCallback(..., mTraversalRunnable, null);
    if (!mUnbufferedInputDispatch) {
      cheduleConsumeBatchedInput();
    }
    
    notifyRendererOfFramePending();
    pokeDrawLockIfNeeded();
  }
}
```
实际上，这里的 Handler 使用的是主线程的 Looper，因此这里会阻断主线程 Looper 的其他同步消息，在 ViewRootImpl 和 Choreographer 中多次使用到了异步消息，以完成 View 的整个绘制流程。


没错，也许有同学已经被启发了，当我们点击页面的某个控件时，希望瞬间得到它的回应，而不是卡在那里，最起码有个圈圈在转也行。当我们点击某个按钮，此时开启了一个 Activity，如果队列中此时有很多消息在排队等候呢？那么这个 Activity 的测量、布局和绘制就得一直等到所有消息被处理完成才能执行，此时我们会看到页面一直黑着或者一直白着，反正不是我们想要的效果，因此如果这个消息队列有一个优先级的特点，那么不久可以解决这个问题了吗？


综上，所以在消息机制中也很巧妙的融入了优先级特点，这个同步障碍机制，实质上是一个对消息队列的优先级显示。关于 Java/Android 中的优先级任务队列的实践，大家可以多搜索相关资料来学习，这个在日常开发中很有用。

> [Android 消息机制和应用](https://yanzhenjie.blog.csdn.net/article/details/89218745)<br>
> [ Java/Android 中的优先级任务队列的实践](https://yanzhenjie.blog.csdn.net/article/details/71773950)

# Android 的大题架构

- 应用层（System Apps）
  - 系统应用
  - 自己开发的应用
- 应用框架层（Java API Framework）
  - Content Provider
  - View System
  - Activity、Resource 等等平时开发用到的 APIs
- 系统运行库（Native）
  - C/C++ 程序库
    - SQLite
    - SQL
    - ...
  - Android 运行时库
    - ART（5.0以后）
    - 核心库
- 硬件抽象层（HAL）
- Linux 内核层（Linux Kernel）

# View 的事件传递分发机制？
事件的传递流程： `Activity（PhoneWindow）->DecorView->ViewGroup->View。` 事件分发过程中三个重要的方法： `dispatchTouchEvent ()、onInterceptTouchEvent ()、onTouchEvent ()`； 事件传递规则 一般一次点击会有一系列的 MotionEvent，可以简单分为：`down->move->….->move->up`, 当一次 `event` 分发到 `ViewGroup` 时，`ViewGroup` 收到事件后调用 `dispatchTouchEvent`，在 `dispatchTouchEvent` 中先检查是否要拦截，若拦截则 `ViewGroup` 处理事件，否则交给有处理能力的子容器处理。

# 有使用过ContentProvider码？能说说Android为什么要设计ContentProvider这个组件吗？

ContentProvider 应用程序间非常通用的共享数据的一种方式，也是 Android 官方推荐的方式。Android 中许多系统应用都使用该方式实现数据共享，比如通讯录、短信等。但我遇到很多做 Android 开发的人都不怎么使用它，觉得直接读取数据库会更简单方便。

那么 Android 搞一个内容提供者在数据和应用之间，只是为了装高大上，故弄玄虚？我认为其设计用意在于：

封装。对数据进行封装，提供统一的接口，使用者完全不必关心这些数据是在DB，XML、Preferences 或者网络请求来的。当项目需求要改变数据来源时，使用我们的地方完全不需要修改。<br>
提供一种跨进程数据共享的方式。<br>
应用程序间的数据共享还有另外的一个重要话题，就是数据更新通知机制了。因为数据是在多个应用程序中共享的，当其中一个应用程序改变了这些共享数据的时候，它有责任通知其它应用程序，让它们知道共享数据被修改了，这样它们就可以作相应的处理。

ContentResolver 接口的 notifyChange 函数来通知那些注册了监控特定 URI 的 ContentObserver 对象，使得它们可以相应地执行一些处理。ContentObserver 可以通过 registerContentObserver 进行注册。

## 既然是对外提供数据共享，那么如何限制对方的使用呢？

android:exported 属性非常重要。这个属性用于指示该服务是否能够被其他应用程序组件调用或跟它交互。如果设置为 true，则能够被调用或交互，否则不能。设置为 false 时，只有同一个应用程序的组件或带有相同用户 ID 的应用程序才能启动或绑定该服务。

对于需要开放的组件应设置合理的权限，如果只需要对同一个签名的其它应用开放 content provider，则可以设置 signature 级别的权限。大家可以参考一下系统自带应用的代码，自定义了 signature 级别的 permission：

```xml
<permission android:name="com.android.gallery3d.filtershow.permission.READ"
            android:protectionLevel="signature" />

<permission android:name="com.android.gallery3d.filtershow.permission.WRITE"
            android:protectionLevel="signature" />

<provider
    android:name="com.android.gallery3d.filtershow.provider.SharedImageProvider"
    android:authorities="com.android.gallery3d.filtershow.provider.SharedImageProvider"
    android:grantUriPermissions="true"
    android:readPermission="com.android.gallery3d.filtershow.permission.READ"
    android:writePermission="com.android.gallery3d.filtershow.permission.WRITE" />
```

如果我们只需要开放部份的URI给其他的应用访问呢？可以参考 Provider 的 URI 权限设置，只允许访问部份 URI，可以参考原生 ContactsProvider2 的相关代码（注意 path-permission 这个选项）：

```xml
<provider android:name="ContactsProvider2"
    android:authorities="contacts;com.android.contacts"
    android:label="@string/provider_label"
    android:multiprocess="false"
    android:exported="true"
    android:grantUriPermissions="true"
    android:readPermission="android.permission.READ_CONTACTS"
    android:writePermission="android.permission.WRITE_CONTACTS">
    <path-permission
            android:pathPrefix="/search_suggest_query"
            android:readPermission="android.permission.GLOBAL_SEARCH" />
    <path-permission
            android:pathPrefix="/search_suggest_shortcut"
            android:readPermission="android.permission.GLOBAL_SEARCH" />
    <path-permission
            android:pathPattern="/contacts/.*/photo"
            android:readPermission="android.permission.GLOBAL_SEARCH" />
    <grant-uri-permission android:pathPattern=".*" />
</provider>
```

## ContentProvider 接口方法运行在哪个线程中呢？

ContentProvider 可以在 AndroidManifest.xml 中配置一个叫做 android:multiprocess 的属性，默认值是 false，表示 ContentProvider 是单例的，无论哪个客户端应用的访问都将是同一个 ContentProvider 对象，如果设为 true，系统会为每一个访问该 ContentProvider 的进程创建一个实例。

这点还是比较好理解的，那如果我要问每个 ContentProvider 的操作是在哪个线程中运行的呢（其实我们关心的是 UI 线程和工作线程）？比如我们在UI线程调用 `getContentResolver().query` 查询数据，而当数据量很大时（或者需要进行较长时间的计算）会不会阻塞UI线程呢？

要分两种情况回答这个问题：

ContentProvider 和调用者在同一个进程，ContentProvider 的方法（query/insert/update/delete 等）和调用者在同一线程中；
ContentProvider 和调用者在不同的进程，ContentProvider 的方法会运行在它自身所在进程的一个 Binder 线程中。
但是，注意这两种方式在 ContentProvider 的方法没有执行完成前都会 blocked 调用者。所以你应该知道这个上面这个问题的答案了吧。

也可以看看 CursorLoader 这个类的源码，看 Google 自己是怎么使用 `getContentResolver().query` 的。

## ContentProvider 是如何在不同应用程序之间传输数据的？

这个问题点有些深入，大家要对 Binder 进程间通信机制比较了解。因为之前我们有提到过一个应用进程有 16 个 Binder 线程去和远程服务进行交互，而每个线程可占用的缓存空间是 128KB 这样，超过会报异常。ContentResolver 虽然是通过 Binder 进程间通信机制打通了应用程序之间共享数据的通道，但 Content Provider 组件在不同应用程序之间传输数据是基于匿名共享内存机制来实现的。有兴趣的可以查看一下老罗的文章 Android 系统匿名共享内存 Ashmem（Anonymous Shared Memory）简要介绍和学习计划。

# AOT和JIT以及混合编译的区别、优劣
> 参考: 
> * https://source.android.com/devices/tech/dalvik
> * https://www.infoq.com/news/2016/03/android-n-aot-jit 
> * https://player.fm/series/android-developers-backstage-1245114/episode-45-state-of-the-art

## AOT 
即Ahead-of-time,指预先编译.

优点 | 缺点
---|---
在程序运行前编译,可以避免在运行时的编译性能消耗和内存消耗. | 在程序运行前编译会使程序安装的时间增加.
可以在程序运行初期就达到最高性能. | 牺牲Java的一致性.
可以显著的加快程序的启动 | 将提前编译的内容保存会占用更多的外存.

## JIT
即Just-In-Time,指即时编译.

优点 | 缺点
---|---
可以根据当前硬件情况实时编译生成最优机器指令(ps:AOT也可以做到,在用户使用是使用字节码根据机器情况在做一次编译) | 编译需要占用运行时资源,会导致进程卡顿.
可以根据当前程序的运行情况生成最优的机器指令序列. | 由于编译时间需要占用运行时间,对于某些代码的编译优化不能完全支持,需要在程序流畅和编译时间之间做权衡.
当程序需要支持动态链接时,只能使用JIT. 4.可以根据进程中内存的实际情况调整代码,使内存能够更充分的利用.|在编译准备和识别频繁使用的方法需要占用时间,使得初始编译不能达到最高性能.

## 混合编译
**Android N 引入**了使用编译+解释+JIT的混合运行时,以获得安装时间,内存占用,电池消耗和性能之间的最佳折衷. 优点: 即使是大型应用程序的安装时间也减少到几秒钟. 系统更新安装得更快,因为它们不需要优化步骤. 应用程序的RAM占用空间较小,在某些情况下降至50％. 改善了表现. 降低电池消耗.

# App 是如何沙箱化，为什么要这么做？

Android沙箱本质是为了实现不同应用程序之间的互相隔离，而这种隔离策略是通过让不同的应用程序运行于各自己的虚拟机进程中实现的。

沙箱，对使用者来说可以理解为一种安全环境，对恶意访问者来说是一种限制。

在Android系统中，应用（通常）都在一个独立的沙箱中运行，即每一个Android应用程序都在它自己的进程中运行，都拥有一个独立的Dalvik虚拟机实例。Dalvik经过优化，允许在有限的内存中同时高效地运行多个虚拟机的实例，并且每一个Dalvik应用作为一个独立的Linux进程执行。Android这种基于Linux的进程“沙箱”机制，是整个安全设计的基础之一。

Android从Linux继承了已经深入人心的类Unix进程隔离机制与最小权限原则，同时结合移动终端的具体应用特点，进行了许多有益的改进与提升。具体而言，进程以隔离的用户环境运行，不能相互干扰，比如发送信号或者访问其他进程的内存空间。因此，Android沙箱的核心机制基于以下几个概念：

1. 标准的Linux进程隔离
2. 大多数进程拥有唯一的用户ID（UID）
3. 以及严格限制文件系统权限。

