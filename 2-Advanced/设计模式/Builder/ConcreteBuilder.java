package com.android.designpatterns.Builder;

/**
 * Created by Beetle_SXY on 2017/6/17.
 * ConcreteBuilder：实现Builder接口，针对不同的商业逻辑，具体化复杂对象的各部分的创建，在建造过程完成后，提供产品的实例。
 */

public class ConcreteBuilder implements Builder{
    Part partOne, partTwo;

    public void buildPartOne() {
        //具体构建代码
    }


    public void buildPartTwo() {
        //具体构建代码
    }


    public Product getProduct() {
        //返回最后组装的产品
        return null;
    }


}
