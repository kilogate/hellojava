package com.kilogate.hello.designpattern.proxy.dynamicproxy.jdk.test;

/**
 * 具体业务实现
 *
 * @author kilogate
 * @create 2017/3/28 16:20
 **/
public class UserServiceImpl implements Service {
    @Override
    public void add() {
        System.out.println("UserServiceImpl.add()");
    }
}
