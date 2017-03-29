package com.kilogate.hello.designpattern.proxy.staticproxy;

/**
 * 代理类
 *
 * @author kilogate
 * @create 2017/3/28 16:13
 **/
public class Proxy implements Subject {
    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        System.out.println("begin");
        subject.request();
        System.out.println("end");
    }
}
