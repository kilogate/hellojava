package com.kilogate.hello.servlet.listener;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
 * HttpSessionActivationListenerJavaBean
 * 不需要注册到 web.xml。需要配置使得 Web 服务器（Tomcat）支持 JavaBean 序列化，app/META-INF/context.xml 一分钟后持久化会话属性到外存。
 *
 * @author fengquanwei
 * @create 2017/11/5 13:37
 **/
public class HttpSessionActivationListenerJavaBean implements HttpSessionActivationListener, Serializable {
    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session(" + httpSessionEvent.getSession().getId() + ") sessionWillPassivate");
        System.out.println(this + " 将随 Session 钝化而被保存到外存");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session(" + httpSessionEvent.getSession().getId() + ") sessionDidActivate");
        System.out.println(this + " 将随 Session 活化而被加载到内存");
    }
}
