package com.kilogate.hello.java.javase.jdkapi.concurrent.interrupt;

import java.util.Date;

/**
 * 可取消阻塞线程中断测试
 *
 * @author fengquanwei
 * @create 2017/5/29 21:21
 **/
public class CancelableBlockingThread extends Thread {
    private Thread parent; // 父线程

    public CancelableBlockingThread(Thread parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(new Date() + " Sub thread is running...");
            long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < 3000) {
                // 为了避免 Thread.sleep() 而需要捕获 InterruptedException 而带来理解上的困惑，
                // 此处用这种方法空转 3 秒
            }

            // 中断一个阻塞线程（父线程）
            parent.interrupt();
        }

    }

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        System.out.println(new Date() + " " + mainThread + " start.");

        CancelableBlockingThread thread = new CancelableBlockingThread(mainThread);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(new Date() + " main thread will die...");
        }

        System.out.println(new Date() + " " + mainThread + " end.");
    }
}
