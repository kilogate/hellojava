package com.kilogate.hello.tomcat.connector2.startup;

import com.kilogate.hello.tomcat.connector2.connector.HttpConnector;

/**
 * 启动类
 *
 * @author fengquanwei
 * @create 2017/8/30 14:38
 **/
public class Bootstrap {
    /**
     * Debug Configuration
     * Working directory: /Users/kilogate/IdeaProjects/hello/hello-tomcat
     * VM options: -classpath /Users/fengquanwei/IdeaProjects/hello/hello-tomcat/target/classes:/Users/fengquanwei/.m2/repository/javax/servlet/servlet-api/3.0-alpha-1/servlet-api-3.0-alpha-1.jar:./
     * <p>
     * Test URL
     * http://localhost:8081/hello.html
     * http://localhost:8081/servlet/com.kilogate.hello.tomcat.servlet.PrimitiveServlet
     * http://localhost:8081/servlet/com.kilogate.hello.tomcat.servlet.ModernServlet
     */
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
