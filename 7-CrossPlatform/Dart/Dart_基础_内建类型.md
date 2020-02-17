# Number

## int 

整数值不大于64位， 具体取决于平台。 在 Dart VM 上， 值的范围从 -2^63 到 2^63 - 1。Dart 被编译为 JavaScript 时，使用 JavaScript numbers, 值的范围从 -2^53 到 2^53 - 1。

## double

64位（双精度）浮点数，依据 IEEE 754 标准。

# String

Dart 字符串是一组 UTF-16 单元序列。 字符串通过单引号或者双引号创建。

## 内嵌表达式

字符串可以通过 `${`*`expression`*`}` 的方式内嵌表达式。 如果表达式是一个标识符，则 {} 可以省略。 在 Dart 中通过调用就对象的 `toString()` 方法来得到对象相应的字符串

```dart
var s = 'string interpolation';

assert('Dart has $s, which is very handy.' ==
    'Dart has string interpolation, ' +
        'which is very handy.');
assert('That deserves all caps. ' +
        '${s.toUpperCase()} is very handy!' ==
    'That deserves all caps. ' +
        'STRING INTERPOLATION is very handy!');
```

## 多行字符串

```dart
var s1 = '''
You can create
multi-line strings like this one.
''';

var s2 = """This is also a
multi-line string.""";
```

## 原始 raw

```dart
var s = r"In a raw string, even \n isn't special.";
```

# Boolean

Dart 使用 `bool` 类型表示布尔值。 Dart 只有字面量 `true` and `false` 是布尔类型， 这两个对象都是编译时常量

# List

几乎每种编程语言中最常见的集合可能是 *array* 或有序的对象集合。 在 Dart 中的 *Array* 就是 [List](https://api.dartlang.org/stable/dart-core/List-class.html) 对象， 通常称之为 *List* 。

Dart 中的 List 字面量非常像 JavaScript 中的 array 字面量。 下面是一个 Dart List 的示例：

**提示：** Dart 推断 `list` 的类型为 `List` 。 如果尝试将非整数对象添加到此 List 中， 则分析器或运行时会引发错误。

# Map

Map 是用来关联 keys 和 values 的对象:

```dart
var gifts = {
  // Key:    Value
  'first': 'partridge',
  'second': 'turtledoves',
  'fifth': 'golden rings'
};

var nobleGases = {
  2: 'helium',
  10: 'neon',
  18: 'argon',
};
```

以上 Map 对象也可以使用 Map 构造函数创建：

```dart
var gifts = Map();
gifts['first'] = 'partridge';
gifts['second'] = 'turtledoves';
gifts['fifth'] = 'golden rings';

var nobleGases = Map();
nobleGases[2] = 'helium';
nobleGases[10] = 'neon';
nobleGases[18] = 'argon';
```

## 改

```dart
var gifts = {'first': 'partridge'};
gifts['first'] = 'calling birds'; //set a key-value pair
```

## 增

```dart
var gifts = {'first': 'partridge'};
gifts['fourth'] = 'calling birds'; //Add a key-value pair
```

## 查

```dart
var gifts = {'first': 'partridge'};
assert(gifts['first'] == 'partridge');
```

**如果 Map 中不包含所要查找的 key，那么 Map 返回 null;**

使用 `.length` 函数获取当前 Map 中的 key-value 对数量:

```dart
var gifts = {'first': 'partridge'};
gifts['fourth'] = 'calling birds';
assert(gifts.length == 2);
```



# Set

在 Dart 中 Set 是一个元素唯一且无需的集合。 Dart 为 Set 提供了 Set 字面量和 Set 类型。

```dart
var halogens = {'fluorine', 'chlorine', 'bromine', 'iodine', 'astatine'};
```

要创建一个空集，使用前面带有类型参数的 `{}` ，或者将 `{}` 赋值给 `Set` 类型的变量：

```dart
var names = <String>{};
// Set<String> names = {}; // 这样也是可以的。
// var names = {}; // 这样会创建一个 Map ，而不是 Set 。
```

## 增

使用 `add()` 或 `addAll()` 为已有的 Set 添加元素：

```dart
var elements = <String>{};
elements.add('fluorine');
elements.addAll(halogens);
```

## 查

使用 `.length` 来获取 Set 中元素的个数：

```dart
var elements = <String>{};
elements.add('fluorine');
elements.addAll(halogens);
assert(elements.length == 5);
```

# Rune

用来表示字符串中的 UTF-32 编码字符。为了兼容32位 Unicode 值需要特殊语法支持；

# Symbol

一个 Symbol 对象表示 Dart 程序中声明的运算符或者标识符。 **你也许永远都不需要使用 Symbol** ，但要按名称引用标识符的 API 时， Symbol 就非常有用了。 因为代码压缩后会改变标识符的名称，但不会改变标识符的符号。 通过字面量 Symbol ，也就是标识符前面添加一个 `#` 号，来获取标识符的 Symbol 。

