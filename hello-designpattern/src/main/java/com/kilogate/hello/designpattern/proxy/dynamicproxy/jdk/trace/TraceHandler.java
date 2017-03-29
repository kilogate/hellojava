package com.kilogate.hello.designpattern.proxy.dynamicproxy.jdk.trace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 跟踪方法调用的调用处理器
 *
 * @author kilogate
 * @create 2017/3/28 17:34
 **/
public class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 打印方法调用信息
        System.out.print(this.target);
        System.out.print("." + method.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) {
                    System.out.println(", ");
                }
            }
        }
        System.out.println(")");

        // 调用实际方法
        return method.invoke(this.target, args);
    }
}
