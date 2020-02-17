[TOC]

[官方文档]: https://developer.android.google.cn/jetpack/androidx/releases/viewpager2#1.0.0-alpha01
[官方Demo]: https://github.com/googlesamples/android-viewpager2


# 概述

2019年11月20号期待已久的 ViewPager2 正式版终于发布了。它不像 ViewPager 一样被内置在系统源码中。要使用ViewPager2必须迁移到androidx才可以

ViewPager2继承ViewGroup，内部核心是RecycleView加LinearLayoutManager，其实就是对RecycleView封装了一层，所有功能都是围绕着RecyclerView和LinearLayoutManager展开，本文章只是简单的介绍原理和使用，具体如何实现我们在这里不展开介绍了。

# 具体改动

## 新特性

1. 
   基于 RecyclerView 实现。这意味着 RecyclerView 的优点将会被 ViewPager2 所继承。
2. 支持竖直滑动。只需要一个参数就可以改变滑动方向。
3. 支持关闭用户输入。通过`setUserInputEnabled`来设置是否禁止用户滑动页面。
4. 支持通过编程方式滚动。通过`fakeDragBy(offsetPx`)代码模拟用户滑动页面。
5. CompositePageTransformer 支持同时添加多个 PageTransformer。
6. 支持 DiffUtil ，可以添加数据集合改变的 item 动画。
7. 支持RTL  (right-to-left) 布局。
8. 完整支持`notifyDataSetChanged`

## API 变化

1. ViewPager2 被声明成了 final ，我们将无法继承 ViewPager2。
2. FragmentStatePagerAdapter 被 FragmentStateAdapter 替代。
3. PagerAdapter 被 RecyclerView.Adapter 替代。
4. addPageChangeListener 被 registerOnPageChangeCallback。
5. 移除了 setPargeMargin 方法。

# 具体使用

## 使用

### 添加依赖

```java
dependencies {
    implementation "androidx.viewpager2:viewpager2:1.0.0"
}
```

### 布局文件

```xml
<androidx.viewpager2.widget.ViewPager2
    android:id="@+id/view_pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

### Adapter

```kotlin
class MyAdapter : RecyclerView.Adapter<MyAdapter.PagerViewHolder>() {
    private var mList: List<Int> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return PagerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun setList(list: List<Int>) {
        mList = list
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    //  ViewHolder需要继承RecycleView.ViewHolder
    class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTextView: TextView = itemView.findViewById(R.id.tv_text)
        private var colors = arrayOf("#CCFF99","#41F1E5","#8D41F1","#FF99CC")

        fun bindData(i: Int) {
            mTextView.text = i.toString()
            mTextView.setBackgroundColor(Color.parseColor(colors[i]))
        }
    }
}
```

item_page中代码:

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center">

    <TextView
        android:id="@+id/tv_text"
        android:background="@color/colorPrimaryDark"
        android:layout_width="match_parent"
        android:layout_height="280dp"
        android:gravity="center"
        android:textColor="#ffffff"
        android:textSize="22sp" />
</LinearLayout>
```

### 为ViewPager设置Adapter

```kotlin
val viewPager2 = findViewById<ViewPager2>(R.id.view_pager)
val myAdapter = MyAdapter()
myAdapter.setList(data)
viewPager2.adapter = myAdapter
```

### 滑动方向

使用接口`public void setOrientation (int orientation)`这里可以传递两个值 ：

- ViewPager2.ORIENTATION_VERTICAL				//竖直滑动
- ViewPager2.ORIENTATION_HORIZONTAL		  //横向滑动（默认）

### 页面滑动事件监听

使用`public void registerOnPageChangeCallback (ViewPager2.OnPageChangeCallback callback)`

```kotlin
viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        Toast.makeText(this@MainActivity, "page selected $position", Toast.LENGTH_SHORT).show()
    }
})
```

注意`ViewPager2.OnPageChangeCallback()`有三个可实现方法，因为是一个抽象类所以设置监听事件只需要重写需要的方法即可。

- onPageScrollStateChanged(int state) //滚动状态更改时调用。
- onPageScrolled(int position, float positionOffset, int positionOffsetPixels) //当滚动当前页面时（作为程序启动的平滑滚动的一部分或由用户启动的触摸滚动的一部分），将调用此方法。
- onPageSelected(int position) //当选择新页面时，将调用此方法。

### 禁止用户滑动

API：setUserInputEnabled(boolean)

### 模拟用户滑动

注意：**fakeDragBy前需要先beginFakeDrag方法来开启模拟拖拽**

fakeDragBy会返回一个boolean值，true表示有fake drag正在执行，而返回false表示当前没有fake drag在执行。我们通过代码来尝试下：

```kotlin
fun fakeDragBy(view: View) {
    viewPager2.beginFakeDrag()
    if (viewPager2.fakeDragBy(-310f))
        viewPager2.endFakeDrag()
}
```

需要注意到是fakeDragBy接受一个float的参数，当参数值为正数时表示向前一个页面滑动，当值为负数时表示向下一个页面滑动。 

### PageTransformer

PageTransformer 不光可以设置页面动画，还可以设置页面间距你可以**同时添加多个PageTransformer**。

#### 页面间距

在 ViewPager2 中使用 MarginPageTransformer() 来设置间距：`viewPager2.setPageTransformer(
  MarginPageTransformer(resources.getDimension(R.dimen.dp_10).toInt()))`

但是 setPageTransformer 只可以输入一个对象且在次输入后会覆盖之前的设置，那么我们想输入多个 PageTransformer 改怎么办呢？

#### CompositePageTransformer

这个时候我们就要使用 `CompositePageTransformer`,来输入多个 PageTransformer

```kotlin
val compositePageTransformer = CompositePageTransformer()
compositePageTransformer.addTransformer(AnimTransformer())
compositePageTransformer.addTransformer(MarginPageTransformer(resources.getDimension(R.dimen.dp_10).toInt()))
viewPager2.setPageTransformer(compositePageTransformer)
```

如果我们要实现自己的动画效果该怎么办呢？而 ViewPager2 也为我们想到了 ，你只需继承  ViewPager2.PageTransformer 并在里面实现你的逻辑即可：

####  ViewPager2.PageTransformer

PageTransformer 是一个位于 ViewPager2 中的接口，因此 ViewPager2 的 PageTransformer 是独立于 ViewPager 的，它与 ViewPager 的 PageTransformer 没有任何关系。但是和 ViewPager 的 PageTransformer 中的实现方式是一摸一样的。

```kotlin
class ScaleInTransformer : ViewPager2.PageTransformer {
    private val mMinScale = DEFAULT_MIN_SCALE
    override fun transformPage(view: View, position: Float) {
        view.elevation = -abs(position)
        val pageWidth = view.width
        val pageHeight = view.height

        view.pivotY = (pageHeight / 2).toFloat()
        view.pivotX = (pageWidth / 2).toFloat()
        if (position < -1) {
            view.scaleX = mMinScale
            view.scaleY = mMinScale
            view.pivotX = pageWidth.toFloat()
        } else if (position <= 1) {
            if (position < 0) {
                val scaleFactor = (1 + position) * (1 - mMinScale) + mMinScale
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
                view.pivotX = pageWidth * (DEFAULT_CENTER + DEFAULT_CENTER * -position)
            } else {
                val scaleFactor = (1 - position) * (1 - mMinScale) + mMinScale
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
                view.pivotX = pageWidth * ((1 - position) * DEFAULT_CENTER)
            }
        } else {
            view.pivotX = 0f
            view.scaleX = mMinScale
            view.scaleY = mMinScale
        }
    }

    companion object {

        const val DEFAULT_MIN_SCALE = 0.85f
        const val DEFAULT_CENTER = 0.5f
    }
}
```

### 一页多屏

ViewPager2 的一屏多页可以通过 RecyclerView 设置 Padding 来实现：

```kotlin
viewPager2.apply { 
            offscreenPageLimit=1
           val recyclerView= getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding = resources.getDimensionPixelOffset(R.dimen.dp_10) +
                        resources.getDimensionPixelOffset(R.dimen.dp_10)
                // setting padding on inner RecyclerView puts overscroll effect in the right place
                setPadding(padding, 0, padding, 0)
                clipToPadding = false
            }
        }
val compositePageTransformer = CompositePageTransformer()
compositePageTransformer.addTransformer(ScaleInTransformer())
compositePageTransformer.addTransformer(MarginPageTransformer(resources.getDimension(R.dimen.dp_10).toInt()))
viewPager2.setPageTransformer(compositePageTransformer)
```

## 常用 API

添加修饰线

- `void addItemDecoration(RecyclerView.ItemDecoration decor)`

- 添加修饰线（index:如果此值为负，则将在末尾添加装饰。)
  - `void addItemDecoration(RecyclerView.ItemDecoration decor, int index)`
- 开始模拟滑动
  - `void beginFakeDrag（）`
- 结束模拟滑动
  - `endFakeDrag（）`
- 设置滑动方向（垂直/水平）
  - `void setOrientation（int orientation）`
- 禁止滑动
  - `void setUserInputEnabled（boolean enabled）`
- 获取当前页面
  - `int getCurrentItem（）`
- 设置显示页面
  - `void setCurremt（int item）`
- 获取适配器
  - `getAdapter（）`
- 设置缓存
  - `setOffscreenPageLimit(int limit)`
- 设置 ViewPager 的切换动画
  - `setPageTransformer(ViewPager2.PageTransformer transformer)`
- 页面回调（同`addPageChangeListener`）
  - `registerOnPageChangeCallback(OnPageChangeCallback).`
- 删除回调
  - `unregisterOnPageChangeCallback(ViewPager2.OnPageChangeCallback callback)`

# 与Fragment配合

ViewPager2 中使用 FragmentStateAdapter 来代替 ViewPager 中的 FragmentStatePagerAdapter；我们来简单实现一个Demo；

```kotlin
<androidx.viewpager2.widget.ViewPager2
    android:id="@+id/vp_fragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_above="@id/rg_tab" />
```

实现一个**FragmentStateAdapter**：

```kotlin
class AdapterFragmentPager(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: SparseArray<BaseFragment> = SparseArray()

    init {
        fragments.put(PAGE_HOME, HomeFragment.getInstance())
        fragments.put(PAGE_FIND, PageFragment.getInstance())
        fragments.put(PAGE_INDICATOR, IndicatorFragment.getInstance())
        fragments.put(PAGE_OTHERS, OthersFragment.getInstance())
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size()
    }

    companion object {

        const val PAGE_HOME = 0

        const val PAGE_FIND = 1

        const val PAGE_INDICATOR = 2

        const val PAGE_OTHERS = 3

    }
}
```

在 Activty 中实现：

```kotlin
vp_fragment.adapter = AdapterFragmentPager(this)
vp_fragment.offscreenPageLimit = 3
vp_fragment.isUserInputEnabled = false
```

# 与 TadLayout 配合

在androidx中，TabLayout没有setupWithViewPager（ViewPager2 viewPager2）方法，而是用TabLayoutMediator将TabLayout和ViewPager2结合。

```java
TabLayoutMediator(mTabLayout, viewPager2, (tab, position) -> tab.setText(titles.get(position))).attach()
```

