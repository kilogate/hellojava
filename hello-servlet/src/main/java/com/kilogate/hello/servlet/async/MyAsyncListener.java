package com.kilogate.hello.servlet.async;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * 我的异步监听器
 *
 * @author fengquanwei
 * @create 2017/11/18 22:02
 **/
// 不需要注解，不需要配置
public class MyAsyncListener implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println(asyncEvent.getAsyncContext() + " onComplete");
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        System.out.println(asyncEvent.getAsyncContext() + " onTimeout");
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        System.out.println(asyncEvent.getAsyncContext() + " onError");
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        System.out.println(asyncEvent.getAsyncContext() + " onStartAsync");
    }
}
