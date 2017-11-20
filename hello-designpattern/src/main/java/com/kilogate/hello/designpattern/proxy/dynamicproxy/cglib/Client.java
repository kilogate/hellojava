package com.kilogate.hello.designpattern.proxy.dynamicproxy.cglib;

/**
 * 测试 CGLIB
 *
 * @author fengquanwei
 * @create 2017/11/20 16:32
 **/
public class Client {
    public static void main(String[] args) {
        HellpImpl proxy = CGLibProxy.getInstance().getProxy(HellpImpl.class);
        proxy.sayHello();
    }
}