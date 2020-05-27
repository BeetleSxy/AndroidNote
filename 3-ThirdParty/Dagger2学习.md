# 介绍

Dagger2 是 Google 维护的依赖注入框架，是 Spring 中的 IOC 思想是实现；

用来解决日渐庞大项目的，耦合性高，扩展差的问题；

# 使用

## 引入Dagger2 

Google 出品引入肯定很方便的了，我们平时使用的 Android 开发工具使用的 gradle 使用可以很方便的导入依赖只需要在项目的 build.gradl 中添加：

```
dependencies {
  
  ...
  
  api 'com.google.dagger:dagger:2.x'
  annotationProcessor 'com.google.dagger:dagger-compiler:2.x'
  
  ...

}
```

## 简单使用

```kotlin
class Engine @Inject constructor() {
    fun run() {
        Log.d("Engine", "Engine 引擎转起来了~~~ ")
    }
}

@Component
interface CarComponent {
    fun inject(car: Car)
}

class Car{
    @Inject lateinit var engine:Engine
    init {
        DaggerCarComponent.builder().build().inject(this)
    }
    fun run(){
        engine.run()
    }
}
```

是不是很简单，我相信你一看就会了，好的这次的讲解就到此结束了，再见。



要是 Dagger 就是如此简单的话肯定就不被大家吹捧了。

让我们先来看看，在上面这个Demo中做了什么：

我们会发现 `@Inject` 出现的频率很高，所以这个是一个重要的注解，在 Engine 类中能看到构造方法被注解标记，这里就表示我们的 Engine 是需要注入的类，而在 Car 中的 engine 变量也被标记了，变量类型正好就是 Engine 类型，我们可以合理推断出 engine 就是需要被注入的变量。而Engine 的的初始化过程我们不需要管，因为框架会帮我我们完成。

而 CarComponent 表示的就是我们的管理者，通过编译器自动生成的 DaggerCarComponent，完成注入过程，记住在这里我的注解仅仅是用来标记的，注入过程是通过 Component 实体类来完成的。而 DaggerCarComponent 也是随便生成的，是通过 @Component 注解来标记的，具体类的实现过程是通过编辑器来自动实现的。

如果我们要实现两个不同类的注入药怎么办呢？

```kotlin
class Apple @Inject constructor(val knife: Knife) {
    var color: String = "red"
}

class Knife @Inject constructor() {
    var name: String = "小刀"
}

@Component
interface FruitComponent {
    fun inject(start: Start)
}

class Start {
    @Inject
     lateinit var apple: Apple

    @Inject
     lateinit var knife: Knife

    init {
        DaggerFruitComponent.builder().build().inject(this)
    }

    fun run() {
        Log.d("Start", "${apple.color} - ${knife.name}")
    }
}
```

是不是稍微，些许，一丢丢的感觉到了方便呢？如果没有就继续向下看：

```kotlin
@Module
class SecondModule{
    @Provides
    fun provideString():String{
        return "SecondModule，provideString @Provides"
    }

}

@Component(modules = [SecondModule::class])
interface SecondComponent {
    fun inject(startone:StartOne)
}

class StartOne{
    @Inject
    lateinit var s: String
init {
    DaggerSecondComponent.builder().build().inject(this)
}
    fun run(){
        Log.d("StartOne",s)
    }
}
```

这里我们又多了一个标签 `@Module`它的作用就是用来将需要注入的变量或者类进行初始化，这样你将封装好的类进行注入，以后如果需要更换就只需在对应的Module中进行更改就行了，其他的类不需要进行更改，是不是很方便。

# 常用注解

## @Inject

## @Component

## @SubComponent

## @Module && @Provides

## @Named

## @Qualifier

## @Singleton

## @Scope

### 作用

@Scope`字面意思是范围，标志一个注入器/对象的使用范围，很多文章说成生命周期也是可以的。

### 使用

```java
@Documented
@Retention(RUNTIME)
@Scope
public @interface MyScope {}
```

MyScope 就是一个 Scope 注解，**Scope 注解只能标注目标类、@provide 方法和 Component。**Scope 注解要生效的话，需要同时标注在 Component 和提供依赖实例的 Module 或目标类上。**Module 中 provide 方法中的 Scope 注解必须和 与之绑定的 Component 的 Scope 注解一样，否则作用域不同会导致编译时会报错。**例如，CarModule 中 provide 方法的 Scope 是MyScope 的话，ManComponent 的 Scope 必须是 是 MyScope 这样作用域才会生效，而且不能是`@Singleton`或其他 Scope 注解，不然编译时 Dagger 2 会报错。

