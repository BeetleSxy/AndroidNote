package com.android.designpatterns.Builder;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * 入口
 */

public class Start {

    public static void main(String[] args){
        ConcreteBuilder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        //开始各部分建造　
        director.construct();
        Product product = builder.getProduct();
    }

}
