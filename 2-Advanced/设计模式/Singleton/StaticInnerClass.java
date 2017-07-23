package com.android.designpatterns.Singleton;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * 静态内部类
 */

public class StaticInnerClass {
    private static class SingletonHolder {
        private static final StaticInnerClass INSTANCE = new StaticInnerClass();
    }

    private StaticInnerClass() {
    }

    public static final StaticInnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
