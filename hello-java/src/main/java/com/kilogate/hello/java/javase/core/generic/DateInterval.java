package com.kilogate.hello.java.javase.core.generic;

import java.util.Date;

/**
 * 翻译泛型方法
 *
 * @author fengquanwei
 * @create 2017/4/24 20:17
 **/
public class DateInterval extends Pair<Date> {
    @Override
    public void setFirst(Date first) {
        super.setFirst(first);
    }

    // 编译器生成的桥方法
//    public void setFirst(Object first) {
//        setFirst((Date) first);
//    }

    @Override
    public Date getFirst() {
        return (Date) super.getFirst().clone();
    }

    // 继承自 Pair 的方法
//    public Object getFirst()

    public static void main(String[] args) {
        // TODO 未知异常报错，回头再看
//        DateInterval interval = new DateInterval();
//        interval.setFirst(new Date());
//        Object first = interval.getFirst();
//        System.out.println(first);
    }
}
