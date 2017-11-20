package com.kilogate.hello.designpattern.proxy.dynamicproxy.cglib;

/**
 * HellpImpl
 *
 * @author fengquanwei
 * @create 2017/11/20 16:32
 **/
public class HellpImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("hello!");
    }
}
