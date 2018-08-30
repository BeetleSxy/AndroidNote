# Android多进程的Applicationd多次初始化

 一般情况下，一个应用开启一个进程，application 会被执行一次，如果涉及多开进程，那情况就不同了，application 就会被执行多次，下面笔者根据这问题对应用开启多进程的进行分析：

## 遇到

 最近重构代码优化性能的时候，在 log 日志中发现每次启动应用时 Application 的 onCreate 执行了多次，导致了重复初始化资源，影响启动时间。

## 探明

后在 Baidu 与 Google 的帮助下发现问题所在 **android:process ** 

通常，一个应用的所有组件都运行在系统为这个应用所创建的默认进程中。这个默认进程是用这个应用的包名来命名的。标签的process属性可以设置成和所有组件都不同的默认值。但是这些组件可以通过设置自己的process值来覆写这个默认值，这样可以让你的应用跨多进程运行。

**如果声明文件中的组件或者应用没有指定这个属性则默认应用和其组件将相应运行在以其包名命名的进程中。**

**一般来说Application的onCreate方法只会执行一次，如果应用中采用多进程方式，oncreate方法会执行多次，根据不同的进程名字进行不同的初始化。**

看官方文档上说:

  一般情况下一个服务没有自己独立的进程，它一般是作为一个线程运行于它所在的应用的进程中。但是也有例外，Android声明文件中的android:process属性却可以为任意组件包括应用指定进程，换句话说，通过在声明文件中设置android:process属性,我们可以让组件（例如Activity, Service等）和应用(Application)创建并运行于我们指定的进程中。如果我们需要让一个服务在一个远端进程中运行（而不是标准的它所在的apk的进程中运行），我们可以在声明文件中这个服务的标签中通过android:process属性为其指定一个进程。


```java
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.soubw.prodemo" >//包名

<activity android:name=".LoginActivity"
    android:process=":login"/>//为activity开启一个进程

<service  
    android:name="com.baidu.location.f"  
    android:enabled="true"  
    android:process=":baiduMap" >  
</service>  
```

上面就是为登录的Activity开启一个叫login进程，只不过这个进程是在以应用默认的包名下开启的进程，叫com.soubw.prodemo:login，在属性中值为什么以冒号开头呢，因为’:’这个前缀将把这个名字附加到你的包所运行的标准进程名字的后面作为新的进程名称（只不过这个login进程为该应用私用，其他应用不能共享），上面的例子很好的讲述这点。

还有一种是不以冒号开头而以小写字母开头，我们也举个例子来说明：

```java
<activity android:name=".RegisterActivity"
    android:process="com.android.register"/>//为activity开启一个不同于应用包名的进程
```

这个进程将以com.android.register这个名字命名的运行于全局的进程中（该进程就可以让不同应用中的各种组件可以共享一个进程）。

## 解决

只要在初始化时进行判断是否是主线程，只在主进程中进行初始化就可以，当然你只要在新进程中进出时初始化也是可以的。

```java
String processName = getProcessName(this, android.os.Process.myPid());  
if (processName != null) {  
    boolean defaultProcess = processName.equals(Constants.REAL_PACKAGE_NAME);  
    // 默认的主进程启动时初始化应用  
    if (defaultProcess) {  
        initAppForMainProcess();  
    }   
    // 其他进程启动时初始化对应内容  
    else if (processName.contains(":webbrowser")) {  

    } else if (processName.contains(":wallet")) {  

    }  
}  
```