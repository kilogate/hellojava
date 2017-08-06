package com.kilogate.hello.java.javase.jvm.classloader;

/**
 * 类加载器用法
 *
 * @author fengquanwei
 * @create 2017/8/5 15:12
 **/
public class ClassLoaderUsage {
    public static void main(String[] args) {
        // 引导类加载器没有对应的 ClassLoader 对象
        ClassLoader classLoader = String.class.getClassLoader();
        System.out.println(classLoader);

        ClassLoader classLoader1 = ClassLoaderUsage.class.getClassLoader();
        System.out.println(classLoader1);
    }
}
