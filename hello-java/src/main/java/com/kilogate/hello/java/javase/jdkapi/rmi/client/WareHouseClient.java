package com.kilogate.hello.java.javase.jdkapi.rmi.client;

import com.kilogate.hello.java.javase.jdkapi.rmi.WareHouse;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * 仓库客户端
 *
 * @author fengquanwei
 * @create 2017/8/10 11:12
 **/
public class WareHouseClient {
    /**
     * 记录 RMI 活动日志 -Djava.rmi.server.logCalls=true
     */
    public static void main(String[] args) throws NamingException, RemoteException {
        InitialContext namingContext = new InitialContext(); // 命名上下文（用于访问 RMI 注册表）

        System.out.println("RMI registry bindings:");
        NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost:8888/");
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement().getName());
        }

        String url = "rmi://localhost:8888/central_warehouse";
        WareHouse centralWareHouse = (WareHouse) namingContext.lookup(url);

        String description = "Blackwell Toaster";
        double price = centralWareHouse.getPrice(description);
        System.out.println(description + ": " + price);
    }
}
