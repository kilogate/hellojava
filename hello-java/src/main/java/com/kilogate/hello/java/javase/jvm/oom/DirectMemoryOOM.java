package com.kilogate.hello.java.javase.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存溢出
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * 测试无效
 *
 * @author fengquanwei
 * @create 2017/9/2 18:28
 **/
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            System.out.println(1);
            unsafe.allocateMemory(_1MB);
        }
    }
}
