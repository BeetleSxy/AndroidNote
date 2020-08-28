# 布局

我们要使用`DataBinding`首先要在布局文件中以根标记 `layout` 开头，后跟 `data` 元素和 `view` 根元素；

示例:

```xml
<?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android">
       <data>
           <variable name="user" type="com.example.User"/>
       </data>
       <LinearLayout
           android:orientation="vertical"
           android:layout_width="match_parent"
           android:layout_height="match_parent">
           <TextView android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="@{user.firstName}"/>
           <TextView android:layout_width="wrap_content"
               android:layout_height="wrap_content"
               android:text="@{user.lastName}"/>
       </LinearLayout>
    </layout>
    
```

## \<data> 标签

### 变量描述

```xml
<variable name="user" type="com.example.User" />
```

在布局中使用`@{}`写入特定的属性

```xml
<TextView android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:text="@{user.firstName}" />
```

### 数据对象

```kotlin
 data class User(val firstName: String, val lastName: String)
```

**注意:这样使用，只会在加载布局时调用一次数据，之后就不会在读取对象中的数据了。**

## 绑定数据

如果只是按照