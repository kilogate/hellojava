package com.kilogate.hello.java.javase.jdkapi.io.channel;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Paths;

/**
 * 文件加锁机制
 *
 * @author fengquanwei
 * @create 2017/7/3 22:43
 **/
public class FileLockUsage {
    public static void main(String[] args) throws IOException {
//        FileChannel channel = FileChannel.open(Paths.get("/tmp/adobegc.log"));
//        try (FileLock lock = channel.lock()) {
//
//        }

        System.out.println((int)'A');
        System.out.println("\u0041");
        System.out.println("\0101");
    }
}
