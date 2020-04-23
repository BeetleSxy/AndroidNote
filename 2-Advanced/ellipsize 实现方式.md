 今天在项目中遇到一个问题:在一段文字如果超过3行就将文件阶段并在第三行结尾显示 `...阅读全文` 这个要怎么去处理呢,使用 TextView 去处理结尾只能是 `...` 啊. 更何况 `阅读全文` 我还要去做点击事件,点击后展示全文.这个要怎么去处理呢?

思考好久无果,突然灵光一闪,那么 `TextView.ellipsize()` 是怎么处理的呢?

```java
if (singleLine && getKeyListener() == null && ellipsize < 0) {
    ellipsize = 3; // END
}

switch (ellipsize) {
    case 1:
        setEllipsize(TextUtils.TruncateAt.START);
        break;
    case 2:
        setEllipsize(TextUtils.TruncateAt.MIDDLE);
        break;
    case 3:
        setEllipsize(TextUtils.TruncateAt.END);
        break;
    case 4:
        if (ViewConfiguration.get(context).isFadingMarqueeEnabled()) {
            setHorizontalFadingEdgeEnabled(true);
            mMarqueeFadeMode = MARQUEE_FADE_NORMAL;
        } else {
            setHorizontalFadingEdgeEnabled(false);
            mMarqueeFadeMode = MARQUEE_FADE_SWITCH_SHOW_ELLIPSIS;
        }
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        break;
}
```

这里对传递进来的 ellipsize 进行分类,这里我只看为 END 时:

```java
public void setEllipsize(TextUtils.TruncateAt where) {
    // TruncateAt is an enum. != comparison is ok between these singleton objects.
    if (mEllipsize != where) {
        mEllipsize = where;

        if (mLayout != null) {
            nullLayouts();
            requestLayout();
            invalidate();
        }
    }
}
```

这里看到将值传递给了 `mEllipsize` 并且 `shouldEllipsize = mEllipsize != null;` 传递给了 `shouldEllipsize` ;

继续看下竟然都是使用了 `TextUtils.ellipsize()` 方法
来来我们在看看 `TextUtils.ellipsize()` 是干了什么


```java
/**
 * Returns the original text if it fits in the specified width
 * given the properties of the specified Paint,
 * or, if it does not fit, a copy with ellipsis character added
 * at the specified edge or center.
 * If <code>preserveLength</code> is specified, the returned copy
 * will be padded with zero-width spaces to preserve the original
 * length and offsets instead of truncating.
 * If <code>callback</code> is non-null, it will be called to
 * report the start and end of the ellipsized range.  TextDirection
 * is determined by the first strong directional character.
 */
public static CharSequence ellipsize(CharSequence text,
                                     TextPaint paint,
                                     float avail, TruncateAt where,
                                     boolean preserveLength,
                                     EllipsizeCallback callback) {
    return ellipsize(text, paint, avail, where, preserveLength, callback,
            TextDirectionHeuristics.FIRSTSTRONG_LTR,
            (where == TruncateAt.END_SMALL) ? ELLIPSIS_TWO_DOTS_STRING : ELLIPSIS_STRING);
}
```


```java
/**
 * Returns the original text if it fits in the specified width
 * given the properties of the specified Paint,
 * or, if it does not fit, a copy with ellipsis character added
 * at the specified edge or center.
 * If <code>preserveLength</code> is specified, the returned copy
 * will be padded with zero-width spaces to preserve the original
 * length and offsets instead of truncating.
 * If <code>callback</code> is non-null, it will be called to
 * report the start and end of the ellipsized range.
 *
 * @hide
 */
public static CharSequence ellipsize(CharSequence text,
        TextPaint paint,
        float avail, TruncateAt where,
        boolean preserveLength,
        EllipsizeCallback callback,
        TextDirectionHeuristic textDir, String ellipsis) {

    int len = text.length();

    MeasuredText mt = MeasuredText.obtain();
    try {
        float width = setPara(mt, paint, text, 0, text.length(), textDir);

        if (width <= avail) {
            if (callback != null) {
                callback.ellipsized(0, 0);
            }

            return text;
        }

        // XXX assumes ellipsis string does not require shaping and
        // is unaffected by style
        float ellipsiswid = paint.measureText(ellipsis);
        avail -= ellipsiswid;

        int left = 0;
        int right = len;
        if (avail < 0) {
            // it all goes
        } else if (where == TruncateAt.START) {
            right = len - mt.breakText(len, false, avail);
        } else if (where == TruncateAt.END || where == TruncateAt.END_SMALL) {
            left = mt.breakText(len, true, avail);
        } else {
            right = len - mt.breakText(len, false, avail / 2);
            avail -= mt.measure(right, len);
            left = mt.breakText(right, true, avail);
        }

        if (callback != null) {
            callback.ellipsized(left, right);
        }

        char[] buf = mt.mChars;
        Spanned sp = text instanceof Spanned ? (Spanned) text : null;

        int remaining = len - (right - left);
        if (preserveLength) {
            if (remaining > 0) { // else eliminate the ellipsis too
                buf[left++] = ellipsis.charAt(0);
            }
            for (int i = left; i < right; i++) {
                buf[i] = ZWNBS_CHAR;
            }
            String s = new String(buf, 0, len);
            if (sp == null) {
                return s;
            }
            SpannableString ss = new SpannableString(s);
            copySpansFrom(sp, 0, len, Object.class, ss, 0);
            return ss;
        }

        if (remaining == 0) {
            return "";
        }

        if (sp == null) {
            StringBuilder sb = new StringBuilder(remaining + ellipsis.length());
            sb.append(buf, 0, left);
            sb.append(ellipsis);
            sb.append(buf, right, len - right);
            return sb.toString();
        }

        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(text, 0, left);
        ssb.append(ellipsis);
        ssb.append(text, right, len);
        return ssb;
    } finally {
        MeasuredText.recycle(mt);
    }
}
```


代码写的简单明了,不过我在来说一下吧:

先看传递进来的参数是用来干什么的:

参数|作用
-|-
CharSequence text|完整的文字
TextPaint paint| TextView 的 paint 你可以使用 `TextView.getPaint()` 来获取
float avail|你显示区域的的宽度需要将 Padding 和 margin 减去
TruncateAt where| 如何删减(这里是 TextUtils 中的枚举类)
boolean preserveLength|返回被截取后的文字还是在控件中的容纳的文字
EllipsizeCallback callback|回调返回截取开始和结束位置
TextDirectionHeuristic textDir|我没搞懂,好像表示是截取的方向,正序还是倒叙??(被隐藏)
String ellipsis|省略的符号默认 `...` 和 `..` (被隐藏)

剩下的就好解决了,方法有了那就开始实现功能了.
