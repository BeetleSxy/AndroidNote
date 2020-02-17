package com.android.designpatterns.Builder;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * Builder：一个抽象接口，用来规范产品对象的各个组成成分的建造。
 */

public interface Builder {
    void buildPartOne();
    void buildPartTwo();
    Product getProduct();
}
