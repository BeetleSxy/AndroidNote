# Android7.0适配

在Android N（7.0）普及的越来越多的情况下我们对 Android N 的适配现在也是必要的了，个人感觉 Android N 的更新是安全性大于流畅度的，在 Android M （6.0）加入动态权限后 N 更一步的加大的对文件安全的力度，这些更改在为用户带来更加安全的操作系统的同时也为开发者带来了一些新的任务。如何让你的 APP 能够适应这些改变而不是 cash，是摆在每一位 Android 开发者身上的责任。

## 目录被限制访问

一直以来，在目录及文件的访问保护方面iOS做的是很到位的，如：iOS的沙箱机制。但，Android在这方面的保护就有些偏弱了，在Android中应用可以读写手机存储中任何一个目录及文件，这也带来了很多的安全问题。现在Android也在着力解决这一问题。

在Android7.0中为了提高私有文件的安全性，面向 Android N 或更高版本的应用私有目录将被限制访问。对于这个权限的更改开发者需要留意一下改变：

- 私有文件的文件权限不在放权给所有的应用，使用 MODE_WORLD_READABLE 或 MODE_WORLD_WRITEABLE 进行的操作将触发 SecurityException。

> 应对策略：这项权限的变更将意味着你无法通过File API访问手机存储上的数据了，基于File API的一些文件浏览器等也将受到很大的影响，看到这大家是不是惊呆了呢，不过迄今为止，这种限制尚不能完全执行。 应用仍可能使用原生 API 或 File API 来修改它们的私有目录权限。 但是，Android官方强烈反对放宽私有目录的权限。可以看出收起对私有文件的访问权限是Android将来发展的趋势。

- 给其他应用传递 file:// URI 类型的Uri，可能会导致接受者无法访问该路径。 因此，在Android7.0中尝试传递 file:// URI 会触发 FileUriExposedException。

> 应对策略：大家可以通过使用FileProvider来解决这一问题。

- DownloadManager 不再按文件名分享私人存储的文件。COLUMN_LOCAL_FILENAME在Android7.0中被标记为deprecated ，
旧版应用在访问 COLUMN_LOCAL_FILENAME时可能出现无法访问的路径。 面向 Android N 或更高版本的应用在尝试访问 COLUMN_LOCAL_FILENAME 时会触发 SecurityException。

> 应对策略：大家可以通过[ContentResolver.openFileDescriptor()](https://developer.android.com/reference/android/content/ContentResolver.html#openFileDescriptor(android.net.Uri, java.lang.String))来访问由 DownloadManager 公开的文件。

## 应用间共享文件

在Android7.0系统上，Android 框架强制执行了 StrictMode API 政策禁止向你的应用外公开 file:// URI。 如果一项包含文件 file:// URI类型 的 Intent 离开你的应用，应用失败，并出现 FileUriExposedException 异常，如调用系统相机拍照，或裁切照片。

> 应对策略：若要在应用间共享文件，可以发送 content:// URI类型的Uri，并授予 URI 临时访问权限。 进行此授权的最简单方式是使用 FileProvider类。 如需有关权限和共享文件的更多信息，请参阅共享文件。

## 电池和内存
Android 6.0（API 级别 23）引入了低电耗模式，Android7.0在电池和内存上又做了进一步优化，
来减少Android应用对电量的消耗以及对内存的占用。这些优化所带来的一些规则的变更可能会影响你的应用访问系统资源，以及你的系统通过特定隐式 Intent 与其他应用互动的方式。
所以开发人员需要特别注意这些改变。]

## 低电耗模式
在低电耗模式下，当用户设备未插接电源、处于静止状态且屏幕关闭时，该模式会推迟 CPU 和网络活动，从而延长电池寿命。
Android7.0通过在设备未插接电源且屏幕关闭状态下、但不一定要处于静止状态（例如用户外出时把手持式设备装在口袋里）时应用部分 CPU 和网络限制，进一步增强了低电耗模式。

> 也就是说，Android7.0会在手机屏幕关闭的状态下，限时应用对CPU以及网络的使用。

具体规则如下：

1. 当设备处于充电状态且屏幕已关闭一定时间后，设备会进入低电耗模式并应用第一部分限制： 关闭应用网络访问、推迟作业和同步。
2. 如果进入低电耗模式后设备处于静止状态达到一定时间，系统则会对 PowerManager.WakeLock、AlarmManager
闹铃、GPS 和 Wi-Fi 扫描应用余下的低电耗模式限制。 无论是应用部分还是全部低电耗模式限制，系统都会唤醒设备以提供简短的维护时间窗口，在此窗口期间，应用程序可以访问网络并执行任何被推迟的作业/同步。

## 后台优化

小伙伴们都知道在Android中有一些隐式广播，使用这些隐式广播可以做一些特定的功能，如，当手机网络变成WiFi时自动下载更新包等。
但，这些隐式广播会在后台频繁启动已注册侦听这些广播的应用，从而带来很大的电量消耗，为缓解这一问题来提升设备性能和用户体验，在Android 7.0中删除了三项隐式广播，以帮助优化内存使用和电量消耗。

Android 7.0 应用了以下优化措施：

- 在 Android 7.0上 应用不会收到 CONNECTIVITY_ACTION 广播，即使你在manifest清单文件中设置了请求接受这些事件的通知。 但，在前台运行的应用如果使用BroadcastReceiver 请求接收通知，则仍可以在主线程中侦听 CONNECTIVITY_CHANGE。
- 在 Android 7.0上应用无法发送或接收 ACTION_NEW_PICTURE 或ACTION_NEW_VIDEO 类型的广播。

> 应对策略：Android 框架提供多个解决方案来缓解对这些隐式广播的需求。 例如，[JobScheduler API](https://developer.android.com/reference/android/app/job/JobScheduler.html)
提供了一个稳健可靠的机制来安排满足指定条件（例如连入无限流量网络）时所执行的网络操作。 您甚至可以使用 [JobScheduler API](https://developer.android.com/reference/android/app/job/JobScheduler.html) 来适应内容提供程序变化。

另外，大家如果想了解更多关于后台的优化可查阅[后台优化](https://developer.android.com/preview/features/background-optimization.html)。

## 在Android7.0上调用系统相机拍照，裁切照片

因为内容较多，所以另起一篇；