首先我们在需要应用模块中的`build.gradle`中添加`dataBinding`

```gradle
android {
    ...
    dataBinding {
        enabled = true
    }
}
```

**注意：即使应用模块不直接使用数据绑定，也必须为依赖于使用数据绑定的库的应用模块配置数据绑定。**

