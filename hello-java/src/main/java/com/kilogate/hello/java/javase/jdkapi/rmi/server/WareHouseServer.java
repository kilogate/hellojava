package com.kilogate.hello.java.javase.jdkapi.rmi.server;

import com.kilogate.hello.java.javase.jdkapi.rmi.WareHouse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 仓库服务器：构造并注册服务
 * RMI 注册表，自举注册服务（bootstrap registry service）
 *
 * @author fengquanwei
 * @create 2017/8/10 11:05
 **/
public class WareHouseServer {
    public static void main(String[] args) throws RemoteException, NamingException {
        System.out.println("Constructing server implemention...");
        WareHouse centralWareHouse = new WareHouseImpl();

        LocateRegistry.createRegistry(8888);

        System.out.println("Binding server implemention to registry...");
        InitialContext namingContext = new InitialContext(); // 命名上下文（用于访问 RMI 注册表）
        // RMI URL 格式：rmi://host:port/object，默认情况下，主机名是 localhost， 端口号是 1099
        namingContext.bind("rmi://localhost:8888/central_warehouse", centralWareHouse); // 绑定服务

        System.out.println("Waiting for invacations from clients...");
    }
}
