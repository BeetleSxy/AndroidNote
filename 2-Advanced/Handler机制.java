概述
　　在Android中主线也叫做UI线程,主要是用来创建和更新UI的,而其他的耗时操作我们会放在子线程中比如:网络访问,文件处理,多媒体处理.所以在子线程中操作是为了保证操作的流畅，一般手机显示的刷新率为60Hz/s。没16.67毫秒刷新一次，所以为了不丢帧我们尽量保证主线程中处理代码时间不要超过16毫秒。当子线程中处理完数据，为了防止逻辑混乱只允许主线程处理UI，这个时候就需要进行线程之间的通信。Android就提供了Handler专门来进行线程之间的通信（数据传递）。

Handler的使用
　　我们通常将Handler声明在Activity中,然后去复写Handler中的handlerMessage方法,当子线程去调用handler.sendMessage();方法后就会在主线程中去执行handlermessage方法中的代码。

Handler实现
　　除了Handler、Massage外还隐藏了Looper和MassageQueue对象。
	在主线程中Android已经默认调用了Looper.prepre()方法,会在Looper中调用MassageQueue成员变量,并把Looper对象绑定到当前线程中,当调用Handler的sendMessage（对象）方法的时候就将 Message 对象添加到了 Looper 创建的 MessageQueue队列中，同时给 Message 指定了 target 对象，其实这个 target 对象就是 Handler 对象。主线程默认执行了 Looper.looper（）方法，该方法从 Looper 的成员变量 MessageQueue 中取出 Message，然后调用 Message 的 target 对象的 handleMessage()方法。这样就完成了整个消息机制。