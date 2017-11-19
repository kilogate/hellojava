package com.kilogate.hello.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * AAAFilter
 *
 * @author fengquanwei
 * @create 2017/11/5 21:21
 **/
@WebFilter(filterName = "AAA", urlPatterns = "/*", asyncSupported = true) // 配合测试异步处理
public class AAAFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AAAFilter doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
