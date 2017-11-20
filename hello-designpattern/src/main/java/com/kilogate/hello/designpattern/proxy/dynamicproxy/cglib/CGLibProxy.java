package com.kilogate.hello.designpattern.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib 代理
 *
 * @author fengquanwei
 * @create 2017/11/20 16:27
 **/
public class CGLibProxy implements MethodInterceptor {
    private static CGLibProxy instance = new CGLibProxy();

    private CGLibProxy() {
    }

    public static CGLibProxy getInstance() {
        return instance;
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Enhancer.create(clazz, this);
    }

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("intercept before");

        Object result = methodProxy.invokeSuper(object, args);

        System.out.println("intercept after");
        return result;
    }
}
