package com.kilogate.hello.designpattern.proxy.dynamicproxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.*;

/**
 * JDK 动态代理实现原理
 *
 * @author fengquanwei
 * @create 2017/4/20 11:14
 **/
public class JdkDynamicProxy {
    public interface MyService {
        void myMethod();
    }

    static class MyServiceImpl implements MyService {
        public void myMethod() {
            System.out.println("ServiceImpl implements Service");
        }
    }

    static class MyInvocationHandler implements InvocationHandler {
        private Object target;

        public MyInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("-------------------- before --------------------");
            Object result = method.invoke(target, args);
            System.out.println("-------------------- after  --------------------");
            return result;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        // 生成 $Proxy0 的 class 文件
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        // 获取动态代理类
//        Class<?> proxyClass = Proxy.getProxyClass(MyService.class.getClassLoader(), MyService.class);
//        // 获取动态代理类的构造函数
//        Constructor<?> proxyClassConstructor = proxyClass.getConstructor(InvocationHandler.class);
//        // 创建动态代理类实例
//        MyService proxy = (MyService) proxyClassConstructor.newInstance(new MyInvocationHandler(new MyServiceImpl()));
//        // 通过代理类调用目标方法
//        proxy.myMethod();

        // 保存生成的代理类文件到本地
        byte[] classBytes = ProxyGenerator.generateProxyClass("$Proxy1", MyServiceImpl.class.getInterfaces());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("/tmp/$Proxy1.class");
            out.write(classBytes);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
