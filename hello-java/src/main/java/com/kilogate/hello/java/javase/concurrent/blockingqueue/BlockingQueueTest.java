package com.kilogate.hello.java.javase.concurrent.blockingqueue;

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

/**
 * This task enumerates all files in a directory and its subdirectories.
 */
class FileEnumerationTask implements Runnable {
    public static File DUMMY = new File(""); // 虚拟对象，用于表示最后一个文件
    private BlockingQueue<File> queue;
    private File startingDirectory;

    public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    @Override
    public void run() {
        try {
            enumerate(startingDirectory);
            queue.put(DUMMY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                enumerate(file);
            } else {
                queue.put(file);
            }
        }
    }
}

/**
 * This task searchs files for a given keyword.
 */
class SearchTask implements Runnable {
    private BlockingQueue<File> queue;
    private String keyword;

    public SearchTask(BlockingQueue<File> queue, String keyword) {
        this.queue = queue;
        this.keyword = keyword;
    }

    @Override
    public void run() {
        try {
            boolean done = false;
            while (!done) {
                File file = queue.take();
                if (file == FileEnumerationTask.DUMMY) {
                    queue.put(file);
                    done = true;
                } else {
                    search(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void search(File file) throws IOException {
        try (Scanner in = new Scanner(file)) { // 自动资源管理
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
                }
            }
        }
    }
}