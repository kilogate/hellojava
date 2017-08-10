package com.kilogate.hello.java.javase.jdkapi.rmi.server;

import com.kilogate.hello.java.javase.jdkapi.rmi.WareHouse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * 仓库实现（远程对象实现）
 * 远程方法调用的目标，扩展自 UnicastRemoteObject（这个类的构造器使得它的对象可供远程访问）
 *
 * @author fengquanwei
 * @create 2017/8/10 10:49
 **/
public class WareHouseImpl extends UnicastRemoteObject implements WareHouse {
    private Map<String, Double> prices;

    public WareHouseImpl() throws RemoteException {
        prices = new HashMap<>();
        prices.put("Blackwell Toaster", 24.95);
        prices.put("ZapXpress Microwaave Oven", 49.95);
    }

    @Override
    public double getPrice(String description) throws RemoteException {
        Double price = prices.get(description);
        return price == null ? 0 : price;
    }
}
