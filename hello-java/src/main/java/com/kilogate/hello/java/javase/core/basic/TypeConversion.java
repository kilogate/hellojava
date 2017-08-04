package com.kilogate.hello.java.javase.core.basic;

/**
 * 类型转换
 *
 * @author fengquanwei
 * @create 2017/6/12 20:19
 **/
public class TypeConversion {
    public static void main(String[] args) {
        // 隐式类型转换
        int i = 123;
        long l = i;
        System.out.println(l);

        // 强制类型转换
        double d = 9.9;
        i = (int) d;
        System.out.println(i);
    }
}
