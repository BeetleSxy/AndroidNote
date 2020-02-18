# Android_基础单位



dip: device independent pixels(设备独立像素). 不同设备有不同的显示效果, 这个和设备硬件有关，一般我们为了支持 WVGA、HVGA 和 QVGA 推荐使用这个，不依赖像素。

注：[Android](http://lib.csdn.net/base/android "Android知识库") 中，dp 是 Density-independent Pixels 简写，而 dip 则是 Density Independent Pixels，实际上指的是一个概念，都是 Android 的单位。

dp 等同于 dip，它是一个长度单位，**1dp=1/160 英寸**

| 单位                   | 说明                                                         |
| ---------------------- | ------------------------------------------------------------ |
| px（像素）             | 屏幕上的点。                                                 |
| in（英寸）             | 长度单位。                                                   |
| mm（毫米）             | 长度单位。                                                   |
| pt（磅）               | 1/72 英寸                                                    |
| dp（与密度无关的像素） | 一种基于屏幕密度的抽象单位。**在每英寸 160 点的显示器上，1dp = 1px**。 |
| dip                    | 与 dp 相同，多用于 android/ophone 示例中。                   |
| sp（与刻度无关的像素） | 与 dp 类似，但是可以根据用户的字体大小首选项进行缩放。       |

