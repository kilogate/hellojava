package com.kilogate.hello.java.javase.core.generic;

import java.util.Date;

/**
 * 桥方法
 */
public class DatePair extends Pair<Date> {
    public DatePair(Date first, Date second) {
        super(first, second);
    }

    // DatePair 的 setSecond 方法
    // 类型擦除之后与父类方法一样，与多态发生了冲突
    // 为此编译器生成了一个桥方法（bridge method）
    public void setSecond(Date second) {
        super.setSecond(second);
    }

    // DatePair 的父类 Pair 的 setSecond 方法
//    public void setSecond(T second) {
//        this.second = second;
//    }

    // 编译器生成的桥方法
//    public void setSecond(Object second) {
//        setSecond((Date) second);
//    }

    // DatePair 的 getSecond 方法
    public Date getSecond() {
        return (Date) super.getSecond().clone();
    }

    // DatePair 的父类 Pair 的 getSecond 方法
//    public Object getSecond(){
//        return second;
//    }

    // 两个 getSecond 方法，签名一样，对于编译器来是不合法的
    // 虚拟机用参数签名和返回类型确定一个方法，虚拟机可以正确处理
}
