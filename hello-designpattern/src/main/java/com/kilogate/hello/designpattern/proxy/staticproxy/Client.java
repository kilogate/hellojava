package com.kilogate.hello.designpattern.proxy.staticproxy;

/**
 * 客户端测试类
 * 静态代理实现中，一个委托类对应一个代理类，代理类在编译期间就已经确定
 *
 * @author kilogate
 * @create 2017/3/28 16:15
 **/
public class Client {
    public static void main(String[] args) {
        RealSubject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);
        proxy.request();
    }
}
