package com.kilogate.hello.java.test;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试专用类
 *
 * @author kilogate
 * @create 2017/3/29 16:50
 **/
public class Test {
    public static void main(String[] args) throws IOException {


        AtomicInteger atomicInteger = new AtomicInteger();
        for(int i = 0;i<10;i++){
            System.out.println(atomicInteger.get());
        }
    }
}
