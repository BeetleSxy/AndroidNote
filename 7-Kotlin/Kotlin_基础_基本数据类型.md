# 基础
---
## 基本类型
- 数字
- 字符
- 布尔值
- 数组
- 字符串

## 数字
|Type|Bit|
|::|::|
|Double|64|
|Float|32|
|Long|64|
|Int|32|
|Short|16|
|Byte|8|

在 Kotlin 中字符不是数字
字面常量：
- 十进制:`123`
	- Long:`123L`
- 十六进制:`0x0F`
- 二进制:`0b00001011`

**注意**不支持八进制 

自1.1起数字字面值可以带下划线，不会报错更加易读

### 自动装箱
**注意**数字装箱不必保留同一性:

	val a: Int = 10000
	print(a === a) // 输出“true”
	val boxedA: Int? = a
	val anotherBoxedA: Int? = a
	print(boxedA === anotherBoxedA) // ！！！输出“false”！！！

另一方面，它保留了相等性:

	val a: Int = 10000
	print(a == a) // 输出“true”
	val boxedA: Int? = a
	val anotherBoxedA: Int? = a
	print(boxedA == anotherBoxedA) // 输出“true”

### 显式转换
由于不同的表示方式，较小类型并不是较大类型的子类型。 如果它们是的话，就会出现下述问题：
	
	// 假想的代码，实际上并不能编译：
	val a: Int? = 1 // 一个装箱的 Int (java.lang.Integer)
	val b: Long? = a // 隐式转换产生一个装箱的 Long (java.lang.Long)
	print(a == b) // 惊！这将输出“false”鉴于 Long 的 equals() 检测其他部分也是 Long

因此较小的类型**不能**隐式转换为较大的类型。 这意味着在不进行显式转换的情况下我们不能把 `Byte` 型值赋给一个 `Int` 变量。

	val b: Byte = 1 // OK, 字面值是静态检测的
	val i: Int = b // 错误

如果我们需要进行转换的可以使用拓宽
每个数字类型支持如下的转换:

- `toByte(): Byte`
- `toShort(): Short`
- `toInt(): Int`
- `toLong(): Long`
- `toFloat(): Float`
- `toDouble(): Double`
- `toChar(): Char`

### 运算

这是完整的位运算列表（只用于 `Int` 和 `Long`）：

- `shl(bits) – 有符号左移 (Java 的 <<)`
- `shr(bits) – 有符号右移 (Java 的 >>)`
- `ushr(bits) – 无符号右移 (Java 的 >>>)`
- `and(bits) – 位与`
- `or(bits) – 位或`
- `xor(bits) – 位异或`
- `inv() – 位非`

### 浮点数比较

- 相等性检测：`a == b` 与 `a != b`
- 比较操作符：`a < b`、 `a > b`、 `a <= b`、 `a >= b`
- 区间实例以及区间检测：`a..b`、 `x in a..b`、 `x !in a..b`

## 字符
字符用 Char 类型表示。它们**不能直接当作数字**

	fun check(c: Char) {
	    if (c == 1) { // 错误：类型不兼容
	        // ……
	    }
	}

字符字面值用单引号括起来: '1'。 特殊字符可以用反斜杠转义。 支持这几个转义序列：\t、 \b、\n、\r、\'、\"、\\ 和 \$。 编码其他字符要用 Unicode 转义序列语法：'\uFF00'。

	fun decimalDigitValue(c: Char): Int {
	    if (c !in '0'..'9')
	        throw IllegalArgumentException("Out of range")
	    return c.toInt() - '0'.toInt() // 显式转换为数字
	}

当需要可空引用时，像数字、字符会被装箱。装箱操作不会保留同一性。

## 布尔
布尔用 Boolean 去代替，和 java 中的 Boolean 一样

## 数组
数组在 Kotlin 中使用 Array 类来，拥有 `get` 、 `set` 和 `size` 属性，

	class Array<T> private constructor() {
	    val size: Int
	    operator fun get(index: Int): T
	    operator fun set(index: Int, value: T): Unit
	
	    operator fun iterator(): Iterator<T>
	    // ……
	}

我们可以使用库函数 arrayof() 来创建数组并传递数值进去，也可以使用 arratOfBulls() 可以创建一个指定大小所有元素都为空的数组

	// 创建一个 Array<String> 初始化为 ["0", "1", "4", "9", "16"]
	val asc = Array(5, { i -> (i * i).toString() })

**注意**: 与 Java 不同的是，Kotlin 中数组是不型变的（invariant）。这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any>，以防止可能的运行时失败（但是你可以使用`Array<out Any>`

Kotlin 也有无装箱开销的专门的类来表示原生类型数组: ByteArray、 ShortArray、IntArray 等等。这些类和 Array 并没有继承关系，但是它们有同样的方法属性集。它们也都有相应的工厂方法:

	val x: IntArray = intArrayOf(1, 2, 3)
	x[0] = x[1] + x[2]

## 字符串
字符串用 String 类型表示。字符串是不可变的。 字符串的元素——字符可以使用索引运算符访问: s[i]。 可以用 for 循环迭代字符串:

	for (c in str) {
	    println(c)
	}

这很爽！
用`"""`可以没有转义并且可以包含换行和任何其他字符的完美显示。
可以用trimMargin()去除前导空格就是特点字符，默认是： `|`

### 字符串模板 

字符串可以包含模板表达式 ，即一些小段代码，会求值并把结果合并到字符串中。 模板表达式以美元符（$）开头，由一个简单的名字构成:
	
	val i = 10
	val s = "i = $i" // 求值结果为 "i = 10"
或者用花括号括起来的任意表达式:

	val s = "abc"
	val str = "$s.length is ${s.length}" // 求值结果为 "abc.length is 3"
原生字符串和转义字符串内部都支持模板。 如果你需要在原生字符串中表示字面值 $ 字符（它不支持反斜杠转义），你可以用下列语法：

	val price = """
	${'$'}9.99
	"""