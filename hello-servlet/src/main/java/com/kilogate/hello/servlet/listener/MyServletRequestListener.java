package com.kilogate.hello.servlet.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * MyServletRequestListener
 *
 * @author fengquanwei
 * @create 2017/11/5 14:59
 **/
@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        System.out.println("ServletRequest(" + servletRequest + ") requestInitialized");

        servletRequest.setAttribute("start", System.nanoTime());
        servletRequest.setAttribute("start", System.nanoTime());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        System.out.println("ServletRequest(" + servletRequest + ") requestDestroyed");

        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        Long start = (Long) servletRequest.getAttribute("start");
        System.out.println("响应请求 " + uri + " 耗时：" + (System.nanoTime() - start) / 1000000 + "毫秒");

        servletRequest.removeAttribute("start");
    }
}
