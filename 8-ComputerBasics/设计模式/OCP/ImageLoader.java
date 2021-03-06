package com.android.designpatterns.OCP;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Beetle_SXY on 2017/5/31.
 * 图片加载
 */

public class ImageLoader {

    //图片缓存
    ImageCache mImageCache = new MemoryCache();
    //定义一个线程池，线程池数量为cpu数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime()
            .availableProcessors());

    //注入缓存实现
    public void setImageCache(ImageCache cache){
        mImageCache = cache;
    }

    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap == null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        //图片没有缓存，提交到线程池中下载图片
        submitLoadRequest(url,imageView);

    }

    private void submitLoadRequest(final String url, final ImageView imageView) {
        imageView.setTag(url);

        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null)return;

                if (imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url,bitmap);
            }
        });
    }


    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
