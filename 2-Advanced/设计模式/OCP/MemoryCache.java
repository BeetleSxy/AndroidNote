package com.android.designpatterns.OCP;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Beetle_SXY on 2017/5/31.
 * 内存缓存MemoryCache
 */

public class MemoryCache implements ImageCache {

    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCache() {
        super();
    }

    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
    }
}
