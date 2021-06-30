# 如何做性能优化？

## 卡顿优化

1. 不要在主线程进行网络访问/大文件的IO操作
2. 绘制UI时，尽量减少绘制UI层次；减少不必要的view嵌套，可以用Hierarchy Viewer工具来检测，后面会详细讲；
3. 当我们的布局是用的FrameLayout的时候，我们可以把它改成merge,可以避免自己的帧布局和系统的ContentFrameLayout帧布局重叠造成重复计算(measure和layout)
4. 提高显示速度,使用ViewStub：当加载的时候才会占用。不加载的时候就是隐藏的，仅仅占用位置。
5. 在view层级相同的情况下，尽量使用 LinerLayout而不是RelativeLayout；因为RelativeLayout在测量的时候会测量二次，而LinerLayout测量一次，可以看下它们的源码；（优先使用约束布局）
6. 删除控件中无用的属性;
7. 布局复用.比如listView 布局复用
8. 尽量避免过度绘制（overdraw）,比如：背景经常容易造成过度绘制。由于我们布局设置了背景，同时用到的MaterialDesign的主题会默认给一个背景。这时应该把主题添加的背景去掉；还有移除 XML 中非必须的背景
9. 自定义View优化。使用 canvas.clipRect()来帮助系统识别那些可见的区域，只有在这个区域内才会被绘制。也是避免过度绘制．
10. 启动优化,启动速度的监控，发现影响启动速度的问题所在，优化启动逻辑，提高应用的启动速度。比如闪屏页面，合理优化布局，加载逻辑优化，数据准备，这里后面我会单独写一篇文章讲如何优化程序的启动速度及Splash页面设计，这里还会讲到热启动和冷启动．
11. 合理的刷新机制，尽量减少刷新次数，尽量避免后台有高的 CPU 线程运行，缩小刷新区域

## 耗电优化

1. 合理的使用wack_lock锁，wake_lock锁主要是相对系统的休眠(这里就是为了省电，才做休)而言的，意思就是我的程序给CPU加了这个锁那系统就不会休眠了，这样做的目的是为了全力配合我们程序的运行。有的情况如果不这么做就会出现一些问题，比如微信等及时通讯的心跳包会在熄屏不久后停止网络访问等问题。所以微信里面是有大量使用到了wake_lock锁。[这里有一篇关于wake_lock的使用，请查阅](https://www.jianshu.com/p/5db15ce7de1e)；
2. 使用jobScheduler2，集中处理一些网络请求，有些不用很及时的处理可以放在充电的时候处理，比如，图片的处理，APP下载更新等等，[这里有一篇关于jobScheduler的使用，请查阅](https://www.jianshu.com/p/d9a1e1d1a070)；
3. 计算优化，避开浮点运算等。
4. 数据在网络上传输时，尽量压缩数据后再传输，建议用FlatBuffer序列化技术，这个比json效率高很多倍，不了解[FlatBuffer](https://link.jianshu.com/?t=http%3A%2F%2Fgoogle.github.io%2Fflatbuffers%2Findex.html)，建议找资料学习一下，后面有时间的话，也会专门写关于FlatBuffer的文章．

## 安装包大小优化

1. res资源优化
   1. 只使用一套图片，使用高分辨率的图片。
   2. UI设计在ps安装[TinyPNG插件](https://link.jianshu.com/?t=https%3A%2F%2Ftinypng.com%2F)，对图片进行无损压缩。
   3. svg图片：一些图片的描述，牺牲CPU的计算能力的，节省空间。使用的原则：简单的图标。
   4. 图片使用WebP([https://developers.google.com/speed/webp/](https://link.jianshu.com/?t=https%3A%2F%2Fdevelopers.google.com%2Fspeed%2Fwebp%2F))的格式（Facebook、腾讯、淘宝在用。）缺点：加载相比于PNG要慢很多。 但是配置比较高。工具：[http://isparta.github.io/](https://link.jianshu.com/?t=http%3A%2F%2Fisparta.github.io%2F)
   5. 使用tintcolor(android - Change drawable color programmatically)实现按钮反选效果。
2. 代码优化
   1. 实现功能模块的逻辑简化
   2. Lint工具检查无用文件将无用的资源列在“UnusedResources: Unused resources”，删除。
   3. 移除无用的依赖库。
3. lib资源优化
   1. 动态下载的资源。
   2. 一些模块的插件化动态添加。
   3. so文件的剪裁和压缩。
4. assets资源优化
   1. 音频文件最好使用有损压缩的格式，比如采用opus、mp3等格式，但是最好不要使用无损压缩的音乐格式
   2. 对ttf字体文件压缩，可以采用FontCreator工具只提取出你需要的文字。比如在做日期显示时，其实只需要数字字体，但是使用原有的字体库可能需要10MB大小，如果只是把你需要的字体提取出来生成的字体文件只有10KB
5. 代码混淆。
   1. 使用proGuard 代码混淆器工具，它包括压缩、优化、混淆等功能。
6. 7z极限压缩
   1. 具体请参考[微信的安接包压缩](https://link.jianshu.com/?t=https%3A%2F%2Fgithub.com%2Fshwenzhang%2FAndResGuard),实现实现原理，有时间再分析；

## 内存优化

1. 对象引用。强引用、软引用、弱引用、虚引用四种引用类型，根据业务需求合理使用不同，选择不同的引用类型。
2. 减少不必要的内存开销。注意自动装箱，增加内存复用，比如有效利用系统自带的资源、视图复用、对象池、Bitmap对象的复用。
3. 使用最优的数据类型。比如针对数据类容器结构，可以使用ArrayMap数据结构，避免使用枚举类型，使用缓存Lrucache等等。
4. 图片内存优化。可以设置位图规格，根据采样因子做压缩，用一些图片缓存方式对图片进行管理等等。[图片的压缩几种方案](https://www.jianshu.com/p/f305fb008ab6);

## 稳定性优化

1. 提高代码质量。比如开发期间的代码审核，看些代码设计逻辑，业务合理性等。
2. 代码静态扫描工具。常见工具有Android Lint、Findbugs、Checkstyle、PMD等等。
3. Crash监控。把一些崩溃的信息，异常信息及时地记录下来，以便后续分析解决。
4. Crash上传机制。在Crash后，尽量先保存日志到本地，然后等下一次网络正常时再上传日志信息。

# Bitmap 如何处理大图？如何预防 OOM？

这里只做思路提供，

1. 压缩图片，采样率压缩
2. 使用区域解码器（BitmapRegionDecoder）