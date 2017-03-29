package com.kilogate.hello.designpattern.proxy.staticproxy;

/**
 * 委托类
 *
 * @author kilogate
 * @create 2017/3/28 16:12
 **/
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("Real Subject");
    }
}
