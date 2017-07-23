package com.android.designpatterns.Singleton;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * 懒汉式(线程安全)
 */

public class LazySafety {

    private static LazySafety instance;

    private LazySafety() {
    }

    public static synchronized LazySafety getInstance() {
        if (instance == null) {
            instance = new LazySafety();
        }
        return instance;
    }
}
