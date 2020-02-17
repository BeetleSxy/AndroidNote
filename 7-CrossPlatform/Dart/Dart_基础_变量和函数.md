# 变量

创建一个变量并进行初始化:

```dart
var name = 'Bob';//此时这里的变量是 name 存储了一个 String 类型的对象引用
```

`name` 变量的类型被推断为 `String` 。 但是也可以通过指定类型的方式，来改变变量类型。 如果对象不限定为单个类型，可以指定为 `对象类型` 或 `动态类型`：

```dart
dynamic name = 'Bob';// 动态类型 运行时推断类型
var name = 'Bob';// 自动推断
String name = 'Bob';// 显式声明
```

## 默认值

未初始化的变量默认值是 `null`。即使变量是数字 类型默认值也是 null，因为在 Dart 中一切都是对象，数字类型 也不例外。

# 常量 

使用过程中从来不会被修改的变量， 可以使用 `final` 或 `const`, 而不是 `var` 或者其他类型， Final 变量的值只能被设置一次； Const 变量在编译时就已经固定 (Const 变量 是隐式 Final 的类型.) 最高级 final 变量或类变量在第一次使用时被初始化。

## final

创建和设置一个 Final 变量：

```dart
final name = 'Bob'; // Without a type annotation
final String nickname = 'Bobby';
name = 'Alice'; // Error: 一个 final 变量只能被设置一次。
```

## const

如果需要在**编译时**就固定变量的值，可以使用 `const` 类型变量。 如果 Const 变量是类级别的，需要标记为 `static const`。 在这些地方可以使用在编译时就已经固定不变的值，字面量的数字和字符串， 固定的变量，或者是用于计算的固定数字：

```dart
const bar = 1000000; // 压力单位 (dynes/cm2)
const double atm = 1.01325 * bar; // 标准气压
//Const 关键字不仅可以用于声明常量变量。 还可以用来创建常量值，以及声明创建常量值的构造函数。 任何变量都可以拥有常量值。
var foo = const [];
final bar = const [];
const baz = []; // Equivalent to `const []`

foo = [1, 2, 3]; // 曾经引用过 const [] 常量值。
baz = [42]; // Error: 常量变量不能赋值修改。
```

# 函数

## 一般函数

```dart
bool isNoble(int atomicNumber) {
  return _nobleGases[atomicNumber] != null;
}
//如果函数中只有一句表达式，可以使用简写语法
bool isNoble(int atomicNumber) => _nobleGases[atomicNumber] != null;
```

### 可选参数

函数方法的可选参数通过在参数列表中用 {} 指定，例如：

```dart
void say(String name, {String word = 'hello'}){
    print('$name say $word');
  }

// 通过（可选参数名 + :）进行可选参数的赋值
main(){
    say('kuky', word: 'Hello World'); // kuky say Hello World
}
```

### 参数默认值

认值只能是编译时常量。 如果没有提供默认值，则默认值为 null。

```dart
/// 设置 [bold] 和 [hidden] 标志 ...
void enableFlags({bool bold = false, bool hidden = false}) {...}

// bold 值为 true; hidden 值为 false.
enableFlags(bold: true);
```

### 可变参数

将参数放到 `[]` 中来标记参数是可变的：

```dart
String say(String from, String msg, [String device]) {
  var result = '$from says $msg';
  if (device != null) {
    result = '$result with a $device';
  }
  return result;
}

assert(say('Bob', 'Howdy') == 'Bob says Howdy');
assert(say('Bob', 'Howdy', 'smoke signal') ==
    'Bob says Howdy with a smoke signal');
```

## Main() 函数

 `main()` 函数是应用的的顶级函数，作为应用服务的入口。 `main()` 函数返回值为空，参数为一个可选的 `List` 

```dart
void main() {
  //入口
}
```

## 函数也是对象

一个函数可以作为另一个函数的参数。 例如：

```dart
void printElement(int element) {
  print(element);
}

var list = [1, 2, 3];

// 将 printElement 函数作为参数传递。
list.forEach(printElement);
```

也可以将一个函数值赋值给一个变量：

```dart
var loudify = (msg) => '!!! ${msg.toUpperCase()} !!!';
assert(loudify('hello') == '!!! HELLO !!!');
```

## 匿名函数

创建没有名字的函数，这种函数被称为 *匿名函数*， 有时候也被称为 *lambda* 或者 *closure* 。 匿名函数可以赋值到一个变量中， 举个例子，在一个集合中可以添加或者删除一个匿名函数。

匿名函数和命名函数看起来类似— 在括号之间可以定义一些参数或可选参数，参数使用逗号分割。

```dart
var list = ['apples', 'bananas', 'oranges'];
list.forEach((item) {
  print('${list.indexOf(item)}: $item');
});
//如果函数只有一条语句， 可以使用箭头简写。
list.forEach(
    (item) => print('${list.indexOf(item)}: $item'));
```

## 作用域

**Dart 支持多个嵌套函数的变量作用域：**

```dart
bool topLevel = true;

void main() {
  var insideMain = true;

  void myFunction() {
    var insideFunction = true;

    void nestedFunction() {
      var insideNestedFunction = true;

      assert(topLevel);
      assert(insideMain);
      assert(insideFunction);
      assert(insideNestedFunction);
    }
  }
}
```

注意 `nestedFunction()` 可以访问所有的变量， 一直到顶级作用域变量

## 闭包

*闭包* 即一个函数对象，即使函数对象的调用在它原始作用域之外， 依然能够访问在它词法作用域内的变量。

```dart
/// 返回一个函数，返回的函数参数与 [addBy] 相加。
Function makeAdder(num addBy) {
  return (num i) => addBy + i;
}

void main() {
  // 创建一个加 2 的函数。
  var add2 = makeAdder(2);

  // 创建一个加 4 的函数。
  var add4 = makeAdder(4);

  assert(add2(3) == 5);
  assert(add4(3) == 7);
}
```

