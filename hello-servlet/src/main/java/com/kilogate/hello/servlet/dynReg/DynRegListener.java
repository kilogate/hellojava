package com.kilogate.hello.servlet.dynReg;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

/**
 * 动态注册监听器
 *
 * @author fengquanwei
 * @create 2017/11/19 21:01
 **/
@WebListener
public class DynRegListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        Servlet firstServlet = null;
        try {
            firstServlet = servletContext.createServlet(FirstServlet.class);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        if (firstServlet != null && firstServlet instanceof FirstServlet) {
            ((FirstServlet) firstServlet).setName("Dynamically registered servlet");
        }

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("/firstServlet", firstServlet);
        dynamic.addMapping("/dynamic");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
