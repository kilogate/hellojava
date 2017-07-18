package com.kilogate.hello.java.javase.jdkapi.io.channel;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.zip.CRC32;

/**
 * 内存映射用法
 *
 * @author fengquanwei
 * @create 2017/6/30 20:37
 **/
public class MemoryMapUsage {
    public static long checksumInputStream(Path path) throws IOException {
        try (InputStream in = Files.newInputStream(path)) {
            CRC32 crc32 = new CRC32();
            int i;
            while ((i = in.read()) != -1) {
                crc32.update(i);
            }
            return crc32.getValue();
        }
    }

    public static long checksumBufferedInputStream(Path path) throws IOException {
        try (InputStream in = new BufferedInputStream(Files.newInputStream(path))) {
            CRC32 crc32 = new CRC32();
            int i;
            while ((i = in.read()) != -1) {
                crc32.update(i);
            }
            return crc32.getValue();
        }
    }

    public static long checksumRandomAccessFile(Path path) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(path.toFile(), "r")) {
            CRC32 crc32 = new CRC32();

            long length = file.length();
            for (long p = 0; p < length; p++) {
                file.seek(p);
                int i = file.readByte();
                crc32.update(i);
            }
            return crc32.getValue();
        }
    }

    public static long checksumMappedFile(Path path) throws IOException {
        try (FileChannel channel = FileChannel.open(path)) {
            CRC32 crc32 = new CRC32();

            long size = channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            for (int p = 0; p < size; p++) {
                int i = buffer.get(p);
                crc32.update(i);
            }

            return crc32.getValue();
        }
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/kilogate/Downloads/rt.jar");
        long crcValue = -1;
        long start = -1;
        long end = -1;

        System.out.println("Input Stream Start: " + new Date());
        start = System.currentTimeMillis();
        crcValue = checksumInputStream(path);
        end = System.currentTimeMillis();
        System.out.println((end - start) + " milliseconds");
        System.out.println("crc: " + Long.toHexString(crcValue));
        System.out.println("Input Stream End: " + new Date());

        System.out.println();

        System.out.println("Buffered Input Stream Start:" + new Date());
        start = System.currentTimeMillis();
        crcValue = checksumBufferedInputStream(path);
        end = System.currentTimeMillis();
        System.out.println((end - start) + " milliseconds");
        System.out.println("crc: " + Long.toHexString(crcValue));
        System.out.println("Buffered Input Stream End:" + new Date());

        System.out.println();

        System.out.println("Random Access File Start:" + new Date());
        start = System.currentTimeMillis();
        crcValue = checksumRandomAccessFile(path);
        end = System.currentTimeMillis();
        System.out.println("crc: " + Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");
        System.out.println("Random Access File End:" + new Date());

        System.out.println();

        System.out.println("Mapped File Start:" + new Date());
        start = System.currentTimeMillis();
        crcValue = checksumMappedFile(path);
        end = System.currentTimeMillis();
        System.out.println("crc: " + Long.toHexString(crcValue));
        System.out.println((end - start) + " milliseconds");
        System.out.println("Mapped File End:" + new Date());
    }
}
