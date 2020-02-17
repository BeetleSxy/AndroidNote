package com.android.designpatterns.Singleton;

import android.util.ArrayMap;

import java.util.Map;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * 容器
 */

public class Container {
    private static Map<String, Object> objectMap = new ArrayMap<>();

    public Container() {
        super();
    }

    public static void registerService(String key, Object instance) {
        if (!objectMap.containsKey(key)) {
            objectMap.put(key, instance);
        }
    }

    public static Object getService(String key) {
        return objectMap.get(key);
    }
}
