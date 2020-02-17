# 类和继承
class 也是 kotlie 的类声明

	class Invoice {
	}

类声明由类名、类头（指定其类型参数、主构造函数等）和由大括号包围的类体构成。类头和类体都是可选的； 如果一个类没有类体，可以省略花括号。

	class Empty
---

在 Kotlin 中的一个类可以有一个主构造函数和一个或多个次构造函数。主构造函数是类头的一部分：它跟在类名（和可选的类型参数）后。

	class Person constructor(firstName: String) {
	}

如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。

	class Person(firstName: String) {
	}

主构造函数不能包含任何的代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块（initializer blocks）中。