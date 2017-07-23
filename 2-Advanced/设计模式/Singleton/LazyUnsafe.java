package com.android.designpatterns.Singleton;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * 懒汉式(线程不安全)
 */

public class LazyUnsafe {

    private static LazyUnsafe instance;

    private LazyUnsafe() {
    }

    public static LazyUnsafe getInstance() {
        if (instance == null) {
            instance = new LazyUnsafe();
        }
        return instance;
    }
}
