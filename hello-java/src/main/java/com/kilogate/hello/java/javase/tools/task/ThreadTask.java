package com.kilogate.hello.java.javase.tools.task;

import java.util.Date;

/**
 * Created by fengquanwei on 2016/10/11.
 * 普通线程实现定时任务
 */
public class ThreadTask {
    public static void main(String[] args) {
        final long timeInterval = 1000; // 每秒运行一次
        final long endTimeMills = new Date().getTime() + 10 * 1000; // 线程结束时间
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (new Date().getTime() < endTimeMills) {
                    System.out.println(new Date());
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
