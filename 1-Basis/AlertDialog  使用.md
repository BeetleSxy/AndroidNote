# AlertDialog  使用

AlertDialog  是 Android 提供的原生的提示框，有 app 包和 V7 包。V7 包是对 Material Design 兼容库，

可以简单实现一些基础的提示，当然只要PM同意使用；

## 简单的使用

我们可以使用 AlertDialog  提供的原生方法来快速的实现一先简单的功能：



### 简单文本内容：

```kotlin
AlertDialog.Builder(mContext)
                .setTitle("这里输入标题")
                .setMessage("这里输入内容")
                .setNegativeButton("取消按钮", (dialog, which) -> {/* 点击后执行 */})
                .setPositiveButton("确定按钮", (dialog, which) -> {/* 点击后执行 */})
                .setNeutralButton("说明", (dialog, which) -> {/* 点击后执行 */})
                .create()//初始化 AlertDialog 并返回 AlertDialog 实例
                .show();//显示
```





### 简单列表项

```kotlin
// 创建数据
val items = arrayOf("北京", "上海", "广州", "深圳")

AlertDialog.Builder(context!!)
    .setTitle("标题")
    .setItems(items) {dialogInterface : DialogInterface?, position : Int -> /* 点击执行 */  }
    .create()
    .show()
```



### 单选列表项

```kotlin
// 创建数据
val items = arrayOf("北京", "上海", "广州", "深圳")

AlertDialog.Builder(context!!)
    .setTitle("标题")
    .setSingleChoiceItems(items,0){dialogInterface: DialogInterface?, position: Int -> /* 点击执行 */ }
    .create()
    .show()
```



### 多选列表项

```kotlin
// 创建数据
val items = arrayOf("北京", "上海", "广州", "深圳")

AlertDialog.Builder(context!!)
    .setTitle("标题")
    .setMultiChoiceItems(items, booleanArrayOf(true,false,true,false)) {dialogInterface: DialogInterface?, position: Int, isSelect: Boolean -> /* 点击执行 */ }
    .create()
    .show()
```



### ListView 适配器

```kotlin
val items = arrayOf("北京", "上海", "广州", "深圳")// 创建数据
val adapter = ArrayAdapter<String>(this, android.R.layout.view, items)//创建适配器

AlertDialog.Builder(context!!)
    .setTitle("使用适配器")
    .setAdapter(adapter){dialogInterface：DialogInterface？ , which：Int -> }
    .create()
    .show()
```





## 定制化 AlertDialog  

仅仅只是简单的使用并不能让我应付 PM 设计出来那酷炫的原型图，那么我们就要进行对项目定制的 AlertDialog   了：



### 简单的定制

在调用`create()`后可以获取到 AlertDialog 实例对象，可以调用 AlertDialog 实例对象中的方法对弹窗内容进行简单的修改；

```kotlin
val mAlertDialog = AlertDialog.Builder(mContext)
                        .setTitle("这里输入标题")
                        .setMessage("这里输入内容")
                        .setNegativeButton("取消按钮", (dialog, which) -> {/* 点击后执行 */})
                        .setPositiveButton("确定按钮", (dialog, which) -> {/* 点击后执行 */})
                        .setNeutralButton("说明", (dialog, which) -> {/* 点击后执行 */})
                        .create()//初始化 AlertDialog 并返回 AlertDialog 实例
            
mAlertDialog.
```



### 自定义布局

```kotlin
// 加载布局
val view = LayoutInflater.from(context!!).inflate(R.layout.view,viewGroup,false)

...布局逻辑代码...

AlertDialog.Builder(context!!)
    .setTitle("标题")
    .setView(view)
    .create()//创建 Dialog
    .show()//显示 Dialog
```

这种是比较复杂的自定义方式，可以对布局进行定制；