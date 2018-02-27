在kotlin中单独学习并不知道在项目中是如何使用，所以，我在看一个kotlin项目，边看边学习，看完后再去系统的学习。

### lateinit （延迟加载）
 这是 kotlin 的一个延迟加载修饰字段，**只可以用来修饰非基本类型字段；**

因为Kotlin会使用null来对每一个用lateinit修饰的属性做初始化，而基础类型是没有null类型，所以无法使用lateinit。 

### as （不安全类型转换）

as 字段是用来进行类的类型转换，但是请注意这并不安全，会造成空对象

	val x: String? = y as String?

as? 则是安全的类型转换

	val x: String? = y as? String

**请注意，尽管事实上 as? 的右边是一个非空类型的 String，但是其转换的结果是可空的。**

