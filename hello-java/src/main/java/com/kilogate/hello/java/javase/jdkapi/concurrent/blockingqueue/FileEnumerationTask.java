package com.kilogate.hello.java.javase.jdkapi.concurrent.blockingqueue;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * This task enumerates all files in a directory and its subdirectories.
 */
public class FileEnumerationTask implements Runnable {
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
