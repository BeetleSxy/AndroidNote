# 控制流
- if
- when
- for
- while

## if 控制语句

在 Kotlin 中，if是一个表达式，即它会返回一个值。 也可以当做三元运算符（条件 ? 然后 : 否则）

	// 传统用法
	var max = a 
	if (a < b) max = b
	
	// With else 
	var max: Int
	if (a > b) {
	    max = a
	} else {
	    max = b
	}
	 
	// 作为表达式
	val max = if (a > b) a else b


if的分支可以是代码块，最后的表达式作为该块的值：

	val max = if (a > b) {
	    print("Choose a")
	    a
	} else {
	    print("Choose b")
	    b
	}

**注意：**
如果你使用 if 作为表达式而不是语句（例如：返回它的值或者把它赋给变量），该表达式需要有 else 分支。

## when

when 是用来取代 switch 操作符：

	when (x) {
	    1 -> print("x == 1")
	    2 -> print("x == 2")
	    else -> { // 注意这个块
	        print("x is neither 1 nor 2")
	    }
	}

在Kotlin中when还可以用来判断函数，这点很厉害，发几个例子：
	
例1:如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔：
	when (x) {
	    0, 1 -> print("x == 0 or x == 1")
	    else -> print("otherwise")
	}

例2：我们可以用任意表达式（而不只是常量）作为分支条件

	when (x) {
	    parseInt(s) -> print("s encodes x")
	    else -> print("s does not encode x")
	}

例3：我们也可以检测一个值在（in）或者不在（!in）一个区间或者集合中：

	when (x) {
	    in 1..10 -> print("x is in the range")
	    in validNumbers -> print("x is valid")
	    !in 10..20 -> print("x is outside the range")
	    else -> print("none of the above")
	}

例4：另一种可能性是检测一个值是（is）或者不是（!is）一个特定类型的值。注意： 由于智能转换，你可以访问该类型的方法和属性而无需任何额外的检测。

	fun hasPrefix(x: Any) = when(x) {
	    is String -> x.startsWith("prefix")
	    else -> false
	}

例5：用来取代 if-else if链。 如果不提供参数，所有的分支条件都是简单的布尔表达式，而当一个分支的条件为真时则执行该分支：

	when {
	    x.isOdd() -> print("x is odd")
	    x.isEven() -> print("x is even")
	    else -> print("x is funny")
	}


## for循环

for 循环可以对任何提供迭代器（iterator）的对象进行遍历：

	for (item in collection) print(item)
循环体可以是一个代码块。

	for (item: Int in ints) {
	    // ……
	}

如上所述，for 可以循环遍历任何提供了迭代器的对象。即：

- 有一个成员函数或者扩展函数 iterator()，它的返回类型
- 有一个成员函数或者扩展函数 next()，并且
- 有一个成员函数或者扩展函数 hasNext() 返回 Boolean。

这三个函数都需要标记为 operator。

对数组的 for 循环会被编译为并不创建迭代器的基于索引的循环。


如果你想要通过索引遍历一个数组或者一个 list，你可以这么做：

	for (i in array.indices) {
	    print(array[i])
	}

**注意：**
这种“在区间上遍历”会编译成优化的实现而不会创建额外对象。

或者你可以用库函数 withIndex：

	for ((index, value) in array.withIndex()) {
	    println("the element at $index is $value")
	}

## While 循环
while 和 java 中一样使用