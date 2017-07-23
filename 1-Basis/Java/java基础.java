
day01 ----------------------------------------------------------------------------------------------------------------------

 * 计算机基础知识

	* 快捷键

		ctrl + A
		ctrl + C
		ctrl + V
		ctrl + X
		ctrl + S
		ctrl + Z

		windows + D
		windows + E
		windows + R
		windows + L

	* DOS命令

		* D:
		* dir
		* cd 
		* cd ..
		* cd \
		* md
		* rd
		* del
		* cls
		* exit

 * JAVA语言的入门
		
	* java语言的特点

		** 开源
		** 跨平台(平台: 操作系统) ; 一处编译到处运行 , 跨平台是由jvm实现的

	* JRE 和 JDK

		** JRE: java的运行环境 , 包含了jvm 和核心类库
		** JDK: java的开发工具包 , 包含了jre和java的开发工具

	* jdk的下载和安装

 * HelloWorld

		class HelloWorld {
			public static void main(String[] args) {
				System.out.println("HelloWorld") ;
			}
		}

 * PATH环境变量
		
	** 为什么要配置: 是为了让我们的java命令和javac命令在任意路径下都可以执行
	** 建议的配置方式:

		* 新建一个环境变量: JAVA_HOME=jdk的安装目录的bin目录的上一级路径
		* 修改PATH=%JAVA_HOME%\bin;...

 * classpath环境变量

	** 不需要配置
	** 作用: 为了让我们的某一个类对应的字节码文件在任意路径下都可以执行
	** 怎么配置:

		新建一个环境变量: classpath=.;某一个类的字节码文件对应的路径

 * 注释
	
	** 概述: 解释说明程序的文字
	** 分类:
		
		* 单行注释	// 
		* 多行注释	/* */
		* 文档注释  /** */
	
	** 作用:

		* 解释说明程序的文字
		* 排错

 * 关键字
	
	** 被java语言赋予特殊含义的单词
	** 特点:	组成关键字的字母都是小写
	** 注意事项:	goto 和 const 是java语言中的保留字 , 对于第三方的记事本软件对关键字是存在颜色变化的

 * 标识符

	** 概述: 就是给类 ,接口, 方法 , 变量,  常量起名字时使用的字符序列
	** 字符序列组成规则:

		* 英文大小写字符
		* 数字字符
		* $ 和 _
	
	** 注意事项:
	
		* 不能以数字开始
		* 不能是java语言中的关键字
		* 区分大小写

	** 命名规则

		* 包			都是小写
		* 类和接口
			
			** 如果是一个单词,首字母大写
			** 如果是多个单词,每一个单词的首字母都是大写

		* 方法和变量
			
			** 如果是一个单词,都是小写
			** 如果是多个单词,从第二个单词开始首字母大写

		* 常量

			** 全部都是大写
			** 如果是多个单词,每一个单词之间使用 "_" 隔开  MAX_VALUE
		

day02 ----------------------------------------------------------------------------------------------------------------------

	* 常量
		
		** 概述: 就是在程序的运行过程中其值不发生改变的量
		** 分类:

			* 字面值常量
				
				** 字符串常量		使用双引号引起来的内容就是字符串常量,比如: "HelloWorld"
				** 整数常量			包含所有的整数, 比如: 110 , 9527
				** 小数常量			包含所有的小数, 比如: 3.1415926
				** 字符常量			使用单引号引起来的内容就是字符串常量 , 注意事项:单引号中只能是单个字母,单个符合, 单个数字
				** 布尔常量			true 和 false 
				** 空常量			null

			* 自定义常量(面向对象)

		** 不同进制数据的表现形式

			* 二进制			由 0 , 1 组成 ; 以0b开始
			* 八进制			由 0, ..7组成 ; 以0开始
			* 十进制			由 0,...9组成 ; 整数默认就是十进制
			* 十六进制			由 0,...9 a , b , c , d , e ,f 组成 , 以0x开始

		** 有符号数据表示法:

			* 原码
			* 反码
			* 补码

			正数的原码,反码 ,补码都是相同的, 负数的反码是在原码的基础上, 符号位不变, 数值位取反 ; 负数的补码 , 是在反码的末尾+1

			计算机底层在做运算的时候,都是使用的补码进行

	* 变量
		
		** 概述: 就是在程序的运行过程中,其值可以发生改变的量
		** 定义变量的格式:	数据类型 变量名 = 变量值;
		** 数据类型分类:

			* 基本数据类型
				
				** 整数型
						
						byte	1	-128 ~ 127		
						short	2
						int		4
						long	8

				** 浮点数
						
						float	4
						double	8

				** 字符型
						
						char	2	0 ~ 65535

				** 布尔型
						
						boolean


			* 引用数据类型(数组,类, 接口)

			整数默认是int类型 , 浮点数默认是double类型

		** 定义变量的注意事项

			* 作用域的问题: 在同个作用域内不能定义同名的变量 
			* 默认值的问题: 变量在使用之前必须对其赋值
			* 在一行上建议只写一条语句

		** 数据类型转换

			* 隐式转换	byte, short , char ---> int ---> long ----> float ----> double
			* 强制转换

				** 固定的格式: 目标数据类型 变量名 = (目标数据类型)(待转换的数据) ;
		
		** 字符和字符串参与运算的情况

			* 字符参与运算		char ---> int , 去查ASCII码表 ; 'a' ---> 97 , 'A' ---> 65 , '0' ---> 48
			* 字符串参与运算

				** 任何数和字符串进行相加,得到的结果都是一个新的字符串
				** + 表示的是字符串连接符

	* 运算符

		** 算术运算符
			
			** 包含: + , - , * , / , % , ++ , --
			** / : 两个整数相除, 得到的结果就是一个整数 , 结果其实就是除法的商
			** % : 获取的是除法的余数 , 结果的正负和左边有关系	
			** ++ :
				
				* 作用: 进行自加1
				* 使用

					** 单独使用			++在前和++在后 , 结果是一样的
					** 参与运算的使用

						* ++在前,先进行自加1 , 然后在参与运算
						* ++在后,先参与运算 , 然后在进行自加1 
			

		** 赋值运算符
			
			** 包含: = , -= , += , *= , /= , %=
			** int a = 34 ;		// 把34赋值给int类型的变量a
			** +=: 把左边和右边进行相加,然后在赋值给左边的变量

		** 比较运算符

			** 包含: == , != , > , < , >= , <=
			** 注意: 比较运算符计算完毕以后结果都是一个boolean类型的值
			** 注意: 不能将 "==" 写成 "="

day03 ----------------------------------------------------------------------------------------------------------------------

	* 运算符
		
		** 逻辑运算符:	& , | , ^ , ! , && , ||

			& : 有false, 则false
			| : 有true, 则true
			! : 非false则true , 非true则false
			^ : 相同为false , 不同为true
			&& : 有false, 则false ; 当左边为false的时候,右边就不执行了
			|| : 有true, 则true ; 当左边为true的时候,右边就不执行了

			常用的逻辑运算符: && , || , !

		** 位运算符: & , | , ^ , ~ , << , >> , >>>
			
			都是对二进制数据的补码进行操作

			& : 有0则0
			| : 有1则1
			^ : 相同为0 ,不同为1
			~ : 按位取反
			<< : 用左边的数乘以2的移动次幂
			>> : 用左边的数除以2的移动次幂
			>>>: 得到的结果都是正数

			^: 一个数被另一个数据异或两次其值不变

		** 三元运算符

			格式: (关系表达式) ? 表达式1 : 表达式2 ;
			执行流程:	首先计算关系表达式的值,看其结果是true还是false , 如果是true ,执行表达式1 , 如果是false , 执行表达式2 ;

	* 键盘录入数据
			
		** 步骤:

			1. 导包
				
				* 格式: import java.util.Scanner ;
				* 位置: 在class上边

			2. 创建键盘录入对象
				
				* 格式: Scanner sc = new Scanner(System.in) ;

			3. 接收键盘录入数据
				
				* 格式: int x = sc.nextInt() ;


	* 流程控制语句
		
		** 概述: 就是用来控制程序的执行流程
		** 分类:

			1. 顺序结构
				
				没有固定的语法结构 , 程序从上往下依次执行

			2. 选择结构
			
				* if 语句
					
					** if语句的第一种格式:

						if(关系表达式) {
							语句体 ;
						}

						执行流程: 计算关系表达式的值,看其结果是true还是false , 如果是true执行,语句体; 如果是false,就不执行语句体 ;
					
					** if 语句的第二种格式:

						if(关系表达式) {
							语句体1 ;
						}else {
							语句体2 ;
						}

						执行流程: 计算关系表达式的值,看其结果是true还是false , 如果是true执行语句体1; 如果是false ,执行语句体2 ;

					** if 语句的第三种格式:

						if(关系表达式1) {
							语句体1 ;
						}else if(关系表达式2) {
							语句体2 ;
						}
						...
						else {
							语句体n+1 ;
						}

						执行流程: 
						
							计算关系表达式1的值,看其结果是true还是false , 如果是true执行语句体1 , if语句结束 ;
							如果是false , 计算关系表达式2的值,看其结果是true还是false,  如果是true执行语句体2 , if语句结束 ;
							....
							如果都没有满足条件的if语句 ,那么执行else对应的语句体 ;
								

					* if语句的注意事项

						** 关系表达式无论简单还是复杂,结果必须是一个boolean类型的值
						** 如果if语句控制的是一条语句,大括号可以省略,如果是多条,就不能省略,建议永远都不要省略
						** 有左大括号就没有";"
						

				* switch 语句

					** 格式:

						switch(表达式) {
							case 值1 :
								语句体1 ;
								break ;
							case 值2 :
								语句体2 ;
								break ;
							case 值3 :
								语句体3 ;
								break ;
							...
							default : 
								语句体n+1 ;
								break ;
						}
	
						表达式的取值: 可以取 byte , short , int , char ; jdk1.5以后可以取枚举 ; jdk1.7以后可以取字符串

						执行流程:
							
							计算表达式的值,然后找对应的case的值,如果找到了就执行对应的case的语句体 , 遇到break程序结束;
							如果没有找到对应的case , 那么就执行default语句
					
					** 注意事项:

						a:case后面只能是常量，不能是变量，而且，多个case后面的值不能出现相同的
						b:default可以省略吗?
							* 可以省略，但是不建议，因为它的作用是对不正确的情况给出提示。
							* 特殊情况：
								* case就可以把值固定。
								* A,B,C,D
						c:break可以省略吗?
							* 最后一个可以省略,其他最好不要省略
							* 会出现一个现象：case穿透。
							* 最终我们建议不要省略
						d:default一定要在最后吗?
							* 不是，可以在任意位置。但是建议在最后。
						e:switch语句的结束条件
							* a:遇到break就结束了
							* b:执行到switch的右大括号就结束了
			3. 循环结构

day04 ----------------------------------------------------------------------------------------------------------------------

	* 流程控制语句之循环结构
		
		** for 循环
			
			* for循环的格式:

				for(初始化表达式语句 ; 判断条件语句 ; 控制条件语句) {
					循环体语句 ;
				}

			* 执行流程:
				
				1. 执行初始化表达式语句
				2. 执行判断条件语句, 看其返回结果是true还是false , 如果是true,就继续执行, 如果是false , 就结束循环
				3. 执行循环体语句
				4. 执行控制条件语句
				5. 回到2继续
			
		** while 循环
			
			* while 循环的格式:

				初始化表达式语句  ;
				while(判断条件语句) {
					循环体语句 ;
					控制条件语句 ;
				}

			* 执行流程:
				
				1. 执行初始化表达式语句
				2. 执行判断条件语句, 看其返回结果是true还是false , 如果是true,就继续执行, 如果是false , 就结束循环
				3. 执行循环体语句
				4. 执行控制条件语句
				5. 回到2继续

		** do...while 循环

			* do...while 循环的格式:

				初始化表达式语句 ;
				do {
					循环体语句 ;
					控制条件语句 ;
				}while(判断条件语句) ;

			* 执行流程:

				1. 执行初始化表达式语句
				2. 执行循环体语句
				3. 执行控制条件语句
				4. 执行判断条件语句, 看其返回结果是true还是false , 如果是true,就继续执行, 如果是false , 就结束循环
				5. 回到2继续

		** 练习题:

			* 求和思想
			* 统计思想

	* 控制跳转语句
		
		** break 
			
			* 应用场景: switch , 循环中
			* 作用: 用来结束循环或者switch语句

		** continue
			
			* 应用场景: 循环中
			* 作用: 结束本次循环 , 执行下一次循环

		** return 

			* 作用: 结束方法的

	* 方法

		** 作用: 用来提高代码的复用性
		** 概述: 就是用来完成某一种功能的代码块
		** 格式:

			修饰符 返回值类型 方法名(参数类型 变量名1 , 参数类型 变量名2 , ...) {
				方法体 ;
				return 返回值 ;
			}

			修饰符:			public static 
			返回值类型:		用来限定方法的返回值的 , 本质上就是数据类型
			方法名:			就是一个合法标识符
			参数类型:		本质上也是数据类型 , 作用: 用来限定调用该方法的时候传入的具体的数据的类型
			变量名:			就是一个合法标识符 , 将方法定义上的变量,称之为形式参数 , 作用: 用来接收实际参数; 实际参数: 就是调用该方法时传入的具体的值
			方法体:			功能的代码块
			返回值:			就是该方法执行完毕以后的结果

		** 方法的调用:

			* 有明确返回值类型的方法调用
				
				** 直接调用(了解)
				** 输出调用(还有点用)
				** 赋值调用(建议大家使用)

			* 没有明确返回值类型的方法调用

				** 直接调用

		** 方法的注意事项:

			* 方法不调用不执行
			* 方法与方法是平级关系，不能嵌套定义
			* 方法定义的时候参数之间用逗号隔开
			* 方法调用的时候不用在传递数据类型
			* 如果方法有明确的返回值，一定要有return带回一个值
	
		** 方法重载:

			* 概述:	允许在同一个类中,存在一个以上的同名方法,只要它们的参数列表不同 , 和返回值类型无关

			参数列表不同:

				参数的个数不同
				参数的类型不同

day05 ----------------------------------------------------------------------------------------------------------------------

	* 数组

		** 概述:  就是一个用来存储同一种数据类型的元素的一个容器
		** 一维数组(掌握)
		
			* 格式:

				** 数据类型[] 数组名 = new 数据类型[数组的长度] ;  (推荐使用)
				** 数据类型 数组名[] = new 数据类型[数组的长度] ;

				数据类型:	 作用:用来限定数组中存储元素的类型
				数组名:		 合法的标识符
				数组的长度:	 作用: 用来限定数组中存储元素的个数

			* 数组的初始化:

				** 动态初始化	就是由我们给出数组的长度,由系统分配初始化值

					* 格式: 数据类型[] 数组名 = new 数据类型[数组的长度] ;  (推荐使用)
	
				** 静态初始化	就是由我们给出初始化值,由系统分配长度

					* 格式: 数据类型[] 数组名 = new 数据类型[]{元素1 , 元素2 , 元素3 , ...} ;  
					* 格式: 数据类型[] 数组名 = {元素1 , 元素2 , 元素3 , ...} ;  

				注意事项: 不能进行动静结合

			* 如何获取数组的长度:	数组名.length ;
			* 如何获取数组的元素:	数组名[索引] ;  // 索引: 从0开始, 最大索引为arr.length - 1

			* java语言的内存分配:

				** 栈: 存储的都是局部变量
					
					* 局部变量: 定义在方法声明上或者方法定义中

				** 堆: 存储的都是 new 出来的东西

					* 系统为每一个new出来的东西分配地址值
					* 系统为每一个元素,分配默认值

						byte, short , int ,long  ---- 0
						float , double			 ---- 0.0
						char					 ---- '\u0000'
						boolean					 ---- false

						引用数据类型		     ---- null

					* 使用完毕以后就变成了垃圾,等待垃圾回收器对其进行回收

				** 方法区(面向对象)
				** 本地方法区(和系统相关)
				** 寄存器(CPU)

			* 两个错误

				** ArrayIndexOutOfBoundsException
				** NullPointerException

			* 数组的常见操作:

				** 遍历
				** 反转
				** 获取最值
				** 查表法
				** 基本查找

		** 二维数组(了解)

			* 概述: 就是一个数组 , 只不过每一个元素也是一个数组
			* 定义格式:

				** 第一种格式

					数据类型[][] 数组名 = new 数据类型[m][n] ;		(推荐使用)
					数据类型[] 数组名[] = new 数据类型[m][n] ;
					数据类型 数组名[][] = new 数据类型[m][n] ;

					m: 表示的意思是这个二维数组中一维数组的个数
					n: 每一个一维数组中元素的个数

				** 第二种格式:

					数据类型[][] 数组名 = new 数据类型[m][] ;
					m: 表示的意思是这个二维数组中一维数组的个数

				** 第三种格式:

					数据类型[][] 数组名 = {{元素1 , 元素2 , ...} , {元素1 , 元素2 , ...} , {元素1 , 元素2 , ...} ,...} ;

				** 如何获取二维数组的长度: 数组名.length ;
				** 如何获取二维数组的元素: 数组名[m][n];	获取二维数组中第m+1个一维数组中的第n+1个元素

			* 练习题

				** 遍历
				** 求和
			
	* java语言的参数传递问题

		基本数据类型的参数传递,形式参数的改变对实际参数没有影响,因为传递的是具体的数值
		引用数据类型的参数传递,形式参数的改变对实际参数有影响,因为传递的是地址值 ; ( String 例外)

day06 --------------------------------------------------------------------------------------------------------------------------------------------------------

	* 面向对象的思想
		
		** 就是指挥对象做事情 , 体现到代码上: 就是创建对象调用方法
		** 面向对象是基于面向过程的
		** 面向对象的特征:

			* 封装
			* 继承
			* 多态

	* 类和对象
		
		** 类:		就是一组相关属性和行为的集合 ; 我们定义一个类,其实就是定义成员变量和成员方法
		** 对象:	就是该事物的一个具体的体现
		** 属性:	就是该事物固有的信息
		** 行为:	就是该事物可以做的事情
		** 类和事物的对应关系

				事物						类

					属性						成员变量
					行为						成员方法
			
			定义成员变量: 和之前定义变量一致 ,只不过位置不同 ; 成员变量的位置在类中方法外
			定义成员方法: 和之前定义方法一样, 只不过去掉 static

		** 定义学生类
		** 定义手机类

	* 类的使用
		
		** 使用步骤:

			1. 创建对象 ;		格式:	类名 对象名 = new 类名() ;
			2. 访问成员变量 ;	格式:	对象名.变量名 ;					// 前提就是这个成员变量不能被private修饰
			3. 访问成员方法 ;	格式:	对象名.方法名(...) ;

	* 对象内存图
		
		** 一个对象的内存图					目的: 告诉大家我们这个对象在内存中是如何进行构建的
		** 两个对象的内存图					目的: 告诉大家每 new 一次,都会在堆内存中开辟一个新的空间
		** 3个引用两个对象的内存图			目的: 告诉大家栈内存中的多个引用可以指向堆内存中的同一个地址

	* 方法的形式参数是类名的时候我们如何调用
		
		** 传递对象
		** 代码:

			class StudentDemo {
				
				public void function(Student s) {
					s.show() ;
				}
			
			}
			
			class Student {
				
				public void show() {
					System.out.println("Student....show................") ;
				}
			
			}

			// 需求: 调用StudentDemo类中的function方法
			// 1. 创建StudentDemo对象
			StudentDemo sd = new StudentDemo() ;

			// 2. 创建一个Student对象
			Student s = new Student() ;

			// 3. 调用function方法
			sd.function(s) ;

	* 成员变量和局部变量的区别
		
		** 在类中的位置不同
			
			* 成员变量: 在类中方法外
			* 局部变量: 在方法声明上或者方法定义中

		** 在内存中的位置不同
			
			* 成员变量: 在堆内存中
			* 局部变量: 在栈内存中

		** 生命周期不同
			
			* 成员变量: 随着对象的创建而产生,随着对象的消失而消失
			* 局部变量: 随着方法的调用而产生,随着方法的消失而消失

		** 默认值的问题
			
			* 成员变量: 存在默认值
			* 局部变量: 没有默认值,使用之前必须对其进行赋值

	* 匿名对象
		
		** 概述: 就是没有名字的对象
		** 举例: new Student() ;
		** 使用场景:

			* 当仅仅调用一次方法的时候,我们就可以使用匿名对象
			* 可以作为参数进行传递

	* 封装(private 关键字)
		
		** private : 是一个权限修饰符
		** 可以用来修饰成员变量和成员方法
		** 被 private 修饰的成员,只能在本类中访问
		** 最常见的应用: 就是把成员变量通过private修饰,然后对外提供getXxx()和setXxx()方法

	* this 关系
		
		** 代表的是本类对象的一个引用 ; 谁调用我这个方法,我这个方法中的this就代表谁 ;
		** 使用this关键字,我们可以区分到底访问的是局部变量还是成员变量 ; 因为变量的访问遵循一个"就近原则"

day07 --------------------------------------------------------------------------------------------------------------------------------------------------------

	* 构造方法
		
		** 特点:

			* 方法名称和类名系统
			* 没有返回值类型,连void也没有
			* 没有具体的返回值
		
		** 作用: 对数据进行初始化的
		** 注意事项:

			* 如果我们没有给出构造方法,那么系统会提供一个无参的构造方法
			* 如果我们给出了构造方法,那么系统就不会系统无参的构造方法

	* 对象的创建步骤
		
		Student s = new Student() ;

		1. 把Student.class加载到方法区
		2. 在栈内存中为s开辟空间
		3. 在堆内存中为new Student() 开辟空间
		4. 给对象的成员变量进行默认初始化
		5. 给对象的成员变量进行显式初始化
		6. 调用构造方法对成员变量进行初始化
		7. 把堆内存中的地址值赋值给栈内存中的引用变量s

	* static 关键字
		
		** 意思: 静态的
		** 特点:
			
			1. 被static修饰的成员,被该类的所有的对象所共享
			2. 随着类的加载而加载
			3. 优先于对象存在
			4. 可以通过类名访问,本身也可以通过对象名访问 ,建议使用类名

		** 注意事项:

			1. 静态方法中不能存在this关键字
			2. 静态只能访问静态 , 而非静态可以访问静态也可以访问非静态

	* API的查看

		** 步骤:
		
			1. 双击打开API
			2. 点击显示, 找到索引
			3. 输入要查找的类名 , 敲击Enter两次
			4. 看该类所属的包, java.lang包下的类,在使用的时候不需要导包 ,其他的都需要导包
			5. 看该类的描述
			6. 看版本
			7. 看构造方法 ; 目的: 看我们如何来创建该类的对象
			8. 看方法摘要

				* 左边	看返回值类型(目的: 看待会我们调用完方法以后,应该使用什么数据类型进行接收) , 以及是否是静态(目的: 看该方法我们是否可以通过类名直接访问)
				* 右边  看参数列表; (目的: 看我们调用该方法的时候需要传递一个什么样数据类型的数据)
			



		class StudentDemo {

			public static void main(String[] args) {
			
				// 调用Student类中的createStudent的方法
				Student s = new Student() ;
				
				// 创建一个IPhone对象
				IPhone p = IPhone.createIPhone() ;
				Student haha = s.createStudent(p) ;
				
				// 调用show方法
				haha.show() ;

			}
		
		}

		class IPhone {							// 要的都是对象
		
			public static IPhone createIPhone() {
				return new IPhone() ;
			}

			public void usePhone() {
				System.out.println("使用苹果手机打电话....") ;
			}

		}

		class Student {
			
			public Student createStudent(IPhone iphone) {	
				iphone.usePhone() ;
				return new Student() ;
			}

			public void show() {
				System.out.println("学生晕倒了.................") ;
			}
			
		}

day008 ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	* 代码块
		
		** 概述: 使用{}括起来的代码
		** 分类:
			
			* 局部代码块: 
				
				位置: 在方法定义中
				作用: 限定变量的生命周期

			* 构造代码块
				
				位置: 在类中方法外
				特点: 每创建一次对象,就执行一次 ; 优先于构造方法执行

			* 静态代码块	(掌握)
				
				位置: 在类中方法外 , 前面需要添加上static
				特点: 随着类的加载而执行 , 优先于构造代码块 , 只执行一次

			* 同步代码块(多线程)

	* 继承
		
		** 格式:  class 子类 extends 父类 {}
		** 好处:

			* 提高了代码的复用性
			* 提高了代码的维护性
			* 让类和类产生了关系,是多态的前提

		** 弊端: 提高了的代码的耦合性 ; 开发的原则是: 高内内聚 , 低耦合
		
		** 特点: 只支持单继承 , 不支持多继承 , 但是支持多层继承
		** 注意事项:

			* 子类只能继承父类中非私有的成员
			* 父类的构造方法,子类不能继承 , 但是可以通过super关键字去访问
			* 不能为了某一些功能去使用继承 ; 我们继承体现的是 "is a"的关系

		** 继承中成员的访问特点:

			* 成员变量: 遵循一个"就近原则"
				
				this 和 super 的区别:

					this 代表的是本类对象的一个引用
					super 代表的是父类对应的一个引用

				this 和 super 去访问成员的时候的格式:
					
					成员变量	this.变量名 ; super.变量名 ;
					构造方法	this(...)   ; super(...) ;
					成员方法	this.方法名(...) ; super.方法名(...) ;

			* 构造方法: 子类在初始化的时候,默认需要调用父类无参的构造方法
					
				所有的构造方法的第一条语句默认是 super() ;
				Object: 是继承体系中的顶层父类,所有的类都是直接或者间接的继承该类
				this(...) 和 super(...) 不能同时存在 , 必须是构造方法的第一条语句

			* 成员方法 : 查找顺序为: 先在本类中查找 , 如果没有上父类中查找 , 父类如果没有 ,就报错了 ; 

	* 方法的重写的注意事项:

		** 父类中私有的方法,子类不能重写
		** 子类在重写父类的方法的时候,要求访问权限不能低于父类的方法权限,最好一致
		** 父类中静态的方法,子类在重写的时候,需要使用静态

	* 方法重写和方法重载的区别

		** 方法重写,发生在子父类的继承中, 子类中出现了和父了一模一样的方法(方法名称 , 参数列表 , 返回值)
		** 方法重载,发生在同一个类中 , 允许同时存在两个以后的同名方法 , 只要参数列表不同 , 和返回值没有关系

	* final 关键字

		final: 是一个修饰符 , 意思: 最终的
		final 可以修饰类 , 方法 , 变量

		被final 修饰的类 , 不能被继承
		被final修饰的方法 , 不能被重写
		被final修饰的变量,其实是一个常量,只能被赋值一次

day09 ----------------------------------------------------------------------------------------------------------------------

	* 多态
		
		** 概述: 就是同一个事物在不同时刻表现出来的不同状态
		** 前提:

			* 需要存在继承的关系
			* 需要存在方法重写
			* 需要存在父类的引用指向子类对象		父 f = new 子() ;

		** 多态的形式访问类中的成员

			* 成员变量	编译看左边 , 运行看左边
			* 构造方法	子类在初始化的时候,都要默认调用父类无参的构造方法,对父类的数据进行初始化
			* 成员方法
				
				** 非静态成员方法	编译看左边 , 运行看右边
				** 静态成员方法		编译看左边 , 运行看左边

		** 多态的好处和弊端

			* 好处:
				
				** 提高了代码的复用性
				** 提高了代码的维护性
				** 提高了代码的扩展性
			
			* 弊端:

				** 不能访问子类特有的功能

		** 向下转型: 格式: 子类 对象名 = (子类)父类的引用 ;
		** 向上转型: 多态就是向上转型的一种体现形式

	* 抽象类
		
		** 定义抽象类的格式:	abstract class 类名 {}
		** 定义抽象方法的格式:	修饰符 abstract 返回值类型 方法名称(参数列表) ;
		** 特点:
			
			1. 抽象类不能直接实例化,但是可以通过多态的形式对其进行间接实例化
			2. 抽象类中不一定要存在抽象方法,但是如果一个类中存在了抽象方法,那么这个类我们就需要定义成抽象类
			3. 子类的问题:

				* 可以是抽象类
				* 可以是非抽象类 , 但是这个类必须要重写抽象类中所有的抽象方法
		
		** 成员特点

			* 成员变量	可以是变量,也可以是常量
			* 构造方法	有 , 作用: 用于子类在访问父类数据的时候对父类数据进行初始化
			* 成员方法	可以是抽象方法,也可以是非抽象方法

	* 接口
		
		** 定义接口的格式:	interface 接口名 {}
		** 特点:

			1. 接口不能直接实例化,但是可以通过多态的形式对其进行间接实例化
			2. 子类的问题:

				* 可以是抽象类
				* 可以是非抽象类 , 但是这个类必须要重写接口中所有的抽象方法 

		** 成员特点
			
			* 成员变量	只能是常量; 因为存在默认的修饰符: public static final
			* 构造方法	没有
			* 成员方法	只能是抽象方法, 因为存在默认的修饰符: public abstract

	** 类与类 , 类与接口 , 接口和接口之间的关系

		* 类与类继承(extends)的关系,只能是单继承 , 但是可以是多层继承
		* 类与接口是实现(implements)的关系 , 可以是单实现, 也可以是多实现; 并且一个类在继承一个类的同时去实现多个接口
		* 接口和接口是继承(extends)的关系 , 可以是单继承,也可以是多继承

	** 抽象类和接口的区别

		* 成员的区别
		* 关系的区别
		* 设计理念的区别

			抽象类可以被继承 ,体现的是一种"is a"的关系 , 抽象类中定义的都是该继承体系中共性的内容
			接口需要被实现 , 体现的是一种"like a"的关系 , 接口中定义的都是该继承体系中扩展性的内容

day10 ----------------------------------------------------------------------------------------------------------------------

	* 包(了解)
	* 四种权限修饰符

						本类		同一个包下(子类和无关类)	不同包下的子类		不同包下的无关类
		private			 Y				
		默认			 Y				Y							
		protected		 Y				Y							Y
		public			 Y				Y							Y						Y
			
	* 内部类

		** 概述: 就是把一个类定义在另一个类中,那么这个类我们就将其称之为内部类
		** 按照位置进行分类:
		
			* 成员内部类		就是把这个类定义在了成员位置(类中方法外的位置)
				
				** private:		提高数据的安全性		
				** static :		访问其他的类访问

				** 非静态的成员内部类被其他类创建对象的格式:	外部类名.内部类名 对象名 = 外部类对象.内部类对象 ;
				** 静态的成员内部类被其他类创建对象的格式:		外部类名.内部类名 对象名 = new 外部类名.内部类名() ;

			* 局部内部类		就是把这个类定义在局部位置(方法定义中的位置)

				** 特点:	局部内部类在访问局部变量的时候,要求这个局部变量要被final修饰
				** 为什么?  请看图片---- 局部内部类的特点.png

		** 内部类的特点

			* 内部类可以直接访问外部类的成员,包含私有的
			* 外部类要访问内部类的成员,需要创建对象

		** 匿名内部类

			* 它是局部内部类的简化格式
			* 前提: 需要存在一个类或者接口 ; 这个类可以是抽象类, 也可以是非抽象类 ;
			* 格式:

				new 类名/接口名() {
					方法重写 ;
				} ;

			* 本质: 就是一个继承某一个类或者实现某一个接口的子类对象

			
day11 ----------------------------------------------------------------------------------------------------------------------
	
	* Eclipse
		
		** 快捷键
			* 1:Alt+/ 起提示作用
			* 2:main+alt+/,syso+alt+/,给出其他提示
			* 3:补充输出语句,选中需要输出的部分,alt+/选择最后一项即可
			* 4:定义自己的alt + /
			* 5:新建 ctrl + n
			* 6:格式化  ctrl+shift+f
			* 7:导入包  ctrl+shift+o 
			* 8:注释  ctrl+/,ctrl+shift+/,ctrl+shift+\
			* 9:代码上下移动 选中代码alt+上/下箭头
			* 10:查看源码  选中类名(F3或者Ctrl+鼠标点击)
			* 11:查找具体的类 ctrl + shift + t
			* 12:查找具体类的具体方法 ctrl + o
			* 13:给建议 ctrl+1,根据右边生成左边的数据类型,生成方法
			* 14:删除代码 ctrl + d
			* 15:抽取方法alt + shift + m 
			* 16:改名alt + shift + r  
			* 17:复制行 ctrl + alt + 方向键下键
	* Object 类

		** 看api
		** 咋看?

			1. 打开api
			2. 找到索引
			3. 输入要查找的类,敲击enter两下
			4. 看包 ,java.lang包下的类在使用的时候不需要导包 , 其他都需要进行导包
			5. 看类的描述
			6. 看构造方法(目的: 看我们如何来创建该类的对象)
			7. 看成员方法(目的: 看这个类给我们提供了哪些功能)

		** Object: 在java.lang 包下 ; 所有的类都是默认直接或者间接继承这个类,Object 是继承体系结构的顶层父类
		** 构造方法: public Obejct()
		** 成员方法

			public int hashCode() ;				获取对象的哈希码值
			public Class getClass() ;			获取一个类对应的字节码文件对象
			public String toString() ;			返回对象的字符串表现形式
			public boolean equals() ;			比较两个对象是否相等

		** 面试题:

			== 和 equals 的区别?

day12 ----------------------------------------------------------------------------------------------------------------------
	
	* Scanner 类
		
		** 作用: 可以用来接收键盘录入数据
		** 构造方法:

			public Scanner(InputStream source) ;
		
		** 成员方法

			hasNextXxx():	判断我们录入的数据的类型是否是Xxx这种类型
			nextXxx():		接收Xxx这个类型的数据

		** 常见录入数据的方法

			* nextInt():	录入一个int类型的值
			* nextLine():	录入一个字符串类型的数据

		** 存在的小问题

			就是当我们先录入int类型的值,然后在录入String类型的值的时候,那么这个String类型的执行不让我们录入

			解决方案:

				1. 在录入String类型的值之前重新创建一个Scanner对象
				2. 所有的数据全部按照String类型的进行录入,然后后期使用基本数据类型包装类将其转换成指定的类型

	* String 类

		** 概述: String 来表示字符串
		** 特点:

			1. 字符串字面值就是字符串的一个对象  "abac"
			2. 一旦被创建值就不能被改变

		** 构造方法

			public String()
			public String(byte[] bytes)
			public String(byte[] bytes , int off , int len)
			public String(char[] chs)
			public String(char[] chs , int off , int len)
			public String(String s)
	
		** 成员方法

			* 判断功能相关的方法
				
				boolean equals(Object obj)
				boolean equalsIgnoreCase(String str)
				boolean contains(String str)
				boolean startsWith(String str)
				boolean endsWith(String str)
				boolean isEmpty()

			* 获取功能相关的方法

				int length()
				char charAt(int index)
				int indexOf(int ch)
				int indexOf(String str)
				int indexOf(int ch,int fromIndex)
				int indexOf(String str,int fromIndex)
				lastIndexOf
				String substring(int start)
				String substring(int start,int end)

			* 转换功能相关的方法
				
				 byte[] getBytes()
				 char[] toCharArray()
				 static String valueOf(char[] chs)
				 static String valueOf(int i)
				  注意：String类的valueOf方法可以把任意类型的数据转成字符串。

				 String toLowerCase()
				 String toUpperCase()
				 String concat(String str)

			* 其他功能相关的方法

				** 替换
					
					String replace(char old,char new)
					String replace(String old,String new)

				** 去除字符串的两端空格
					
					String trim()
						
				** 按照字典顺序比较字符串

					int compareTo(String str)
					int compareToIgnoreCase(String str)


day13 ----------------------------------------------------------------------------------------------------------------------

	* StringBuffer 
		
		** 特点: 是一个线程安全的可变字符序列 ; 线程安全对应的效率低 ;
		** 构造方法

			public StringBuffer()
			public StringBuffer(String s)

		** 成员方法

			* 添加功能
				
				public StringBuffer append(String str)
				public StringBuffer insert(int offset,String str)

			* 删除功能
				
				public StringBuffer deleteCharAt(int index)
				public StringBuffer delete(int start,int end)

			* 替换和反转功能
				
				public StringBuffer replace(int start,int end,String str)
				public StringBuffer reverse()

			* 截取功能

				public String substring(int start)
				public String substring(int start,int end)
		
		** String 和 StringBuffer 之间的相互转换

			* String ----> StringBuffer:	1. 可以使用StringBuffer的构造方法 2. 使用append方法
			* StringBuffer ---> String :	1. StringBuffer 的 toString() ;

		** String 作为参数传递的问题: String 虽然是一个引用数据类,但是在作为参数传递的时候是按照基本数据类型进行传递的,传递的是具体的值 

	* StringBuilder
		
		** 特点: 线程不安全的可变字符序列 ; 线程不安全对应的效率高 ;
		** String ,  StringBuffer 和 StringBuilder 的区别:
			
			1. String 和字符串缓冲区的区别是: String 是一个不可变的字符序列 , 而字符串缓冲区是可变的 
			2. StringBuffer 是一个线程安全的可变字符序列 ; 线程安全对应的效率低 ;
			3. StringBuilder 线程不安全的可变字符序列 ; 线程不安全对应的效率高 ;

	* 数组的高级操作

		** 冒泡排序
		** 选择排序
		** 二分查找

	* Arrays
		
		** 概述: 是数组工具类 , 其中提供了很多关于数组的相关操作
		** 成员方法

			public static String toString(int[] arr) ;
			public static void sort(int[] arr) ;
			public static int binarySearch(int[] arr , int value) ;

	* 基本数据类型包装类

		** 作用: 为了方便的操作基本数据类型,java就针对每一种数据类型,提供了对应的包装类类型
		** 使用基本数据类型包装类的常见操作之一: 完成 基本数据类型 和 字符串的相关转换
		** 对应关系

			byte		Byte
			short		Short
			int			Integer
			long		Long
			char		Character
			float		Float
			double		Double
			boolean		Boolean
		
		** 以Integer举例

			* 构造方法

				public Integer(int i)
				public Integer(String s)

			* 成员方法
				
				public int intValue() ;
				public static int parseInt(String s) ;

			* int 和 String 之间的相互转换

				** int ---> String
					
					1. 和 "" 进行拼接
					2. 使用String类中的静态方法valueOf:  public static String valueOf(int i) ;

				** String ---> int

					1. Integer类中的方法: public static int parseInt(String s) ;

		** Jdk1.5的新特性: 自动拆装箱

			* 自动装箱	把基本数据类型转换成包装类类型
			* 自动拆箱	把包装类类型转换成基本数据类型

		** 字节常量池: 在方法区中存在一个字节常量池其中存储的数据的范围是: -128 ~ 127 

day14 ---------------------------------------------------------------------------------------------------------------------------------------------------

	* 正则表达式
		
		** 规则

			A:字符类

				* [abc] a、b 或 c（简单类） 
				* [^abc] 任何字符，除了 a、b 或 c（否定） 
				* [a-zA-Z] a到 z 或 A到 Z，两头的字母包括在内（范围） 
				* [0-9] 0到9的字符都包括

			B:预定义字符类 

				* . 任何字符。
				* \d 数字：[0-9]
				* \w 单词字符：[a-zA-Z_0-9]

			C:Greedy 数量词 

				* X? X，一次或一次也没有
				* X* X，零次或多次
				* X+ X，一次或多次
				* X{n} X，恰好 n 次 
				* X{n,} X，至少 n 次 
				* X{n,m} X，至少 n 次，但是不超过 m 次 
			
		** String类的切割功能：public String[] split(String regex)
		** String类的替换功能：public String replaceAll(String regex,String replacement)

	* Pattern 和 Matcher
		
		** 应用这个 Pattern 和 Matcher 可以完成字符串获取功能
		** 怎么使用:

			// 获取模式器对象
			Pattern p = Pattern.compile("a*b") ;

			// 获取匹配器对象
			Mather m = p.matcher("aaaaab") ;

			// 调用方法
			public boolean matches(): 校验
			public boolean find(): 查找
			public String group(): 获取

	* Math
		
		** 成员方法

			* public static int abs(int a)
			* public static double ceil(double a)						掌握
			* public static double floor(double a)						掌握
			* public static int max(int a,int b) min自学
			* public static double pow(double a,double b)
			* public static double random()
			* public static int round(float a)							掌握
			* public static double sqrt(double a)


	* Random
		
		** 构造方法:	public Random() ;
		** 成员方法:	public int nextInt(int n) 

	* System
		
		** 成员方法:

			public void exit(int status)
			public static native long currentTimeMillis();

	* BigInteger
		
		** 构造方法: public BigInteger(String val)
		** 成员方法:

			* public BigInteger add(BigInteger val)
			* public BigInteger subtract(BigInteger val)
			* public BigInteger multiply(BigInteger val)
			* public BigInteger divide(BigInteger val)
			* public BigInteger[] divideAndRemainder(BigInteger val)

	* BigDecimal
		
		** 构造方法:  public BigDecimal(String val)
		** 成员方法

			* public BigDecimal add(BigDecimal augend)
			* public BigDecimal subtract(BigDecimal subtrahend)
			* public BigDecimal multiply(BigDecimal multiplicand)
			* public BigDecimal divide(BigDecimal divisor)

	* Date
		
		** 日期类
		** 构造方法:
			
			public Date() 
			public Date(long time)

		** 成员方法

			public void setTime(long time) ;
			public long getTime() ;

	* SimpleDateFormat
		
		** 作用完成日期对象和字符串的相互转换
		** 构造方法

			public SimpleDateFormat(String pattern) 

		** 对应关系

			y			年
			M			月
			d			日
			H			时
			m			分
			s			秒
		
		** Date ----> String :	public String format(Date date) 
		** String ----> Date :	public Date parse(String date) 

	* Calendar

		** 概述: 日历类
		** 获取实例:		public static Calendar getInstance() ;
		** 成员方法:

			public int get(int filed)
			public void add(int field , int amount)
			public void set(int year , int month , int day)


day15 ----------------------------------------------------------------------------------------------------------------------


	* 对象数组
		
		** 概述: 就是用来存储对象的数组
		** 举例: Student[] s = new Student[3] ;

	* 集合
		
		** 由来: 由于数组满足不了变化的需求,于是java就给我们提供了另外一个容器,而这个容器内就是集合
		** 数组和集合的区别:

			* 长度的区别: 数组的长度是不可变的,集合的长度是可变的
			* 存储数据类型的区别: 数组可以存储基本数据类型的元素,也可以存储引用数据类型的元素 ; 而集合只能存储引用数据类型的元素
			* 内容的区别: 数组只能存储同一种数据类型的元素 , 而集合可以存储多种数据类型的元素
		
		** 集合的继承体系图

			Collection
				
				|---- List					元素有序 , 每一个元素存在整数索引 , 可以存储重复元素
					|-- ArrayList			底层的数据的结构是数组,查询快 , 增删慢 ; 线程不安全效率高 
					|-- LinkedList			底层的数据结构是链表 , 查询慢 , 增删快 ; 线程不安全效率高
					|-- Vector				底层的数据结构是数组 , 查询快 , 增删慢 ; 线程安全, 效率低
				|---- Set					元素无序 , 每一个元素不存在整数索引 , 但是可以保证元素的唯一性
					|-- HashSet
					|-- TreeSet
		
	* Collection

		** 基本功能
			
			boolean add(E e)
			boolean remove(Object o)
			void clear()
			boolean contains(Object o)
			boolean isEmpty()
			int size()

		** 带All的功能
		
			boolean addAll(Collection c)
			boolean removeAll(Collection c)
			boolean containsAll(Collection c)
			boolean retainAll(Collection c)

		** 遍历功能

			Iterator<E> iterator()

			Iterator: 

				public boolean hasNext() ;
				public E next() ;

		** 集合转换成数组的功能

			Object[] toArray() ;
	

	* List
		
		** 特点: 元素有序 , 每一个元素存在整数索引 , 可以存储重复元素
		** 特有的功能

			* void add(int index,E element)
			* E remove(int index)
			* E get(int index)
			* E set(int index,E element)

	* Vector
		
		** 底层的数据结构是数组 , 查询快 , 增删慢 ; 线程安全, 效率低
		** 特有的功能

			* public void addElement(E obj)
			* public E elementAt(int index)
			* public Enumeration elements()

	* 数据结构

		** 概述: 就是存储数据的方式
		** 分类: 栈 , 队列 , 数组 , 链表 , 哈希表 , 树
		** 数据结构的特点:

			栈:			先进后出
			队列:		先进先出
			数组:		查询块 , 增删慢
			链表:		查询慢 , 增删快

day16 ----------------------------------------------------------------------------------------------------------------------
	
	* ArrayList	:		底层的数据的结构是数组,查询快 , 增删慢 ; 线程不安全效率高 
	* LinkedList:		底层的数据结构是链表 , 查询慢 , 增删快 ; 线程不安全效率高
		
		** 特有的功能

			public void addFirst(Object obj) ;
			public void addLast(Object obj) ;
			public Object getFirst() ;
			public Object getLast() ;
			public Object removeFirst() ;
			public Object removeLast() ;

	* 泛型
	
		** 泛型的格式:	<数据类型1 , 数据类型2 , ...>
		** 泛型的作用:	用来限定集合中存储元素的数据类型
		** 好处:
			
			* 将运行的错误提前到了编译期
			* 省略了向下转型
			* 除去黄色警告线

		** 自定义泛型

			* 自定义泛型类			public class 类名<数据类型> {}
			* 自定义泛型方法		public <数据类型> 返回值类型 方名称(数据类型 变量名) {}
			* 自定义泛型接口		public interface 接口<数据类型> {}

		** 泛型统配符

			* ?					表示任意类型
			* ? extends E		向下限定 , 表示的是E或者E的子类
			* ? super E			向上限定 , 表示的是E或者E的父类

	* 增强for循环
		
		** 作用:	为了方便的遍历数组和Collection集合
		** 格式:

			for(数据类型 变量名 : 数组名称或者Collection集合名称) {
				直接使用变量名 , 这个变量存储的就是容器中的每一个元素 ;
			}

	* 可变参数
			
		** 格式:		修饰符 返回值类型 方名称(数据类型... 变量名) {}
		** 本质:		可变参数的本质是数组
		** 注意事项:	如果方法上定义了多个参数,那么这个可变参数只能是最后一个参数
	
	* 静态导入
		
		** 格式:		import static 包名.类名.方法名 ;


day17 ----------------------------------------------------------------------------------------------------------------------
	
	* Set 集合的特点: 无序 , 元素唯一 , 每一个元素不存在整数索引 ; 遍历方式存在两种: 增强for循环 , 迭代器遍历
	* HashSet
		
		** 结论: 使用HashSet集合存储元素,要保证元素的唯一性,需要依赖于元素的两个方法一个是hashCode方法一个是equals方法 ;
				 只需要让元素重写hashCode方法和equals方法即可 ; 我们可以使用eclipse中的快捷键生成出来 , shift + alt + s ; h + enter ;

	* LinkedHashSet: 
		
		* 特点: 有序 , 唯一
		* 底层的数据结构为: 链表和哈希表 , 链表保证有序 , 哈希表保证唯一

	* TreeSet

		* 特点: 可以对元素进行排序 , 而排序分为两种方式

			1. 自然排序
			2. 比较器排序

			那么我们到底使用的是自然排序还是比较器排序 , 取决于我们在创建TreeSet集合对象的时候所选定的构造方法
			如果我们选择是无参的构造方法,那么我们使用的就是自然排序 , 如果我们选择的是接收一个Comparator参数的构造方法
			那么我们使用的就是比较器排序

			如果我们选择的是自然排序对元素有要求 , 要求元素必须去实现Comparable这个接口	
			TreeSet 保证元素唯一性依赖于compareTo 或者 compare方法的返回值是否为 0

day18 ----------------------------------------------------------------------------------------------------------------------	


	* Map	
		
		** 特点: 属于双列集合 , 键是唯一的, 值可以重复 ; Map 集合的数据结构之和键有关系
		** 功能:

			* V put(K key,V value)
			* void clear()
			* V remove(Object key)
			* boolean containsKey(Object key)
			* boolean containsValue(Object value)
			* boolean isEmpty()
			* Set<Map.Entry<K,V>> entrySet()
			* V get(Object key)
			* Set<K> keySet()
			* Collection<V> values()
			* int size()
			
	* HashMap
			
		** 结论: 使用HashMap集合存储元素,要保证元素的唯一性,需要依赖于元素的两个方法一个是hashCode方法一个是equals方法 ;
				 只需要让元素重写hashCode方法和equals方法即可 ; 我们可以使用eclipse中的快捷键生成出来 , shift + alt + s ; h + enter ;

	* TreeMap
	
		* 特点: 可以对元素进行排序 , 而排序分为两种方式

			1. 自然排序
			2. 比较器排序

			那么我们到底使用的是自然排序还是比较器排序 , 取决于我们在创建TreeSet集合对象的时候所选定的构造方法
			如果我们选择是无参的构造方法,那么我们使用的就是自然排序 , 如果我们选择的是接收一个Comparator参数的构造方法
			那么我们使用的就是比较器排序

			如果我们选择的是自然排序对元素有要求 , 要求元素必须去实现Comparable这个接口	
			TreeMap 保证元素唯一性依赖于compareTo 或者 compare方法的返回值是否为 0
		
	* LinkedHashMap

		* 特点: 有序 , 唯一
		* 底层的数据结构为: 链表和哈希表 , 链表保证有序 , 哈希表保证唯一
	
day19 ----------------------------------------------------------------------------------------------------------------------
	
	* 异常
		
		** 概述: 就是在程序的运行过程中很有可能会出现的问题
		** 分类:

			Throwable

				|-- Error
				|-- Exception
					
					|-- 编译期异常	非 RuntimeException
					|-- 运行期异常	RuntimeException  以及 RuntimeException 的子类

		** JVM对异常的默认处理方式: 就是把错误信息打印到控制台, 并结束程序
		** 异常处理方案

			* try...catch...语句
				
				** 格式: 

					try {
						可能会出现问题的代码 ;
					}catch(异常名称 变量名) {
						对异常的处理方式 ;
					} finally {
						释放资源的代码 ;
					}

					简化格式:

					try {
						可能会出现问题的代码 ;
					}catch(异常名称 变量名) {
						对异常的处理方式 ;
					}
						
					其他的格式:

						try {
							可能会出现问题的代码 ;
						}catch(异常名称 变量名) {
							对异常的处理方式 ;
						}catch(异常名称 变量名) {
							对异常的处理方式 ;
						}

			* Throwable 中常见的方法:	 public void printStackTrace(): 把异常信息打印到控制台
			* 编译期异常和运行期异常的区别:

				编译期异常: 我们必须要对其进行处理,如果不处理程序无法编译通过
				运行期异常: 我们可以对其进行处理,也可以不处理

			* throws

				throws 和 throw 的区别:
				throws 用在方法定义上 , 后面跟一个或者多个异常名称 , 如果是多个异常名称,之间使用","隔开 , 表达的意思是给该方法添加一个或者多个异常声明; 告诉调用者
					   该方法可能会出现问题
				throw 用在方法定义中,后面跟一个异常对象,这个异常对象可以是编译期异常对象也可以是运行期异常对象,表示的意思是抛出一个异常对象
			
			* finally : 被finally修饰的代码始终要被执行 , 前提是jvm不能退出
			* 自定义异常: 自己定义一个类,然后让该类去继承 RuntimeException 或者 Exception ; 然后提供指定的构造方法(两个) ; 
			* 异常的注意事项:

				** a:子类重写父类方法时，子类的方法必须抛出相同的异常或父类异常的子类。(父亲坏了,儿子不能比父亲更坏)
				** b:如果父类抛出了多个异常,子类重写父类时,只能抛出相同的异常或者是他的子集,子类不能抛出父类没有的异常
				** c:如果被重写的方法没有异常抛出,那么子类的方法绝对不可以抛出异常,如果子类方法内有异常发生,那么子类只能try,不能throws

	* File 类

		** 作用: 可以表示文件或者文件夹
		** 构造方法:

			public File(String filePathName)
			public File(String parent , String child)
			public File(File parent , String child)

		** 成员方法

			* 创建功能
				
				* public boolean createNewFile()
				* public boolean mkdir()
				* public boolean mkdirs()

			* 删除和重命名
				
				* public boolean renameTo(File dest)
				* public boolean delete()

			* 判断功能
				
				* public boolean isDirectory()
				* public boolean isFile()
				* public boolean exists()
				* public boolean canRead()
				* public boolean canWrite()
				* public boolean isHidden()

			* 获取功能
				
				* public String getAbsolutePath()
				* public String getPath()
				* public String getName()
				* public long length()
				* public long lastModified()
				* public String[] list()
				* public File[] listFiles()

				* public String[] list(FilenameFilter filter)
				* public File[] listFiles(FilenameFilter filter)
				
day20 ----------------------------------------------------------------------------------------------------------------------
	
	* IO 的作用以及分类
		
		** 作用: 处理设备之间的数据传输的
		** I: Input(输入) , O: Output(输出)
		** 什么是输入和输出? 我们说输入和输出问题是站在内存的角度而言 , 如果我们程序读取硬盘上的数据那么就是输入 , 如果我们程序往硬盘上写数据那么就是输出
		** 分类
			
			字节流
				
				字节输入流	InputStream				读
				字节输出流	OutputStream			写
	
			字符流

				字符输入流	Reader					读
				字符输出流	Writer					写

			上面的4个流对象都是抽象类,不能直接实例化,那么需要使用其子类

	* 字节流

		** FileInputStream
			
			* 构造方法	
				
				public FileInputStream(String filePathName)
				public FileInputStream(File file)
				
			* 成员方法

				public int read() ;
				public int read(byte[] bytes) ;		

		** FileOutputStream

			* 构造方法	
				
				public FileOutputStream(String filePathName)
				public FileOutputStream(File file)

				public FileOutputStream(String filePathName , boolean append)
				public FileOutputStream(File file , boolean append)
				
			* 成员方法

				public void write(int by) ;
				public void write(byte[] bytes) ;
				public void write(byte[] bytes , int off , int len) ;

		** BufferedInputStream(高效的字节输入流)
			
			* 构造方法:	public BufferedInputStream(InputStream out) ;
			* 成员方法:

				public int read() ;
				public int read(byte[] bytes) ;		

		** BufferedOutputStream(高效的字节输出流)

			* 构造方法:	public BufferedOutputStream(OutputStream out) ;
			* 成员方法:
				
				public void write(int by) ;
				public void write(byte[] bytes) ;
				public void write(byte[] bytes , int off , int len) ;

		复制文件

day21 ----------------------------------------------------------------------------------------------------------------------

	* 字符流
		
		** 组成: 字节流 + 编码表 
		** 常见的编码表: ASCII , GBK , UTF-8
		** 字符输入流	Reader
		** 字符输出流	Writer

		** InputStreamReader(转换输入流)
			
			* 构造方法:	

				public InputStreamReader(InputStream in)
				public InputStreamReader(InputStream in , String charsetName)

			* 成员方法:

				public int read() ;
				public int read(char[] chs) ;

		** OutputStreamWriter(转换输出流)

			* 构造方法:	

				public OutputStreamWriter(OutputStream out)
				public OutputStreamWriter(OutputStream out , String charsetName)

			* 成员方法:

				public void write(int ch) ;
				public void write(char[] chs) ;
				public void write(char[] chs , int off , int len) ;
				public void write(String s) ;
				public void write(String s , int off , int len) ;
	
		** FileReader
		** FileWriter

		** 高效的字符输入流		BufferedReader
			
			* 构造方法:		public BufferedReader(Reader r)
			* 特有的功能:	public String readLine() ;

		** 高效的字符输出流		BufferedWriter

			* 构造方法:		public BufferedWriter(Writer w) ;
			* 特有的功能:	public void newLine() ;

	* 递归算法

		** 概述: 就是方法定义中调用方法本身的现象
		** 注意事项:
			
			1. 递归的次数不宜过多 , 过多 , 会产生栈内存溢出
			2. 递归一定要存在出口

day22 ----------------------------------------------------------------------------------------------------------------------

	* SequenceInputStream	(了解)
	* ByteArrayOutputStream 和 ByteArrayInputStream(理解)
		
		** 特点: 都是在内存中对数据进行操作 , 不需要关闭
		** ByteArrayOutputStream

			* 构造方法: public ByteArrayOutputStream() ;
			* 成员方法:

				public byte[] toByteArray() ;
				public String toString() ;

	* RandomAccessFile	(掌握)
		
		** 特点: 父类是Object , 可以对数据进行读和写 ; 其中维护了一个文件指针,可以通过操作文件指针来进行位置的精确控制
		** 构造方法:	public RandomAccessFile(String filePathName , String mode) ;		// mode: rw: 可读可写 
		** 成员方法:

			public void seek(long filePointer) ;
			public long getFilePointer() ;

	* ObjectOutputStream 和 ObjectInputStream(了解)
	* DataInputStream 和 DataOutputStream(了解)
	* 打印流(掌握)
		
		** 分类: 字节打印流(PrintStream) , 字符打印流(PrintWriter)
		** 特点:

			1. 可以对任意数据进行操作		println(Object obj)
			2. 只能操作目的地(只能用其进行写数据 , 不能读取数据)
			3. 如果启动了自动刷新,那么在调用println方法的时候可以完成自动刷新
		
	* 标准输入和输出流(了解)
	* Properties(掌握)

		** 特点:

			1. 键和值都是字符串
			2. 可以和IO流进行配合使用

		** 父类是 Hashtable
		** 特有的功能:


			public Object setProperty(String key , String value) ;
			public String getProperty(String key) ;
			public Set<String> stringPropertyNames() ;

			public void load(Reader r)
			public void load(InputStream in)

			public void store(Writer w)
			public void store(OutputStream out)

day23 ----------------------------------------------------------------------------------------------------------------------
	
		多练习

day24 ----------------------------------------------------------------------------------------------------------------------
	
		* 多线程
		
			** 进程: 就是正在执行的应用程序
			** 多进程的意义: 提高CPU的使用率
			** 线程: 应用程序中的一个任务其实就是一个线程
			** 多线程的意义:	提高应用程序的使用率 ; 我们应用在执行的时候都是依赖于线程去抢占CPU的执行权,谁抢占到了CPU的执行权,那么CPU就执行谁; 
				 但是CPU的执行是具有随机性

		* JVM的运行原理
			
			** 我们使用java命令来运行一个程序,那么就需要启动JVM , 而jvm的启动就相当于启动了一个进程 , 而这个进程在启动的时候会自动启动一个线程,由
				 这个线程去调用main方法,而这个线程就是主线程 ; 并且我们JVM的启动时是线程的 , 因为至少存在两个线程一个是主线程, 一个是垃圾回收线程
		
		* 实现多线程的两种方式
			
			** 继承Thread类
					
					* 定义一个类,让该类去继承Thread 类
					* 重写run方法
					* 创建该类的对象
					* 启动线程
					
			** 实现Runnable接口
			
					* 定义一个类,让该类去实现Runnable接口
					* 重写run方法
					* 创建该类的对象
					* 创建Thread的对象,把第3步创建的对象作为参数传递进来
					* 启动线程
		
		* 获取当前正在执行的线程对象: public static Thread currentThread();
		* 线程控制
			
				** 线程休眠		public static void sleep(long time) ;
				** 线程中断		public void stop() ;
			
		* 同步代码块
			
				** 作用: 保证了数据的安全性
				** 弊端: 程序的运行效率降低了
				** 格式:
						
						synchronized(对象) {
								要被同步的代码 ;	
						}
						
		* 同步代码块 , 同步方法 , 静态同步方法的锁对象问题:
		
			同步代码块的锁对象是任意对象
			同步方法的锁对象为this
			静态同步方法的锁对象是该类的字节码文件对象

day25 ----------------------------------------------------------------------------------------------------------------------

		* 单例设计模式	(自己完成)
		* 死锁现象
			
			** 概述: 就是多个线程在因为抢占CPU的执行权的时候出现的一种相互等待的状态
			** 原因: 出现了同步代码块的嵌套
		
		* Timer
			
			** 构造方法:	public Timer() ;
			** 成员方法:	public void schedule(TimerTask task, long delay)
		
		* 等待唤醒机制: 了解
		* 线程组	ThreadGroup (了解)
		* 线程池
				
			** 使用步骤:
				
				* 获取线程池
				
						通过Executors中的静态方法获取:
						
							public static ExecutorService newFixedThreadPool(int nThreads)
							public static ExecutorService newSingleThreadExecutor()
				
				* 提交任务
				
					Future<?> submit(Runnable task);
		
		* 工厂设计模式		(自己完成)
			
				** 简单工厂设计模式
				** 工厂方法设计模式
				
		* GUI
		
			** 事件监听机制			(可以稍稍练一下)
			** 适配器设计模式		(自己完成)
			
day26 ----------------------------------------------------------------------------------------------------------------------	

	* 网络编程
		
		** 研究的事情: 研究的是多个计算机之间进行通讯
	
	* 网络通讯3要素
		
		** IP				用来标示我们计算机在互联网上的唯一性
		** 端口号		用来标示我们的进程在计算机中的唯一性	
		** 协议			规则,规定 ; 	
		
				UDP
					
					* 通讯两端不需要建立连接 , 发送的是数据包 , 数据存在大小限制 ,不能超过64k
					* 因为无连接 , 所以有可能丢失数据 , 因此属于不可靠协议
					* 因为无连接 , 所以传输速度快, 效率高	
				
				TCP
				
					* 通讯两端需要建立连接, 数据没有大小限制
					* 因为有连接 , 所以不可能丢失数据 , 因此属于可靠协议
					* 因为有连接 , 所以传输速度慢, 效率低
	
	* UDP
		
		客户端
				
				1. 创建UDP通讯的客户端对象(DatagramSocket)
				2. 创建数据包(DatagramPacket)
				3. 发送数据
				4. 释放资源
		
		服务端
		
				1. 创建UDP通讯的服务对象(DatagramSocket)
				2. 创建数据包对象(DatagramPacket)
				3. 接收数据
				4. 解析数据包
				5. 释放资源
		
	* TCP
	
			客户端
				
				1. 创建TCP通讯的客户端对象(Socket)
				2. 获取输出流对象
				3. 写数据
				4. 释放资源
			
			服务端
			
				1. 创建TCP通讯的服务端对象(ServerSocket)
				2. 接收客户端
				3. 获取输入流对象
				4. 读取数据
				5. 释放资源

day27----------------------------------------------------------------------------------------------------------------------	

	* 类加载器
			
			** 作用: 用来加载类
			** 分类:
			
					1. 根类加载器			/jdk/jre/lib/rt.jar
					2. 扩展类加载器		/jdk/jre/lib/ext/ *.jar
					3. 系统类加载器		加载的是我们自己写的类对应的字节码文件
		
	* 反射
	
			** 概述: 是java语言中一种运行状态下的机制,对于任意一个类我们都可以获取到该类中所有的成员变量 , 构造方法 , 成员方法并使用
			** 获取字节码文件对象
			
				1. getClass()
				2. 通过静态的class属性
				3. 通过Class类中的静态方法 forName(String className)
				
			** 获取构造方法
						
					public Constructor<T> getConstructor(Class<?>... parameterTypes)
					public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
					
					public Constructor<T>[] getConstructors(Class<?>... parameterTypes)
					public Constructor<T>[] getDeclaredConstructors(Class<?>... parameterTypes)
					
					
					Constructor:
					
						public T newInstance(Object... initargs)
			
			** 获取成员方法
					
					public Method getMethod(String name, Class<?>... parameterTypes)
					public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
					public Method[] getMethods()
					public Method[] getDeclaredMethods()

					Method:
					
						public Object invoke(Object obj, Object... args)

	
			** 获取成员变量
			
					public Field getField(String name)
					public Field[] getFields()
					public Field[] getDeclaredFields()
					public Field getDeclaredField(String name)

					Field:
					
						public void set(Object obj,  Object value)

	* 动态代理
	* 枚举
			
			** 什么是枚举: 是一个特殊类 , 该类只能有几个固定的对象, 我们可以使用这几个固定的对象来表示几个固定的值 
			** 格式:
			
					public enum 枚举名称 {
						枚举项1 , 枚举项2 , ... ;
					}
	
	* jdk1.7以及jdk1.8的新特性

		