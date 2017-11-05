package com.kilogate.hello.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * MyHttpSessionListener
 *
 * @author fengquanwei
 * @create 2017/11/5 13:19
 **/
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        System.out.println("Session(" + session.getId() + ") sessionCreated");

        session.setAttribute("sessionAttr", "sessionValue");
        session.setAttribute("sessionAttr", "sessionValueSessionValue");
        session.setAttribute("bindingObject", new HttpSessionBindingListenerJavaBean());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        System.out.println("Session(" + session.getId() + ") sessionDestroyed");

        session.removeAttribute("sessionAttr");
        session.removeAttribute("bindingObject");
    }
}
