> ## 前言

CardView 是谷歌在 Android L 中提出的 **Material Design** 设计风格最好的体现,<br>
在项目中使用需要在 Gradle 中去配置,个人建议和 RecyclerView 组合使用使用.CardView 的特点是阴影圆角在 Android L 上使用的<br>

    compile 'com.android.support:design:25.+'
    compile 'com.android.support:recyclerview-v7:25.+'

使用方式很简单只需在布局文件中去实现

    <android.support.v7.widget.CardView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
    >

这里需要注意 CardView 继承了 FrameLayout 所以使用和 FrameLayout 是一样的,但是在他的部分属性进行了更改:

* CardView_cardBackgroundColor 设置背景色
* CardView_cardCornerRadius 设置圆角大小
* CardView_cardElevation 设置z轴阴影
* CardView_cardMaxElevation 设置z轴最大高度值
* CardView_cardUseCompatPadding 是否使用CompadPadding
* CardView_cardPreventCornerOverlap 是否使用PreventCornerOverlap
* CardView_contentPadding 内容的padding
* CardView_contentPaddingLeft 内容的左padding
* CardView_contentPaddingTop 内容的上padding
* CardView_contentPaddingRight 内容的右padding
* CardView_contentPaddingBottom 内容的底padding
* card_view:cardUseCompatPadding 设置内边距，V21+的版本和之前的版本仍旧具有一样的计算方式
* card_view:cardPreventConrerOverlap 在V20和之前的版本中添加内边距，这个属性为了防止内容和边角的重叠

Beetle_SXY <br>
2017/6/21 8:58:32 