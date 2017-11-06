package com.kilogate.hello.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 日志过滤器（记录所有请求的 URI）
 *
 * @author fengquanwei
 * @create 2017/11/5 21:07
 **/
@WebFilter(filterName = "LoggingFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "logFileName", value = "log.txt"),
                @WebInitParam(name = "prefix", value = "URI: "),
        })
public class LoggingFilter implements Filter {
    private PrintWriter logger;
    private String prefix;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoggingFilter init start");

        prefix = filterConfig.getInitParameter("prefix");

        String logFileName = filterConfig.getInitParameter("logFileName");
        String appPath = filterConfig.getServletContext().getRealPath("/");

        try {
            logger = new PrintWriter(new File(appPath, logFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ServletException(e.getMessage());
        }

        System.out.println("LoggingFilter init end");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LoggingFilter doFilter start");
        logger.println(new Date() + " " + prefix + ((HttpServletRequest) servletRequest).getRequestURI());
        logger.flush();
        System.out.println("LoggingFilter doFilter end");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("LoggingFilter destroy start");
        if (logger != null) {
            logger.close();
        }
        System.out.println("LoggingFilter destroy end");
    }
}
