package com.android.designpatterns.OCP;

import android.graphics.Bitmap;

/**
 * Created by Beetle_SXY on 2017/6/1.
 * 双缓存类
 */

public class DoudleCache implements ImageCache {
    ImageCache mMemoryCache = new MemoryCache();
    ImageCache mDiskCache = new DiskCache();

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            return mDiskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
