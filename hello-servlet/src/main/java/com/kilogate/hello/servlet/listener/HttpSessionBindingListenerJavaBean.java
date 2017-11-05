package com.kilogate.hello.servlet.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * HttpSessionBindingListenerJavaBean
 * 不需要注册到 web.xml
 *
 * @author fengquanwei
 * @create 2017/11/5 14:18
 **/
public class HttpSessionBindingListenerJavaBean implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        Object value = httpSessionBindingEvent.getValue();
        System.out.println("Session(" + httpSessionBindingEvent.getSession().getId() + ") valueBound: " + name + "=" + value + " ----- " + this);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        Object value = httpSessionBindingEvent.getValue();
        System.out.println("Session(" + httpSessionBindingEvent.getSession().getId() + ") valueUnbound: " + name + "=" + value + " ----- " + this);
    }
}
