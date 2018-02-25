# Android 约束布局（ConstraintLayout）详解

---

## 简单了解一下
ConstraintLayout是Android Studio 2.2中主要的新增功能之一，可以把ConstraintLayout看成是一个更高级的RelativeLayout，它可以通过控件之间的相互约束，在一层view中实现非常复杂的布局。同时Android Studio 2.2之后对ConstraintLayout的编辑提供了特殊的可视化操作的支持，可以直接拖动控件，比常规布局的可视化支持体验要好太多。要完整的体验ConstraintLayout的所有功能。

- 优点
	- 更好的绘制速度
	- 更低的性能消耗
	- 对复杂的布局使用更少的嵌套去完成
- 缺点
	- 学习难度相对较高
	- 书写复杂
	

## 基本使用

	dependencies {
	   implementation 'com.android.support.constraint:constraint-layout:1.1.0-beta3'
	}

**这里使用的是 Android Studio 3.0 在 3.0 中 implementation 用来替代 compile** 

这之前你应该先了解一下代码是如何去实现，然后在去用界面拖动的方式去实践在，等界面拖动运用的熟悉后在仔细看代码。因为我就是这样去学习的。


### 一、相对定位属性
- layout_constraintBaseline_toBaselineOf
- layout_constraintTop_toBottomOf
- layout_constraintTop_toTopOf
- layout_constraintBottom_toBottomOf
- layout_constraintBottom_toTopOf
- layout_constraintStart_toEndOf
- layout_constraintStart_toStartOf
- layout_constraintEnd_toEndOf
- layout_constraintEnd_toStartOf
- layout_constraintLeft_toLeftOf
- layout_constraintLeft_toRightOf
- layout_constraintRight_toLeftOf
- layout_constraintRight_toRightOf

上面这些属性命名非常有规律，例如前面的 layout 说明这是一个布局属性，中间的 constraintXXX 为对当前 View 上的某个属性做的约束，而最后的 toXXOf 为当前 View 约束的对象以及约束的方位。



**这里需要注意 parent 代表的是父控件的意思**

### 二、Margin （边距）
这个库有一个特殊的边距方法：
- layout_goneMarginBottom
- layout_goneMarginEnd
- layout_goneMarginLeft
- layout_goneMarginRight
- layout_goneMarginStart
- layout_goneMarginTop

如果设置了这个属性，当 ButtonB 所参考的 ButtonA 可见的时候，这个边距属性不起作用；当 ButtonA 不可见（GONE）的时候，则这个边距就在 ButtonB 上面起作用了。

### 三、子控件的尺寸控制

ConstraintLayout 中子 View 的宽度和高度还是通过 android:layout_width 和 android:layout_height 来指定，可以有三种不同的取值：

- 使用确定的尺寸，比如 48dp
- 使用 WRAP_CONTENT ，和其他地方的 WRAP_CONTENT 一样
- 使用 0dp，这个选项等于 “MATCH_CONSTRAINT”，也就是和约束规则指定的宽(高)度一样

**注意：MATCH_PARENT 属性无法在 ConstraintLayout 里面的 子 View 上使用。虽然官方文档是这样写的但是我在使用中发现还是可以的但是会造成很多问题所以建议不使用**

**注意：在用使用 0dp 时 View 一定要有约束否则会为 0dp 不显示**

### 四、宽高比

- layout_constraintDimensionRatio 控制子 View 的宽高比

比率的取值有两种形式

1. float 值，代表宽度/高度 的比率
2. “宽度:高度”这种比率值

如果宽度和高度都是 MATCH_CONSTRAINT (0dp) 也可以使用宽高比。这种情况，系统会使用满足所有约束条件和比率的最大尺寸。要根据其中一种尺寸来约束另外一种尺寸，则可以在比率值的前面添加 W 或者 H来分别约束宽度或者高度。例如，如果一个尺寸被两个目标约束（比如宽度为0dp，在父容器中居中），你通过使用字符 W 或者 H 来指定那个边被约束。

### 五、精细控制的方式

除了基本的 View 尺寸控制以为，还有如下几个精细控制 View 尺寸的属性（注意：下面这些属性只有宽度或者高度设置为 0dp (MATCH_CONSTRAINT) 的情况下才有效）：

|方法|作用|
|-|-|
|layout_constraintWidth_default|取值为 spread 或者 wrap，默认值为 spread ，占用所有的符合约束的空间；如果取值为 Wrap ，并且view 的尺寸设置为 wrap_content 且受所设置的约束限制其尺寸，则 这个 view 最终尺寸不会超出约束的范围。|
|layout_constraintHeight_default|同上|
|layout_constraintHeight_max|取值为具体的尺寸|
|layout_constraintHeight_min|取值为具体的尺寸|
|layout_constraintWidth_max|取值为具体的尺寸|
|layout_constraintWidth_min|取值为具体的尺寸|

### 六、链条布局（Chains）

这个布局可牛逼了，可以实现类似 weight 的效果，但效果要比 weight 强大太多，当然书写要比 weight 复杂很多

#### 创建链条

如同字面意思一般就是一个链，每个 View 声明他的左右（水平）或上下（垂直）约束，ConstraintLayout 就会认为你在构建一个链条；

> 注意： 在 Android Studio 编辑器中，先把多个 View 单向引用，然后用鼠标扩选多个 View，然后在上面点击右键菜单，选择 “Center Horizontally” 或者 “Center Vertically” 也可以快速的创建 Chain。

#### 链条头（Chain heads）
Chain 的属性由该群组的第一个 View 上的属性所控制（第一个 View 被称之为 Chain head）.
水平群组，最左边的 View 为 head， 垂直群组最上面的 View 为 head。

#### 链条样式（Chain Style）

|方法|作用|
|-|-|
|layout_constraintHorizontal_chainStyle|设置水平链条样式|
|layout_constraintHorizontal_weight|加权链；和LinearLayout的weight类似|
|layout_constraintVertical_chainStyle|设置垂直链条样式|
|layout_constraintVertical_weight|加权链；和LinearLayout的weight类似|

chainStyle 是设置到 Chain Head 上的，指定不同的 style 会改变里面所有 View 的布局方式，有如下四种 Style：

|方法|作用|
|-|-|
|spread|元素将被展开（默认样式）|
|加权链|在spread模式下，如果某些小部件设置为MATCH_CONSTRAINT，则它们将拆分可用空间 |
|spread_inside |类似，但链的端点将不会扩展|
|packed|链的元素将被打包在一起。 孩子的水平或垂直偏差属性将影响包装元素的定位|

#### 加权链（Weighted chains）

和 `LinearLayout` 的 `weight` 类似。

链的默认行为是在可用空间中平均分配元素。 如果一个或多个元素使用 MATCH_CONSTRAINT ，它们将使用剩余的空白空间（在它们之间相等）。 属性 layout_constraintHorizontal_weight 和 layout_constraintVertical_weight 将决定这些都设置了 MATCH_CONSTRAINT 的 View 如何分配空间。 例如，在包含使用 MATCH_CONSTRAINT 的两个元素的链上，第一个元素使用权重为 2 ，第二个元素的权重为 1 ，第一个元素占用的空间将是第二个元素的两倍

## 强大之处

上边这些并不能体现出 ConstraintLayout 的强大之处，下面让我带大家看看他的强大：


### Guideline 

Guideline 是用来辅助定位的一个不可见的元素。可以作为参考坐标，但是是不可见的，也是专门用来辅助完成复杂布局的；

|方法|作用|
|-|-|
|android_orientation|控制 Guideline 是横向的还是纵向的|
|layout_constraintGuide_begin|控制 Guideline 距离父容器开始的距离|
|layout_constraintGuide_end|控制 Guideline 距离父容器末尾的距离|
|layout_constraintGuide_percent|控制 Guideline 在父容器中的位置为百分比|

### Barrier
它可以阻止一个或者多个控件越过自己，就像一个屏障一样。当某个控件要越过自己的时候，Barrier会自动移动，避免自己被覆盖。他的方法在Guideline几个方法上多了

|方法|作用|
|-|-|
|barrierDirection|决定 Barrier 的方向 |
|constraint_referenced_ids|选中的控件|

### 居中和偏移

之前的相对定位属性中如果你这样使用时；

    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent

ConstraintLayout 会怎么去处理呢？

这种情况下，ConstraintLayout 就像使用两个作用力分别从左边和右边来拉住这个控件，就像左右一边一个弹簧固定到父容器左右。最终的效果就是在父容器中水平居中。对于垂直方向上的约束是类似的规则。

此时我像大家介绍性的属性 Bias ：

#### Bias
像上面提到的这种对称相反布局约束会把 View 居中对齐，使用 Bias 可以改变两边的权重（类似于 LinearLayout 中的 weight 属性）：

|方法|作用|
|-|-|
|layout_constraintHorizontal_bias|水平方向的比例|
|layout_constraintVertical_bias|垂直方向的比例|

**注意这里使用：layout_constraintHorizontal_bias 时要保证控件左右有约束，否则此方法无效。同理layout_constraintVertical_bias需要控件上下有约束。**

如果没有设置 bias 值，则左右两边的取值为各占 50%，如果把左边的 bias 值修改为 30%（0.3），则左边空白的边距就小一点，而右边空白的距离就大一点。



