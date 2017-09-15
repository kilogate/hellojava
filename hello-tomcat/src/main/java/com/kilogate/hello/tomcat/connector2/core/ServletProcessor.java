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
        String uri = request.getRequestURI();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet) myClass.newInstance();
            HttpRequestFacade requestFacade = new HttpRequestFacade(request);
            HttpResponseFacade responseFacade = new HttpResponseFacade(response);
            servlet.service(requestFacade, responseFacade);
            response.finishResponse();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
