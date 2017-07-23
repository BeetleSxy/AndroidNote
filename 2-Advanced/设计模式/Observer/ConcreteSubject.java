package com.android.designpatterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beetle_SXY on 2017/6/18.
 * 具体主题
 */

public class ConcreteSubject implements Subject {
    //存放观察者
    private List<Observers> list = new ArrayList<Observers>();

    @Override
    public void addObserver(Observers observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observers observer) {
        list.remove(observer);

    }

    @Override
    public void notifyObservers(String str) {
        for (Observers observer : list) {
            observer.update(str);
        }
    }
}
