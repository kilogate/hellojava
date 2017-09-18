package com.kilogate.hello.tomcat.connector2.core;

import com.kilogate.hello.tomcat.connector2.connector.HttpRequest;
import com.kilogate.hello.tomcat.connector2.connector.HttpRequestFacade;
import com.kilogate.hello.tomcat.connector2.connector.HttpResponse;
import com.kilogate.hello.tomcat.connector2.connector.HttpResponseFacade;
import com.kilogate.hello.tomcat.constant.Constants;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Servlet 处理器
 *
 * @author fengquanwei
 * @create 2017/8/28 14:12
 **/
public class ServletProcessor {
    public void process(HttpRequest request, HttpResponse response) {
        URLClassLoader loader = null;
        try {
            String repository = (new URL("file", null, new File(Constants.WEB_ROOT).getCanonicalPath() + File.separator)).toString();
            loader = new URLClassLoader(new URL[]{new URL(null, repository, (URLStreamHandler) null)});
        } catch (IOException e) {
            e.printStackTrace();
        }

        Class clazz = null;
        try {
            String uri = request.getRequestURI();
            String servletName = uri.substring(uri.lastIndexOf("/") + 1);
            clazz = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet = null;
        try {
            servlet = (Servlet) clazz.newInstance();
            servlet.service(new HttpRequestFacade(request), new HttpResponseFacade(response));
            response.finishResponse();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
