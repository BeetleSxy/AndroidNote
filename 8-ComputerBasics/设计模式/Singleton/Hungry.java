package com.android.designpatterns.Singleton;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * 饿汉
 */

public class Hungry {
    private static Hungry instance = new Hungry();

    private Hungry() {
    }

    public static Hungry getInstance() {
        return instance;
    }
}
