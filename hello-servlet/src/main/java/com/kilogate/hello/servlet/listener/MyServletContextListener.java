package com.kilogate.hello.servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * MyServletContextListener
 *
 * @author fengquanwei
 * @create 2017/11/5 12:35
 **/
@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        System.out.println("ServletContext(" + servletContext + ") contextInitialized");

        servletContext.setAttribute("welcome", "欢迎访问");
        servletContext.setAttribute("welcome", "欢迎访问主页");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        System.out.println("ServletContext(" + servletContext + ") contextDestroyed");

        servletContext.removeAttribute("welcome");
    }
}
