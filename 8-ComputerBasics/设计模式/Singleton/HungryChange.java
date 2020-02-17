package com.android.designpatterns.Singleton;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * 饿汉(变种)
 */

public class HungryChange {
    private static HungryChange instance = null;

    static {
        instance = new HungryChange();
    }

    private HungryChange() {
    }

    public static HungryChange getInstance() {
        return instance;
    }
}
