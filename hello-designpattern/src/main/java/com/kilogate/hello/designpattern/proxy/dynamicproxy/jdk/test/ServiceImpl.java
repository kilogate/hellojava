package com.kilogate.hello.designpattern.proxy.dynamicproxy.jdk.test;

/**
 * 接口具体实现
 *
 * @author fengquanwei
 * @create 2017/4/20 10:53
 **/
public class ServiceImpl implements Service1, Service2 {
    @Override
    public void method1() {
        System.out.println("ServiceImpl implements Service1");
    }

    @Override
    public void method2() {
        System.out.println("ServiceImpl implements Service2");
    }
}
