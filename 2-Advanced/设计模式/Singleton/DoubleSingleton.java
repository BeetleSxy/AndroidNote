package com.android.designpatterns.Singleton;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * 双重校验锁
 */

public class DoubleSingleton {
    private volatile static DoubleSingleton singleton;

    private DoubleSingleton() {
    }

    public static DoubleSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DoubleSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleSingleton();
                }
            }
        }
        return singleton;
    }
}
