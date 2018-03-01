# 修改编译后APK文件名问题

## 初遇

gradle 打包，自定义 apk 名称代码报错:

	（Cannot set the value of read-only property ‘outputFile’ ） 
	Error:(56, 0) Cannot set the value of read-only property ‘outputFile’ for ApkVariantOutputImpl_Decorated{apkData=Main{type=MAIN, fullName=debug, filters=[]}} of type com.android.build.gradle.internal.api.ApkVariantOutputImpl.

## 为何

Android Studio 的自带 Gradle 版本是 4.1，插件版本是 3.0.0，所以如果你使用的是老版本，就会出现一些小的兼容问题。

## 解决

旧代码：

            applicationVariants.all { variant ->
                variant.outputs.each { output ->
                    if (output.outputFile != null && output.outputFile.name.endsWith('.apk')
                            && 'release'.equals(variant.buildType.name)) {
                        def apkFile = new File(
                                output.outputFile.getParent(),
                                "红动中国_${variant.flavorName}_v${variant.versionName}_${variant.versionCode}_${buildTime()}.apk")
                        output.outputFile = apkFile
                    }
                }
            }
新代码：

            android.applicationVariants.all { variant ->
                variant.outputs.all {
                    outputFileName = "红动中国_${variant.flavorName}_v${variant.versionName}_${variant.versionCode}_${buildTime()}.apk"
                }
            }



