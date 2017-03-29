package com.kilogate.hello.designpattern.proxy.dynamicproxy.jdk.test;

/**
 * 测试 JDK 动态代理
 * 相比于静态代理，动态代理可以很方便地对委托类的方法进行统一处理，如添加方法调用次数，添加日志功能等；
 * 通过反射类 Proxy 和回调接口 InvocationHandler 实现的 JDK 动态代理要求委托类必须实现一个接口，但事实上并不是所有类都有接口，对于没有实现接口的类，无法使用 JDK 动态代理
 *
 * @author kilogate
 * @create 2017/3/28 16:25
 **/
public class Client {
    public static void main(String[] args) {
        Service service = new UserServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(service);
        Service proxy = (Service) handler.getProxy();
        proxy.add();
    }
}
