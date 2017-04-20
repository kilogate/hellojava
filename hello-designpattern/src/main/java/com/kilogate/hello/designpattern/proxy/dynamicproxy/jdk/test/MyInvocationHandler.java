package com.kilogate.hello.designpattern.proxy.dynamicproxy.jdk.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author kilogate
 * @create 2017/3/28 16:21
 **/
public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 获取代理对象
     */
    public Object getProxy() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?>[] interfaces = this.target.getClass().getInterfaces();
        return Proxy.newProxyInstance(loader, interfaces, this);
    }

    /**
     * 代理方法调用
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-------------------- before --------------------");

        Object result = method.invoke(this.target, args);

        System.out.println("-------------------- after  --------------------");

        return result;
    }
}
