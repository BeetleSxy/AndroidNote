package com.android.designpatterns.Observer;

/**
 * Created by Beetle_SXY on 2017/6/18.
 * 抽象主题
 * 它把所有观察者对象的引用保存到一个聚集里，每个主题都可以有任何数量的观察者。抽象主题提供一个接口，可以增加和删除观察者对象。
 */

public interface Subject {
    public void addObserver(Observers observer);

    public void removeObserver(Observers observer);

    public void notifyObservers(String str);
}
