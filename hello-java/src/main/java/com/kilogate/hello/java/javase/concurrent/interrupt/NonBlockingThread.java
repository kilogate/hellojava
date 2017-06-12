package com.kilogate.hello.java.javase.concurrent.interrupt;

import java.util.Date;

/**
 * 非阻塞线程中断测试
 *
 * @author fengquanwei
 * @create 2017/5/29 20:45
 **/
public class NonBlockingThread extends Thread {
    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                System.out.println(new Date() + ": Someone interrupted me.");
                return;
            } else {
                System.out.println(new Date() + ": Going...");
            }
            long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < 1000) {
                // 为了避免 Thread.sleep() 而需要捕获 InterruptedException 而带来理解上的困惑，
                // 此处用这种方法空转 1 秒
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NonBlockingThread thread = new NonBlockingThread();
        thread.start();

        // 3s 之后中断一个非阻塞线程
        Thread.sleep(3000);
        thread.interrupt();
    }
}