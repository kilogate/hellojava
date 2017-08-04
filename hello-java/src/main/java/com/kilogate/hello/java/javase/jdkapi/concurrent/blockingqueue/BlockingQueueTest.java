package com.kilogate.hello.java.javase.jdkapi.concurrent.blockingqueue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列测试
 * 程序在一个目录及它的所有子目录下搜索所有文件，打印出包含指定关键字的行。
 *
 * @author fengquanwei
 * @create 2017/6/2 14:31
 **/
public class BlockingQueueTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter base directory (e.g. /Users/kilogate/Downloads/jdk/src): ");
        String directory = in.nextLine();
        System.out.print("Enter keyword (e.g. volatile): ");
        String keyword = in.nextLine();

        final int FILE_QUEUE_SIZE = 10;
        final int SEARCH_THREADS = 100;

        BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

        FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
        new Thread(enumerator).start();

        for (int i = 1; i <= SEARCH_THREADS; i++) {
            new Thread(new SearchTask(queue, keyword)).start();
        }
    }
}

