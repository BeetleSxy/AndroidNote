package com.android.designpatterns.Observer;

/**
 * Created by Beetle_SXY on 2017/6/18.
 * 测试类
 */

public class Start {

    public static void main(String[] args) {
        //一个主题
        ConcreteSubject eatSubject = new ConcreteSubject();
        //两个观察者
        ConcreteOdserver personOne = new ConcreteOdserver();
        ConcreteOdserver personTwo = new ConcreteOdserver();
        //观察者订阅主题
        eatSubject.addObserver(personOne);
        eatSubject.addObserver(personTwo);

        //通知开饭了
        eatSubject.notifyObservers("开饭啦");
    }
}
