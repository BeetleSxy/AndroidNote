package com.android.designpatterns.SRP;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Beetle_SXY on 2017/5/31.
 * 图片缓存
 */

public class ImageCache {
    //图片缓存Lru
    LruCache<String, Bitmap> mLruCache;

    public ImageCache() {
    }

    private void initImageCache() {
        //计算可使用最大内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //使用1/4
        int cacheSize = maxMemory / 4;

        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    /**
     *
     * @param s url
     * @param b bitmap
     */
    public void put(String s,Bitmap b) {
        mLruCache.put(s,b);
    }

    /**
     *
     * @param s url
     * @return
     */
    public Bitmap get(String s){
        return mLruCache.get(s);
    }
}
