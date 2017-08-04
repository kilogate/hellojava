package com.kilogate.hello.java.javase.core.innerclass;

import java.util.ArrayList;

/**
 * 内部类
 *
 * @author fengquanwei
 * @create 2017/6/13 14:52
 **/
public class InnerClass {
    public static void main(String[] args) {
        Outer outer = new Outer("outer");

        String innerName = outer.getInnerName();
        System.out.println(innerName);

        String localName = outer.getLocalName("outer");
        System.out.println(localName);

        Outer.Pair minmax = Outer.minmax(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println(minmax);
    }
}
