package com.kilogate.hello.designpattern.proxy.dynamicproxy.jdk.test;

/**
 * 接口二具体实现
 *
 * @author fengquanwei
 * @create 2017/4/20 10:16
 **/
public class ServiceImpl2 implements Service2 {
    @Override
    public void method2() {
        System.out.println("ServiceImpl2 implements Service2");
    }
}
