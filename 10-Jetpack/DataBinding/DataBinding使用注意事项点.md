1. 变量声明variable后，需要在binding中赋值
2. xml中用到的类，需要import，如控制View的显示隐藏，需要导入View类
3. int类型对应为Integer，且在@{}用于text时候，需要转为String
4. 在@{}中可以使用反引号作为``String，注意非英文编码可能出错，甚至会编译报错
5. 资源引用使用@string、@color、@drawable等，IDE不会补全，注意拼写无误
6. 使用default设置默认值，用于预览编辑
7. null判空配合??使用，默认值可以``反引号、资源引用或者变量、静态函数值
8. String的format使用也是可以``、@string或者其他变量等
9. 静态函数，java的写法和kotlin的写法不同，实质都是jvm虚拟机的静态函数化
10. 函数调用的多种写法，无参、有参、context，以及静态函数调用（针对对象，而非类）。