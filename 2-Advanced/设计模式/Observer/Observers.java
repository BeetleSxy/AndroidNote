package com.android.designpatterns.Observer;

/**
 * Created by Beetle_SXY on 2017/6/18.
 * 抽象观察者
 * 为所有的具体观察者定义一个接口，在得到主题通知时更新自己。
 */

public interface Observers {
    public void update(String str);
}
