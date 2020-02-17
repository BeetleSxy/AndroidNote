package com.android.designpatterns.OCP;

import android.graphics.Bitmap;

/**
 * Created by Beetle_SXY on 2017/6/1.
 * 图片缓存接口
 */

public interface ImageCache {

    Bitmap get(String url);

    void put(String url, Bitmap bitmap);
}
