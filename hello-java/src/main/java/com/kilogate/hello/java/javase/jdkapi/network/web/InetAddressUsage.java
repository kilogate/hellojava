package com.kilogate.hello.java.javase.jdkapi.network.web;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

/**
 * 因特网地址用法
 *
 * @author fengquanwei
 * @create 2017/7/23 14:58
 **/
public class InetAddressUsage {
    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress.getHostAddress());
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress);

        InetAddress[] inetAddresses = InetAddress.getAllByName("www.baidu.com");
        System.out.println(Arrays.toString(inetAddresses));

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
    }
}
