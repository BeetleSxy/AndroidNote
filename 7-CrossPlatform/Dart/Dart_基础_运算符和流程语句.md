# 运算符

## 算数运算符

### 常用运算符

| 运算符 | 描述     |
| ------ | -------- |
| +      | 加       |
| -      | 减       |
| -var   | 负数     |
| *      | 乘       |
| /      | 除       |
| ~/     | 除并取整 |
| %      | 取模     |

### 自增自减

| 运算符 | 表达式                                   |
| ------ | ---------------------------------------- |
| ++var  | `var = var + 1` (表达式的值为 `var + 1`) |
| var++  | `var = var + 1` (表达式的值为 `var`)     |
| --var  | `var = var – 1` (表达式的值为 `var – 1`) |
| var--  | `var = var – 1` (表达式的值为 `var`)     |

## 关系运算符

| 运算符 | 表达式   |
| ------ | -------- |
| ==     | 相等     |
| !=     | 不等     |
| >      | 大于     |
| <      | 小于     |
| >=     | 大于等于 |
| <=     | 小于等于 |

## 类型判定运算符

| 运算符 | 表达式                           |
| ------ | -------------------------------- |
| as     | 类型转换（**也用作指定类前缀**） |
| is     | 如果对象是指定类型则返回 true    |
| is!    | 如果对象是指定类型则返回 false   |

## 赋值运算符

### 判空赋值

`??=` 等于空时候赋值

### 组合赋值

像 `+=` 这样的赋值运算符将算数运算符和赋值运算符组合在了一起

- =
- -=
- +=
- *=
- /=
- ～/=
- %=
- <<=
- `>>=`
- &=
- ^=
- |=

## 位运算符

| 运算符 | 描述                                        |
| ------ | ------------------------------------------- |
| &      | 按位与                                      |
| ｜     | 按位或                                      |
| ^      | 按位异或                                    |
| ~      | 按位取反（即将 “0” 变为 “1”，“1” 变为 “0”） |
| <<     | 位左移                                      |
| >>     | 位右移                                      |

## 逻辑运算符

!	     : 对表达式结果取反（即将 true 变为 false，false 变为 true）

||		: 逻辑或

&&	 : 逻辑与

## 条件表达式

*condition* **?** *expr1* **:** *expr2* ：如果条件为 true，执行表达式 1并返回执行结果，否则执行表达式 2 并返回执行结果。

*expr1* **??** *expr2* ：如果表达式 1 为非 null 则返回其值，否则执行表达式 2 并返回其值。

## 级联运算符

级联运算符（`..`）可以让你在同一个对象上连续调用多个对象的变量或方法。

# 流程语句

## if / else

```dart
if (isRaining()) {
  you.bringRainCoat();
} else if (isSnowing()) {
  you.wearJacket();
} else {
  car.putTopDown();
}
```

## for 循环

```dart
var message = StringBuffer('Dart is fun');
for (var i = 0; i < 5; i++) {
  message.write('!');
}
//for 循环中的闭包会自动捕获循环的 索引值 以避免 JavaScript 中一些常见的陷阱
var callbacks = [];
for (var i = 0; i < 2; i++) {
  callbacks.add(() => print(i));
}
callbacks.forEach((c) => c());
//Iterable 接口
candidates.forEach((candidate) => candidate.interview());
//List 和 Set 等实现了 Iterable 
var collection = [0, 1, 2];
for (var x in collection) {
  print(x); // 0 1 2
}
```

## while 和 do-while

```dart
while (!isDone()) {
  doSomething();
}
//do 循环则会先执行一遍循环体再判断条件
do {
  printLine();
} while (!atEndOfPage());
```

## switch 和 case

```dart
var command = 'OPEN';
switch (command) {
  case 'CLOSED':
    executeClosed();
    break;
  case 'PENDING':
    executePending();
    break;
  case 'APPROVED':
    executeApproved();
    break;
  case 'DENIED':
    executeDenied();
    break;
  case 'OPEN':
    executeOpen();
    break;
  default:
    executeUnknown();
}
//注意 break 穿透问题

//fall-through 
var command = 'CLOSED';
switch (command) {
  case 'CLOSED': // case 语句为空时的 fall-through 形式。
  case 'NOW_CLOSED':
    // case 条件值为 CLOSED 和 NOW_CLOSED 时均会执行该语句。
    executeNowClosed();
    break;
}

//配合标签
var command = 'CLOSED';
switch (command) {
  case 'CLOSED':
    executeClosed();
    continue nowClosed;
  // 继续执行标签为 nowClosed 的 case 子句。

  nowClosed:
  case 'NOW_CLOSED':
    // case 条件值为 CLOSED 和 NOW_CLOSED 时均会执行该语句。
    executeNowClosed();
    break;
}
```

## assert

断言

`assert` 的第一个参数可以是值为布尔值的任何表达式。如果表达式的值为 true，则断言成功，继续执行。如果表达式的值为 false，则断言失败，抛出一个 AssertionError 异常。

## break 和 continue

### break

中断循环

### continue

停止本次循环