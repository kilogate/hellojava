package com.kilogate.hello.tomcat.connector2.core;

import com.kilogate.hello.tomcat.connector2.connector.HttpResponse;
import com.kilogate.hello.tomcat.connector2.connector.HttpRequest;

import java.io.IOException;

/**
 * 静态资源处理器
 *
 * @author fengquanwei
 * @create 2017/8/28 14:11
 **/
public class StaticResourceProcessor {
    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
