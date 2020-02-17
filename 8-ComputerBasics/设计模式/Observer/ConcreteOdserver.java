package com.android.designpatterns.Observer;

/**
 * Created by Beetle_SXY on 2017/6/18.
 * 具体观察者
 * 实现抽象观察者角色所要求的更新接口，以便使本身的状态与主题状态协调。
 */

public class ConcreteOdserver implements Observers {
    @Override
    public void update(String str) {
        System.out.println(str);
    }
}
