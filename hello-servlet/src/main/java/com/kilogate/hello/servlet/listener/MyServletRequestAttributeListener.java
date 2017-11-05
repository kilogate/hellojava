package com.kilogate.hello.servlet.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * MyServletRequestAttributeListener
 *
 * @author fengquanwei
 * @create 2017/11/5 15:12
 **/
@WebListener
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        String name = servletRequestAttributeEvent.getName();
        Object value = servletRequestAttributeEvent.getValue();
        System.out.println("ServletRequest(" + servletRequestAttributeEvent.getServletRequest() + ") attributeAdded: " + name + "=" + value);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        String name = servletRequestAttributeEvent.getName();
        Object value = servletRequestAttributeEvent.getValue();
        System.out.println("ServletRequest(" + servletRequestAttributeEvent.getServletRequest() + ") attributeRemoved: " + name + "=" + value);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        String name = servletRequestAttributeEvent.getName();
        Object value = servletRequestAttributeEvent.getValue();
        System.out.println("ServletRequest(" + servletRequestAttributeEvent.getServletRequest() + ") attributeReplaced: " + name + "=" + value);
    }
}
