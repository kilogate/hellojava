package com.kilogate.hello.designpattern.proxy.dynamicproxy.jdk.test;

/**
 * 接口一具体实现
 *
 * @author fengquanwei
 * @create 2017/4/20 10:15
 **/
public class ServiceImpl1 implements Service1 {
    @Override
    public void method1() {
        System.out.println("ServiceImpl1 implements Service1");
    }
}
