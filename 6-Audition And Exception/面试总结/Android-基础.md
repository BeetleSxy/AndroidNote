# 四大组件是什么？
Android中四大组件分别是
* Activity：活动,用于页面展示；
* Service：服务，不提供页面展示；
* Broadcast Receive：广播接受者，用于接受广播；
* Content Provider：内容提供者，支持多应用中存储和读取数据，相当与数据库；

## Activity
1. **定义**：Activity 是 Android 的四大组件之一。是用户操作的可视化界面；它为用户提供了一个完成操作指令的窗口。当我们创建完毕 Activity 之后，需要调用 `setContentView()` 方法来完成界面的显示；以此来为用户提供交互的入口。在 Android App  中只要能看见的几乎都要依托于 Activity，所以 Activity 是在开发中使用最频繁的一种组件。
2. 一个 Activity 通常就是一个单独的屏幕（窗口）。
3. Activity 之间的通信通过 Intent 来实现；
4. android 应用中每一个 Activity 都必须要在 AndroidManifest.xml 配置文件中声明，否则系统将不识别也不执行该 Activity。在 android stdio 会自动生成，但 eclipse 需要自己手动添加；

## Service
Service（服务）是安卓中的四大组件之一，它通常用作在后台处理耗时的逻辑，与 Activity 一样，它存在自己的生命周期，也需要在 AndroidManifest.xml 配置相关信息。

服务（Service)是 Android 中实现程序后台运行的解决方案，它非常适合去执行那些不需要和用户交互而且还要求长期运行的任务。服务的运行不依赖于任何用户界面，即使程序被切换到后台，或者用户打开了另外一个应用程序，服务仍然能够保持正常运行。

不过需要注意的是，服务并不是运行在一个独立的进程当中的，而是依赖于创建服务时所在的应用程序进程。与某个应用程序进程被杀掉时，所有依赖于该进程的服务也会停止运行。另外.也不要被服务的后台概念所迷惑，实际上服务并不会自动开启线程，所有的代码都是默认运行在主线程当中的。也就是说，我们需要在服务的内部手动创建子线程，并在这里执行具体的任务，否则就有可能出现主线程被阻塞住的情况。

1. service用于在后台完成用户指定的操作。service 分为两种：
    1. started（启动）：当应用程序组件（如activity）调用startService()方法启动服务时，服务处于started状态。
    2. bound（绑定）：当应用程序组件调用bindService()方法绑定到服务时，服务处于bound状态。
2. startService()与bindService()区别：
    1. started service（启动服务）是由其他组件调用startService()方法启动的，这导致服务的onStartCommand()方法被调用。当服务是started状态时，其生命周期与启动它的组件无关，并且可以在后台无限期运行，即使启动服务的组件已经被销毁。因此，服务需要在完成任务后调用stopSelf()方法停止，或者由其他组件调用stopService()方法停止。
    2. 使用bindService()方法启用服务，调用者与服务绑定在了一起，调用者一旦退出，服务也就终止，大有“不求同时生，必须同时死”的特点。
    3. 开发人员需要在应用程序配置文件中声明全部的service，使用<service></service>标签。
    4. Service通常位于后台运行，它一般不需要与用户交互，因此Service组件没有图形用户界面。Service组件需要继承Service基类。Service组件通常用于为其他组件提供后台服务或监控其他组件的运行状态。

### 定义
> Service是一个专门在后台处理长时间任务的Android组件，它没有UI。它有两种启动方式，startService和bindService。

### 这两种启动方式的区别：
> * **startService**只是启动Service，启动它的组件（如Activity）和Service并没有关联，只有当Service调用stopSelf或者其他组件调用stopService服务才会终止。
> * **bindService**方法启动Service，其他组件可以通过回调获取Service的代理对象和Service交互，而这两方也进行了绑定，当启动方销毁时，Service也会自动进行unBind操作，当发现所有绑定都进行了unBind时才会销毁Service。

### Service的onCreate回调函数可以做耗时的操作吗？
不可以，Service的onCreate是在主线程（ActivityThread）中调用的，耗时操作会阻塞UI

如果需要做耗时的操作，你会怎么做？
线程和Handler方式
是否知道IntentService，在什么场景下使用IntentService？

IntentService相比父类Service而言，最大特点是其回调函数onHandleIntent中可以直接进行耗时操作，不必再开线程。其原理是IntentService的成员变量 Handler在初始化时已属于工作线程，之后handleMessage，包括onHandleIntent等函数都运行在工作线程中。
如果对IntentService的了解仅限于此，会有种IntentService很鸡肋的观点，因为在Service中开线程进行耗时操作也不麻烦。我当初也是这个观点，所以很少用IntentService。

但是IntentService还有一个特点，就是多次调用onHandleIntent函数（也就是有多个耗时任务要执行），多个耗时任务会按顺序依次执行。原理是其内置的Handler关联了任务队列，Handler通过looper取任务执行是顺序执行的。

这个特点就能解决多个耗时任务需要顺序依次执行的问题。而如果仅用service，开多个线程去执行耗时操作，就很难管理。

## Broadcast Receive
在Android中，广播是一种广泛运用的在应用程序之间传输信息的机制。而广播接收器是对发送出来的广播进行过滤接受并响应的一类组件。可以使用广播接收器来让应用对一个外部时间做出响应。例如，当电话呼入这个外部事件到来时，可以利用广播接收器进行处理。当下载一个程序成功完成时，仍然可以利用广播接收器进行处理。广播接收器不NotificationManager来通知用户这些事情发生了。广播接收器既可以在AndroidManifest.xml中注册，也可以在运行时的代码中使用Context.registerReceive（）进行注册。只要是注册了，当事件来临时，即使程序没有启动，系统也在需要的时候启动程序。各种应用还可以通过使用Context.sendBroadcast（）将它们自己的Intent广播给其他应用程序。

1. 你的应用可以使用它对外部事件进行过滤，只对感兴趣的外部事件(如当电话呼入时，或者数据网络可用时)进行接收并做出响应。广播接收器没有用户界面。然而，它们可以启动一个activity或serice来响应它们收到的信息，或者用NotificationManager来通知用户。通知可以用很多种方式来吸引用户的注意力，例如闪动背灯、震动、播放声音等。一般来说是在状态栏上放一个持久的图标，用户可以打开它并获取消息。

2. 广播接收者的注册有两种方法，分别是程序动态注册（在运行时的代码中使用Context.registerReceive（）进行注册）和AndroidManifest文件中进行静态注册。

3. 动态注册广播接收器特点是当用来注册的Activity关掉后，广播也就失效了。静态注册无需担忧广播接收器是否被关闭，只要设备是开启状态，广播接收器也是打开着的。也就是说哪怕app本身未启动，该app订阅的广播在触发时也会对它起作用。

## Content Provider
1. android平台提供了Content Provider使一个应用程序的指定数据集提供给其他应用程序。其他应用可以通过ContentResolver类从该内容提供者中获取或存入数据。
2. 只有需要在多个应用程序间共享数据是才需要内容提供者。例如，通讯录数据被多个应用程序使用，且必须存储在一个内容提供者中。它的好处是统一数据访问方式。
3. ContentProvider实现数据共享。ContentProvider用于保存和获取数据，并使其对所有应用程序可见。这是不同应用程序间共享数据的唯一方式，因为android没有提供所有应用共同访问的公共存储区。
4. 开发人员不会直接使用ContentProvider类的对象，大多数是通过ContentResolver对象实现对ContentProvider的操作。
5. ContentProvider使用URI来唯一标识其数据集，这里的URI以content://作为前缀，表示该数据由ContentProvider来管理。

# 谈一谈对Android中Context理解

Context是一个抽象基类。在翻译为上下文，也可以理解为环境，是提供一些程序的运行环境基础信息。Context下有两个子类，ContextWrapper是上下文功能的封装类，而ContextImpl则是上下文功能的实现类。而ContextWrapper又有三个直接的子类， ContextThemeWrapper、Service和Application。其中，ContextThemeWrapper是一个带主题的封装类，而它有一个直接子类就是Activity，所以Activity和Service以及Application的Context是不一样的，只有Activity需要主题，Service不需要主题。Context一共有三种类型，分别是Application、Activity和Service。这三个类虽然分别各种承担着不同的作用，但它们都属于Context的一种，而它们具体Context的功能则是由ContextImpl类去实现的，因此在绝大多数场景下，Activity、Service和Application这三种类型的Context都是可以通用的。不过有几种场景比较特殊，比如启动Activity，还有弹出Dialog。出于安全原因的考虑，Android是不允许Activity或Dialog凭空出现的，一个Activity的启动必须要建立在另一个Activity的基础之上，也就是以此形成的返回栈。而Dialog则必须在一个Activity上面弹出（除非是System Alert类型的Dialog），因此在这种场景下，我们只能使用Activity类型的Context，否则将会出错。
getApplicationContext()和getApplication()方法得到的对象都是同一个application对象，只是对象的类型不一样。
Context数量 = Activity数量 + Service数量 + 1 （1为Application）

# 动态广播和静态广播有什么区别？

- 动态的比静态的安全
- 静态在 App 启动的时候就初始化了，动态使用代码初始化
- 静态需要配置，动态不需要
- 生存期，静态广播的生存期可以比动态广播的长很多
- 优先级动态广播的优先级比静态广播高

# Activity 的生命周期？
- onCreate() (Activity创建时调用 )
- onStart()(可见未获取焦点，无法与之交互 )
- onResume()(可见已获取焦点，可与之交互 )
- onPause()(可见，失去焦点 )
- onStop()(不可见 )
- onRestart()(Activity重启)
- onDestroy()(Activity销毁)

# Activity 的四种 LaunchMode（启动模式）的区别？

- Standard（标准模式）
  - 每次新建
- SingleTop（栈顶复用模式）
  - 会判断栈顶是否已经有启动，启动的话就服用，不会执行onCreate，会执行newIntent（）
- SingleTask（栈内复用模式）
  1. 应用内唯一，复用时newIntent（），注意复用时会将栈上的activity出栈操作。
- SingleInstance（全局唯一模式）
  - 进程唯一

## standard（默认）

创建新的 Activity ，拥有新的 ID

## singleTop（顶部唯一）

被复用时会走`onNewIntent()`而非`onCreate()`;

## singleTask（栈内唯一）

被复用时会走`onNewIntent()`而非`onCreate()`;

注意：**当前系统中已经存在目标Activity的实例**，系统会把任务栈中**目标Activity之上的所有Activity销毁**，以让目标Activity处在栈顶的位置。

不同进程可以存在重复；

## singleInstance（唯一栈）

被复用时会走`onNewIntent()`而非`onCreate()`;

全局唯一；

# Activity 之间的通信方式？

1. 在Intent跳转时携带数据
2. 借助类的静态变量来实现
3. 借助全局变量 Application 来实现
4. 借助Service服务
5. 借助外部存储来实现通讯
6. 通过观察者设计模式
7. 借助广播

# Activity 各种情况下的生命周期？

三大类：开始，绘制，响应

开始：`onCreate` -> `onStrart` -> `onResume`

关闭：`onPause` -> `onStop` -> `onDestroy`

后台销毁：`onStop` -> `onRestrart` -> `onStrart`

切换横/竖屏：`onPause` -> `onSaveInstanceState` -> `onStop` -> `onCreate` -> `onStart` -> `onResume`

在 `launchMode` 设置情况下复用 `Activity` :`onNewIntent` -> `onStrart` -> `onResume`

# Activity 和 Fragment 之间怎么通信， Fragment 和 Fragment 怎么通信？

Activity 传值给 Fragment：通过 Bundle 对象来传递，Activity 中构造 bundle 数据包，调用 Fragment 对象的 `setArguments(Bundle b)` 方法，Fragment 中使用 `getArguments()` 方法获取 Activity 传递过来的数据包取值。

Fragment 传值给 Activity：在 Fragment 中定义一个内部回调接口，Activity 实现该回调接口， Fragment 中获取 Activity 的引用，调用 Activity 实现的业务方法。接口回调机制式 Java 不同对象之间数据交互的通用方法。

Fragment 传值给 Fragment：一个 Fragment 通过 Activity 获取到另外一个 Fragment 直接调用方法传值。

# Service 的生命周期？

- 非绑定：onCreate() ->onStartCommand() ->onDestroy() 
- 绑定：onCreate() ->onBind() -> onUnbind()->onDestroy() 

# Service 的启动方式？

## StartService

通过startService启动后，service会一直无限期运行下去，只有外部调用了stopService()或stopSelf()方法时，该Service才会停止运行并销毁。

如果startService的service已经被启动，那么会在次运行onStartCommand（）

## bindService

1. bindService启动的服务和调用者之间是典型的**client-server**模式。调用者是client，service则是server端。service只有一个，但绑定到service上面的client可以有一个或很多个。这里所提到的client指的是组件，比如某个Activity。
2. **client可以通过IBinder接口获取Service实例**，从而实现在client端直接调用Service中的方法以实现灵活交互，这在通过startService方法启动中是无法实现的。
   3.bindService启动服务的生命周期与其绑定的client息息相关。当client销毁时，client会自动与Service解除绑定。当然，client也可以明确调用Context的unbindService()方法与Service解除绑定。**当没有任何client与Service绑定时，Service会自行销毁**。

# Service 与 IntentService 的区别?

service既不是独立的进程也不是独立的线程，是依赖于主线程的，所以是不建议在service里面做过多的耗时操作的，避免ANR。

intentService是Service的子类,继承service，拥有service的全部生命周期，包含了service的全部特性;

在onCreat被执行时内部会开启一个线程,用于解决在后台服务中进行耗时操作;

# Service 和 Activity 之间的通信方式？

- 在Intent跳转时携带数据（Binder）
- 通过 Broadcast（广播）的形式
- 全局变量
- 静态变量
- 外部存储
- 事件总线

# Android 动画有几种，对其理解
1. 视图动画。视图移动、view 真真的位置并未移动。
2. 帧动画。就和放电影一样，一帧一帧的播
3. 属性动画。视图移动、其位置也会随着移动。
4. 触摸返回动画。发生触摸事件时有反馈效果。比如波纹效果
5. 揭露动画。从某一个点向四周展开或者从四周向某一点聚合起来。
6. 转场动画 & 共享元素。比如切换 activity。共享元素一般我们使用在转换的前后两个页面有共同元素时。
7. 视图状态动画。就是 View 在状态改变时执行的动画效果
8. 矢量图动画。在图片的基础上做动画。
9. 约束布局实现的关键帧动画。就是给需要动画效果的属性，准备一组与时间相关的值。关键的几个值。

# 对 SurfaceView 的了解？

`SurfaceView` 继承之`View`，但拥有独立的绘制表面，即它不与其宿主窗口共享同一个绘图表面，可以单独在一个线程进行绘制;一般游戏，视频播放，直播都会使用SurfaceView；

## 与 View 的区别：

1. `View`主要适用于主动更新的情况下，而`SurfaceView`主要适用于被动更新，例如频繁地刷新
2. `View`在主线程中对画面进行刷新，而`SurfaceView`通常会通过一个子线程来进行页面的刷新
3. `View`在绘图时没有使用双缓冲机制，而`SufaceView`在底层实现机制中就已经实现了双缓冲机制

## 推荐使用 TextureView

TextureView 同 SurfaceView 一样继承之 View 并在单独线程中渲染，但是相对于 SufraceView 的局限：

1. 由于是独立的一层View，更像是独立的一个Window，不能加上动画、平移、缩放；
2. 两个SurfaceView不能相互覆盖。

Texture更像是一般的View，像TextView那样能被缩放、平移，也能加上动画。TextureView只能在开启了硬件加速的Window中使用，并且消费的内存要比SurfaceView多，并伴随着1-3帧的延迟。

注意：因为 SurfaceView 拥有双缓存机制所以，相对游戏来说要比 Texture 更具有优势；

# Serializable 和 Parcelable 的区别？

Serializable（Java 自带）： Serializable 是序列化的意思，表示将一个对象转换成可存储或可传输的状态。序列化后的对象可以在网络上进行传输，也可以存储到本地。

Parcelable（android 专用）： 除了 Serializable 之外，使用 Parcelable 也可以实现相同的效果， 不过不同于将对象进行序列化，Parcelable 方式的实现原理是将一个完整的对象进行分解， 而分解后的每一部分都是 Intent 所支持的数据类型，这样也就实现传递对象的功能了。

## 区别

1. 在使用内存的时候，Parcelable 类比 Serializable 性能高，所以推荐使用 Parcelable 类。
2. Serializable 在序列化的时候会产生大量的临时变量，从而引起频繁的 GC。
3. Parcelable 不能使用在要将数据存储在磁盘上的情况。尽管 Serializable 效率低点，但在这种情况下，还是建议你用 Serializable 。

## 实现

1. Serializable 的实现，只需要继承 Serializable 即可。这只是给对象打了一个标记，系统会自动将其序列化。
2. Parcelabel 的实现，需要在类中添加一个静态成员变量 CREATOR，这个变量需要继承 Parcelable.Creator 接口，（一般利用编译器可以自动生成）。

# 为什么不能在子线程更新 UI？

`ViewRootImpl`对象是在`onResume`方法回调之后才创建，那么就说明了为什么在生命周期的`onCreate`方法里，甚至是`onResume`方法里都可以实现子线程更新UI，因为此时还没有创建`ViewRootImpl`对象，并不会进行是否为主线程的判断

谷歌提出：“一定要在主线程更新`UI`”，实际是为了提高界面的效率和安全性，带来更好的流畅性；反推一下，假如允许多线程更新`UI`，但是访问`UI`是没有加锁的，一旦多线程抢占了资源，那么界面将会乱套更新了，体验效果就不言而喻了；所以在`Android`中规定必须在主线程更新`UI`

# Recycleview 和 ListView 的区别？



ListView

1. ListView的Adapter继承的是BaseAdapter；
2. ListView的ViewHolder不是强制要写的， 只是不写的话导致的后果就是如果数据一多，可能会导致OOM或者界面卡顿；
3. ListView的分割线直接在布局中设置 divider
4. ListView的点击事件直接是setOnItemClickListener

RecyclerView

1. RecyclerView的Adapter继承的是RecyclerView.Adapter
2. RecyclerView的ViewHolder是必须要写的，是强制的，如果不写的话，就不能重写RecyclerView.Adapter中的3个方法 getItemCount()、onCreateViewHolder()、onBindViewHolder()分别表示 总共显示多少条目、创建ViewHolder、绑定数据；
3. RecyclerView在setAdapter之前一定要设置显示的样式，否则数据不能显示;
4. RecyclerView不支持直接在布局中添加分割线;
5. RecyclerView不支持点击事件，只能用回调接口来设置点击事件在adapter中写;
6. 在绑定数据的onBindViewHolder中给所有控件设置完点击事件后判断mItemClickListener不为空的话，设置点击事件，利用回调接口来设置点击事件;

## 缓存机制

ListView与RecyclerView缓存机制原理大致一样，不同的地方是，两者的缓存层级不同，ListView只有两层，RecycleView有四级缓存。

## 局部刷新的区别

RecyclerView可以进行局部刷新，而ListView是"一锅端”

# view 的绘制流程？

Measure、Layout、Draw

- Measure： 用来计算 View 的实际大小（有父布局传递进来）
- Layout：来确定 View 在父容器的布局位置，他是父容器获取子 View 的位置参数后，调用子 View 的 layout 方法并将位置参数传入实现的。
- Draw：具体的绘制，会被多次调用

# Application 和 Activity 的 Context 之间的区别？

Activity和Application都是Context的子类，Context是上下文的意思，在实际应用中它起到了管理上下文环境中各个参数和变量的作用。虽然Application和Activity都是Context的子类，但是他们维护的生命周期是不一样的，前者维护一个Activity的生命周期，所以其对应的Context只能访问该Activity，后者维护一个Application的证明周期。





