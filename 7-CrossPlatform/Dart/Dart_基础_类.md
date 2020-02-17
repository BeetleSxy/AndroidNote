Dart 只能单继承，但是可以多层继承。

# 调用成员变量

操作符几乎和别的语言类似，提个比较特殊的赋值操作符 ??= 和 ?.操作符：

```dart
var a = 1;
var b;
b ?? = a; // 如果 b 的值是 null 则将 a 赋值给 b，否则保持不变
var c = size?.x; // 如果 size 为 null 则返回 null，否则返回 size.a 的值
```

# 构造函数

## 使用

通过 *构造函数* 创建对象。 构造函数的名字可以是 `*ClassName*` 或者 `*ClassName*.*identifier*`。例如， 以下代码使用 a 和 `a.instance()` 构造函数创建 `Point` 对象：

```dart
var a1 = a(2, 2);
var a2 = a.instance({'x': 1, 'y': 2});
//在 Dart 2 中 new 关键字变成了可选的。
var a1 = new a(2, 2);
var a2 = new a.instance({'x': 1, 'y': 2});
```

### 常量构造函数

```dart
var a = const ImmutablePoint(1, 1);
var b = const ImmutablePoint(1, 1);

assert(identical(a, b)); // 它们是同一个实例。
```

在 *常量上下文* 中， 构造函数或者字面量前的 `const` 可以省略。 例如，下面代码创建了一个 const 类型的 map 对象：

```dart
// 这里有很多的 const 关键字。
const pointAndLine = const {
  'point': const [const ImmutablePoint(0, 0)],
  'line': const [const ImmutablePoint(1, 10), const ImmutablePoint(-2, 11)],
};

// 仅有一个 const ，由该 const 建立常量上下文。
const pointAndLine = {
  'point': [ImmutablePoint(0, 0)],
  'line': [ImmutablePoint(1, 10), ImmutablePoint(-2, 11)],
};
/**************************************************************************/
var a = const ImmutablePoint(1, 1); // 创建一个常量对象
var b = ImmutablePoint(1, 1); // 创建一个非常量对象

assert(!identical(a, b)); // 两者不是同一个实例!
```

## 构造函数

```dart
class Point {
  num x, y;

  Point(num x, num y) {
    // 还有更好的方式来实现下面代码，敬请关注。
    this.x = x;
    this.y = y;
  }
}
//两种创建方式一样
class Point {
  num x, y;

  // 在构造函数体执行前，
  // 语法糖已经设置了变量 x 和 y。
  Point(this.x, this.y);
}
```

### 默认构造函数

默认构造函数没有参数并会调用父类的无参构造函数。

### 构造函数不被继承

子类不会继承父类的构造函数。 子类不声明构造函数，那么它就只有默认构造函数 (匿名，没有参数) 。

### 命名构造函数

一个类可以实现多个构造函数：

```dart
class Point {
  num x, y;

  Point(this.x, this.y);

  // 命名构造函数
  Point.origin() {
    x = 0;
    y = 0;
  }
}
```

**切记，构造函数不能够被继承， 这意味着父类的命名构造函数不会被子类继承。 如果希望使用父类中定义的命名构造函数创建子类， 就必须在子类中实现该构造函数。**

### 初始化列表

```dart
// 在构造函数体执行之前，
// 通过初始列表设置实例变量。
Point.fromJson(Map<String, num> json)
    : x = json['x'],
      y = json['y'] {
  print('In Point.fromJson(): ($x, $y)');
}

Point.withAssert(this.x, this.y) : assert(x >= 0) {
  print('In Point.withAssert(): ($x, $y)');
}
```

**警告：** 初始化程序的右侧无法访问 `this` 。

### 重定向构造函数

```dart
class Point {
  num x, y;

  // 类的主构造函数。
  Point(this.x, this.y);

  // 指向主构造函数
  Point.alongXAxis(num x) : this(x, 0);
}
```

### 常量构造函数

```dart
class ImmutablePoint {
  static final ImmutablePoint origin =
      const ImmutablePoint(0, 0);

  final num x, y;

  const ImmutablePoint(this.x, this.y);
}
```

### 工厂构造函数

当执行构造函数并不总是创建这个类的一个新实例时，则使用 `factory` 关键字。 例如，一个工厂构造函数可能会返回一个 cache 中的实例， 或者可能返回一个子类的实例。

```dart
class Logger {
  final String name;
  bool mute = false;

  // 从命名的 _ 可以知，
  // _cache 是私有属性。
  static final Map<String, Logger> _cache =
      <String, Logger>{};

  factory Logger(String name) {
    if (_cache.containsKey(name)) {
      return _cache[name];
    } else {
      final logger = Logger._internal(name);
      _cache[name] = logger;
      return logger;
    }
  }

  Logger._internal(this.name);

  void log(String msg) {
    if (!mute) print(msg);
  }
}
//工厂构造函的调用方式与其他构造函数一样：
var logger = Logger('UI');
logger.log('Button clicked');
```

**提示：** 工厂构造函数无法访问 this。

# 获取对象的类型

使用对象的 `runtimeType` 属性， 可以在运行时获取对象的类型， `runtimeType` 属性回返回一个 Type 对象。

# 实例变量

```dart
class Point {
  num x; // 声明示例变量 x，初始值为 null 。
  num y; // 声明示例变量 y，初始值为 null 。
  num z = 0; // 声明示例变量 z，初始值为 0 。
}
```

**所有实例变量都生成隐式 *getter* 方法。 非 final 的实例变量同样会生成隐式 *setter* 方法。**

# 方法

## 实例方法

```dart
import 'dart:math';

class Point {
  num x, y;

  Point(this.x, this.y);

  num distanceTo(Point other) {
    var dx = x - other.x;
    var dy = y - other.y;
    return sqrt(dx * dx + dy * dy);
  }
}
```

## Getter 和 Setter

Dart 会为变量提供隐形 Getter 和 Setter ，但是也可以使用 `get` 和 `set` 关键字实现 Getter 和 Setter ：

```dart
class Rectangle {
  num left, top, width, height;

  Rectangle(this.left, this.top, this.width, this.height);

  // 定义两个计算属性： right 和 bottom。
  num get right => left + width;
  set right(num value) => left = value - width;
  num get bottom => top + height;
  set bottom(num value) => top = value - height;
}

void main() {
  var rect = Rectangle(3, 4, 20, 15);
  assert(rect.left == 3);
  rect.right = 12;
  assert(rect.left == -8);
}
```

**提示：** 类似 (++) 之类操作符不管是否定义了 getter 方法，都能够正确的执行。 为了避免一些问题，操作符只调用一次 getter 方法， 然后把值保存到一个临时的变量中。

## 抽象方法

同 Java 中的实现：

```dart
abstract class Doer {
  // 定义实例变量和方法 ...

  void doSomething(); // 定义一个抽象方法。
}

class EffectiveDoer extends Doer {
  void doSomething() {
    // 提供方法实现，所以这里的方法就不是抽象方法了...
  }
}
```

# 抽象类

使用 `abstract` 修饰符来定义 *抽象类* — 抽象类不能实例化。 抽象类通常用来定义接口，以及部分实现。 如果希望抽象类能够被实例化，那么可以通过定义一个 工厂构造函数 来实现。

```dart
// 这个类被定义为抽象类，
// 所以不能被实例化。
abstract class AbstractContainer {
  // 定义构造行数，字段，方法...

  void updateChildren(); // 抽象方法。
}
```

# 隐式接口

同 Java 中的接口：

```dart
// person 类。 隐式接口里面包含了 greet() 方法声明。
class Person {
  // 包含在接口里，但只在当前库中可见。
  final _name;

  // 不包含在接口里，因为这是一个构造函数。
  Person(this._name);

  // 包含在接口里。
  String greet(String who) => 'Hello, $who. I am $_name.';
}

// person 接口的实现。
class Impostor implements Person {
  get _name => '';

  String greet(String who) => 'Hi $who. Do you know who I am?';
}

String greetBob(Person person) => person.greet('Bob');

void main() {
  print(greetBob(Person('Kathy')));
  print(greetBob(Impostor()));
}

//实现多接口
class Point implements Comparable, Location {...}
```

# 扩展类（继承）

同 Java ：

```dart
class Television {
  void turnOn() {
    _illuminateDisplay();
    _activateIrSensor();
  }
  // ···
}

class SmartTelevision extends Television {
  void turnOn() {
    super.turnOn();
    _bootNetworkInterface();
    _initializeMemory();
    _upgradeApps();
  }
  // ···
}
```

## 重写类成员

同 Java：

```dart
class SmartTelevision extends Television {
  @override
  void turnOn() {...}
  // ···
}
```

## 重写运算符

这个就很神器，用代码来说明吧，下面示例演示一个类重写 `+` 和 `-` 操作符

```dart
class Vector {
  final int x, y;

  Vector(this.x, this.y);

  Vector operator +(Vector v) => Vector(x + v.x, y + v.y);
  Vector operator -(Vector v) => Vector(x - v.x, y - v.y);

  // 运算符 == 和 hashCode 部分没有列出。 有关详情，请参考下面的注释。
  // ···
}

void main() {
  final v = Vector(2, 3);
  final w = Vector(2, 2);

  assert(v + w == Vector(4, 5));
  assert(v - w == Vector(0, 1));
}
```

### 可被重写的运算符

| `<`  | `+`  | `|`  | `[]`  |
| ---- | ---- | ---- | ----- |
| `>`  | `/`  | `^`  | `[]=` |
| `<=` | `~/` | `&`  | `~`   |
| `>=` | `*`  | `<<` | `==`  |
| `–`  | `%`  | `>>` |       |

**提示：** 你可能会被提示 `!=` 运算符为非可重载运算符。 因为 `e1 != e2` 表达式仅仅是 `!(e1 == e2)` 的语法糖。

## noSuchMethod()

当代码尝试使用不存在的方法或实例变量时， 通过重写 `noSuchMethod()` 方法，来实现检测和应对处理：

```dart
class A {
  // 如果不重写 noSuchMethod，访问
  // 不存在的实例变量时会导致 NoSuchMethodError 错误。
  @override
  void noSuchMethod(Invocation invocation) {
    print('You tried to use a non-existent member: ' +
        '${invocation.memberName}');
  }
}
```

#  枚举类型

同 Java：

```dart
enum Color { red, green, blue }
//枚举中存在一个值 index 返回值为枚举类型定义中的位置 
assert(Color.red.index == 0);
assert(Color.green.index == 1);
assert(Color.blue.index == 2);
//使用 values  获取所有枚举值列表（ list ）
List<Color> colors = Color.values;
assert(colors[2] == Color.blue);
//可以在 switch 语句 中使用枚举
var aColor = Color.blue;
switch (aColor) {
  case Color.red:
    print('Red as roses!');
    break;
  case Color.green:
    print('Green as grass!');
    break;
  default: // 没有这个，会看到一个警告。
    print(aColor); // 'Color.blue'
}
```

# 混入（Mixin）

这个理解起来不算难，我们写一个例子：

```dart
class S {
  a() {print("S.a");}
}

class A {
  a(){print("A.a");}
  b(){print("A.b");}
}

class B {
  a(){print("B.a");}
  b(){print("B.b");}
  c(){print("B.c ");}
}

class T = B with A, S;

main(List<String> args) {
  T t = new T();
  t.a();
  t.b();
  t.c();
}

//输出
S.a
A.b
B.c 
```

# 静态

Dart 和 Java 中的静态是一样的；

