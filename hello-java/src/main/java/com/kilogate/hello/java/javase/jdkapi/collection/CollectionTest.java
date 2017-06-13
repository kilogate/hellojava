package com.kilogate.hello.java.javase.jdkapi.collection;

import java.util.*;

/**
 * 集合测试
 *
 * @author fengquanwei
 * @create 2017/4/25 20:26
 **/
public class CollectionTest {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);
        integers.add(9);

        int i = Collections.binarySearch(integers, 5);
        System.out.println(integers.get(i));
        integers.add(-i - 1, 4);

        System.out.println(integers);
    }
}
