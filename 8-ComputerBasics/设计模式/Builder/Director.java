package com.android.designpatterns.Builder;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * Director：指导者，调用具体建造者来创建复杂对象的各个部分，不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建。
 */

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildPartOne();
        builder.buildPartTwo();
    }
}
