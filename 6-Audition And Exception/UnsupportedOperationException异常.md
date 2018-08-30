# UnsupportedOperationException异常

## 出现
在调用 Arrays.asList() 方法时把一个数组转化成 List 列表时，对得到的 List列表进行add() 和 remove() 操作时出现 `java.lang.UnsupportedOperationException` 异常。把数组转化为 List 的操作代码如下

```java
String[] stringArray = ResourcesUtils.getStringArray(R.array.home_channel);
List<String> stringList = Arrays.asList(stringArray);
stringList.remove(0)
```

运行代码出现如下异常：

 	Caused by: java.lang.UnsupportedOperationException


## 为何
java.lang.UnsupportedOperationException 是指请求的方法不被支持的异常。在从 Arrays.asList() 转化过来的 List 的不支持 add() 和 remove() 方法，这是由于从 Arrays.asList() 返回的是返回 java.util.Arrays$ArrayList ，而不是 ArrayList。Arrays$ArrayList和ArrayList 都是继承 AbstractList，add() 和remove()等方法在AbstractList中默认 throw UnsupportedOperationException 而不做任何操作。ArrayList 重写这些方法对 List 进行操作，而 Arrays$ArrayList 却没有重写 add() 和 remove() 等方法，所以对从 Arrays.asList() 转化过来的 List 进行 add() 和 remove() 会出现 UnsupportedOperationException 异常。

一句话：使用 Arrays.asList() 方式转将数组转换为的集合，是**不可被更改**

## 解决

使用 Iterator 迭代器或者转化成 ArrayList；

```java
stringList.addAll(Arrays.asList(stringArray));
```