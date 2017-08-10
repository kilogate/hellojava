package com.kilogate.hello.java.javase.jdkapi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 仓库接口（远程对象定义）
 *
 * @author fengquanwei
 * @create 2017/8/10 10:48
 **/
public interface WareHouse extends Remote {
    double getPrice(String description) throws RemoteException;
}
