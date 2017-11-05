package com.kilogate.hello.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * MyHttpSessionAttributeListener
 *
 * @author fengquanwei
 * @create 2017/11/5 13:23
 **/
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        Object value = httpSessionBindingEvent.getValue();
        System.out.println("Session(" + httpSessionBindingEvent.getSession().getId() + ") attributeAdded: " + name + "=" + value);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        Object value = httpSessionBindingEvent.getValue();
        System.out.println("Session(" + httpSessionBindingEvent.getSession().getId() + ") attributeRemoved: " + name + "=" + value);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        String name = httpSessionBindingEvent.getName();
        Object value = httpSessionBindingEvent.getValue();
        System.out.println("Session(" + httpSessionBindingEvent.getSession().getId() + ") attributeReplaced: " + name + "=" + value);
    }
}
