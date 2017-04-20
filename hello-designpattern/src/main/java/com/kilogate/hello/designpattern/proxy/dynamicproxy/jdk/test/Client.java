package com.kilogate.hello.designpattern.proxy.dynamicproxy.jdk.test;

/**
 * 测试 JDK 动态代理
 * 相比于静态代理，动态代理可以很方便地对委托类的方法进行统一处理，如添加方法调用次数，添加日志功能等；
 * 通过反射类 Proxy 和回调接口 InvocationHandler 实现的 JDK 动态代理要求委托类必须实现一个接口，但事实上并不是所有类都有接口，对于没有实现接口的类，无法使用 JDK 动态代理
 *
 * 生成的代理类会实现接口的方法以及 Object 对象的 hashCode，equals，toString 方法
 *
 * @author kilogate
 * @create 2017/3/28 16:25
 **/
public class Client {
    public static void main(String[] args) {
//        Service1 service1 = new ServiceImpl1();
//        MyInvocationHandler invocationHandler1 = new MyInvocationHandler(service1);
//        Service1 proxy1 = (Service1) invocationHandler1.getProxy();
//        proxy1.method1();
//
//        Service2 service2 = new ServiceImpl2();
//        MyInvocationHandler invocationHandler2 = new MyInvocationHandler(service2);
//        Service2 proxy2 = (Service2) invocationHandler2.getProxy();
//        proxy2.method2();

        Service2 service = new ServiceImpl();
        MyInvocationHandler invocationHandler = new MyInvocationHandler(service);
        Service2 proxy = (Service2) invocationHandler.getProxy();
        proxy.method2();
    }
}
