###AsyncTask原理和优缺点
　　AsyncTask 是 Android 提供的轻量级的异步类，可以直接继承 AsyncTask，在类种实现异步操作，并提供接口反馈当前**异步执行程度**（可以通过接口实现 UI 进度更新），最后结果给 UI 主线程。

####<font color=CadetBlue>优点</font>
>简单，快捷，过程可控

####<font color=DarkSalmon>缺点</font>
>在使用多个异步操作和并需要进行 Ui 变更时,就变得复杂起来.

 
***

###handler 原理和优缺点
　　在 Handler 异步实现时,涉及到 Handler, Looper, Message,Thread 四个对象，实现异步的流程是主线程启动Thread（子线程）运行并生成 Message-Looper 获取 Message 并传递给 Handler，Handler 逐个获取 Looper 中的 Message，并进行 UI 变更。

####<font color=CadetBlue>优点</font>
>结构清晰，功能定义明确，对于多个后台任务时，简单，清晰

####<font color=DarkSalmon>缺点</font>
>在单个后台异步处理时，显的代码过多，结构过于复杂（相对）

---
##<font color=#228B22>AsyncTask 介绍</font>
　　Android 的 AsyncTask 比 Handler 更轻量级一些（只是代码上轻量一些，而实际上要比 handler 更耗资源），适用于简单的异步处理。首先明确 Android 之所以有 Handler 和  AsyncTask，都是为了不阻塞主线程（UI 线程），且 UI 的更新只能在主线程中完成，因此异步处理是不可避免的。
　　</br>
　　AsyncTask 直接继承于 Object 类，位置为 android.os.AsyncTask。要使用 AsyncTask 工作我们要提供三个泛型参数，并重载几个方法(至少重载一个)。


AsyncTask定义了三种泛型类型 Params，Progress和Result。

* Params 启动任务执行的输入参数，比如 HTTP 请求的 URL。
* Progress 后台任务执行的百分比。
* Result 后台执行任务最终返回的结果，比如 String。

使用过 AsyncTask 的同学都知道一个异步加载数据最少要重写以下这两个方法：

* doInBackground(Params…) 后台执行，比较耗时的操作都可以放在这里。注意这里不能直接操作UI。此方法在后台线程执行，完成任务的主要工作，通常需要较长的时间。在执行过程中可以调用publicProgress(Progress…)来更新任务的进度。
* onPostExecute(Result) 相当于 Handler 处理UI的方式，在这里面可以使用在 doInBackground 得到的结果处理操作UI。 此方法在主线程执行，任务执行的结果作为此方法的参数返回。

有必要的话你还得重写以下这三个方法，但不是必须的：

* onProgressUpdate(Progress…) 可以使用进度条增加用户体验度。 此方法在主线程执行，用于显示任务执行的进度。
* onPreExecute()　这里是最终用户调用Excute时的接口，当任务执行之前开始调用此方法，可以在这里显示进度对话框。
* onCancelled() 用户调用取消时，要做的操作

使用 AsyncTask 类，以下是几条必须遵守的准则：

* Task 的实例必须在UI thread 中创建；
* execute 方法必须在UI thread 中调用；
* 不要手动的调用onPreExecute(), onPostExecute(Result)，doInBackground(Params...), onProgressUpdate(Progress...)这几个方法；
* 该 Task 只能被执行一次，否则多次调用时将会出现异常；

---
##<font color=#228B22>Handler 介绍</font>
　　Handler 主要接受子线程发送的数据, 并用此数据配合主线程更新 UI.</br>　　当应用程序启动时，Android 首先会开启一个主线程, 主线程为管理界面中的UI控件，进行事件分发,更新UI只能在主线程中更新，子线程中操作是危险的。这个时候，Handler 就需要出来解决这个复杂的问题。由于 Handler 运行在主线程中(UI线程中),它与子线程可以通过 Message 对象来传递数据, 这个时候，Handler就承担着接受子线程传过来的(子线程用 sedMessage()方法传递) Message 对象(里面包含数据), 把这些消息放入主线程队列中，配合主线程进行更新UI。

Handler的特点</br>
　　Handler 可以分发 Message 对象和 Runnable 对象到主线程中, 每个 Handler 实例,都会绑定到创建他的线程中,它有两个作用:

1. 安排消息或 Runnable 在某个主线程中某个地方执行;
2. 安排一个动作在不同的线程中执行;

Handler中分发消息的一些方法:

* post(Runnable)
* postAtTime(Runnable,long)
* postDelayed(Runnable long)
* sendEmptyMessage(int)
* sendMessage(Message)
* sendMessageAtTime(Message,long)
* sendMessageDelayed(Message,long)


以上 post 类方法允许你排列一个 Runnable 对象到主线程队列中,
 sendMessage 类方法, 允许你安排一个带数据的 Message 对象到队列中