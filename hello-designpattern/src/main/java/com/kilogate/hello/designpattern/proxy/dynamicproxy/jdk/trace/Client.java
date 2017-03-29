package com.kilogate.hello.designpattern.proxy.dynamicproxy.jdk.trace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * 使用代理对象对二分查找进行跟踪
 *
 * @author kilogate
 * @create 2017/3/28 17:32
 **/
public class Client {
    public static void main(String[] args) {
        // 构造 Integer 对象的代理对象数组
        Object[] elements = new Object[1000];
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            // 构造 Integer 对象的代理对象：使用默认构造器，实现 Comparable 接口，使用跟踪方法调用的调用处理器
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }

        // 使用二分法查找一个随机数，查找的过程中会调用 Integer 的 compareTo 方法，我们就是要跟踪这个方法
        int key = new Random().nextInt(elements.length) + 1;
        int result = Arrays.binarySearch(elements, key);

        if (result >= 0) {
            // Integer 的 toString 方法也会被跟踪
            System.out.println(elements[result]);
        }
    }
}
