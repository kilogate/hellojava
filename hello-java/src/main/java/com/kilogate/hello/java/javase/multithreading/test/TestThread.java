package com.kilogate.hello.java.javase.multithreading.test;

import java.util.Date;

/**
 * 测试线程
 *
 * @author fengquanwei
 * @create 2017/5/15 10:42
 **/
public class TestThread {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                System.out.println(new Date() + " ： " + thread + " start");
                for (int i = 0; i < 10; i++) {
                    System.out.println(new Date() + " ： " + thread + " [" + i + "] " + " isInterrupted: " + thread.isInterrupted());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i == 5){
                        thread.interrupt();
                    }
                }
                System.out.println(new Date() + " ： " + thread + " end");
            }
        };

        Thread thread1 = new Thread(runnable);
        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(runnable);
        thread2.start();

        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread3 = new Thread(runnable);
        thread3.start();
    }
}
