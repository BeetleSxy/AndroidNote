package com.android.designpatterns.OCP;

import android.graphics.Bitmap;

/**
 * Created by Beetle_SXY on 2017/6/1.
 * sd卡
 */

public class DiskCache implements ImageCache {

    @Override
    public Bitmap get(String url) {
        /*
        从本地文件读取文件
         */
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        /*
        写入本地
         */
    }
}
