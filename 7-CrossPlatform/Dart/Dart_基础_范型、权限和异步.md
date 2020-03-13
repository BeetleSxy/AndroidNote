# 范型

Dart 中的范型使用方式与 Java 一致；

# 权限

`import` 参数只需要一个指向库的 URI。

```dart
import 'dart:html';//内置库
import 'package:test/test.dart';//三方库
```

## 指定库前缀

```dart
import 'package:lib1/lib1.dart';
import 'package:lib2/lib2.dart' as lib2;

// 使用 lib1 中的 Element。
Element element1 = Element();

// 使用 lib2 中的 Element。
lib2.Element element2 = lib2.Element();
```

## 导入部分库

与 Java 不同 Dart 中可以导入库中的某个类：

```dart
// 仅导入foo。
import 'package:lib1/lib1.dart' show foo;

// 导入除foo外的所有。
import 'package:lib2/lib2.dart' hide foo;
```

## 延迟加载库

- 减少 APP 的启动时间。
- 执行 A/B 测试，例如 尝试各种算法的 不同实现。
- 加载很少使用的功能，例如可选的屏幕和对话框。

```dart
import 'package:greetings/hello.dart' deferred as hello;

//使用时调用 loadLibrary() 函数来加载库
//loadLibrary() 可多次调用但只加载一次
Future greet() async {
  await hello.loadLibrary();
  hello.printGreeting();
}
```

# 异步

 使用 `async` 和 `await` 关键字实现异步编程；

```dart
//代表会异步执行这个方法
await lookUpVersion();

//调用异步方法是需要在函数上加上 async 关键字
Future checkVersion() async {
  var version = await lookUpVersion();
  // Do something with version
}
```



