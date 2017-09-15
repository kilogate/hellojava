package com.kilogate.hello.tomcat.connector2.startup;

import com.kilogate.hello.tomcat.connector2.connector.HttpConnector;

/**
 * 启动类
 *
 * @author fengquanwei
 * @create 2017/8/30 14:38
 **/
public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
