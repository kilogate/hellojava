package com.kilogate.hello.java.javase.jdkapi.io.stream;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Zip
 *
 * @author fengquanwei
 * @create 2017/6/24 16:01
 **/
public class ZipOutputStreamAndZipInputStream4Zip {
    public static void main(String[] args) throws Exception {
        File srcFile = new File("/Users/kilogate/Documents/Tmp");
        File zipFile = new File("/Users/kilogate/Downloads/out.zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        zip(srcFile, zipFile, out, "");
        out.close();

        unzip(zipFile, "/Users/kilogate/Downloads/unzip");
//        unzip2(zipFile, "/Users/kilogate/Downloads/unzip");

    }

    /**
     * 压缩文件或文件夹
     * srcFile 要压缩的文件或文件夹
     * zipFile 压缩文件
     * out 压缩输出流
     * entryPrefix 项前缀
     */
    private static void zip(File srcFile, File zipFile, ZipOutputStream out, String entryPrefix) throws IOException {
        if (srcFile == null || zipFile == null) {
            return;
        }

        // 如果是文件，则直接压缩该文件
        if (srcFile.isFile()) {
            int count, bufferLength = 1024;
            byte[] buffer = new byte[bufferLength];

            out.putNextEntry(new ZipEntry(entryPrefix + File.separator + srcFile.getName()));
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcFile));
            while ((count = in.read(buffer, 0, bufferLength)) != -1) {
                out.write(buffer, 0, count);
            }
            in.close();
            out.closeEntry();
        } else { // 如果是目录，则压缩整个目录
            File[] files = srcFile.listFiles();
            for (File file : files) {
                zip(file, zipFile, out, entryPrefix + File.separator + srcFile.getName());
            }
        }
    }

    /**
     * 解压缩 ZIP 文件（使用 ZipFile）
     * file 待解压缩的 ZIP 文件
     * destDir 解压到的目录
     */
    public static void unzip(File file, String destDir) throws IOException {
        if (file == null || destDir == null || destDir.equals("")) {
            return;
        }

        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        int count, bufferLength = 1024;
        byte data[] = new byte[bufferLength];

        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = new ZipEntry(entries.nextElement());

            if (zipEntry.isDirectory()) {
                continue;
            }

            String entryFilename = destDir + File.separator + zipEntry.getName();
            int index = -1;
            if ((index = entryFilename.lastIndexOf("/")) != -1) {
                File dir = new File(entryFilename.substring(0, index));
                if (!dir.exists() || !dir.isDirectory()) {
                    dir.mkdirs();
                }
            }

            in = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            out = new BufferedOutputStream(new FileOutputStream(entryFilename));
            while ((count = in.read(data, 0, bufferLength)) != -1) {
                out.write(data, 0, count);
            }
            out.flush();
            out.close();
            in.close();
        }

        zipFile.close();
    }

    /**
     * 解压缩 ZIP 文件（使用 ZipInputStream）
     * file 待解压缩的 ZIP 文件
     * destDir 解压到的目录
     */
    public static void unzip2(File file, String destDir) throws IOException {
        if (file == null || destDir == null || destDir.equals("")) {
            return;
        }

        ZipInputStream in = new ZipInputStream(new FileInputStream(file));
        ZipEntry zipEntry = null;

        int count, bufferLength = 1024;
        byte buffer[] = new byte[bufferLength];

        BufferedOutputStream out = null;
        while ((zipEntry = in.getNextEntry()) != null) {
            if (zipEntry.isDirectory()) {
                continue;
            }

            String entryFilename = destDir + File.separator + zipEntry.getName();
            int index = -1;
            if ((index = entryFilename.lastIndexOf("/")) != -1) {
                File dir = new File(entryFilename.substring(0, index));
                if (!dir.exists() || !dir.isDirectory()) {
                    dir.mkdirs();
                }
            }

            out = new BufferedOutputStream(new FileOutputStream(entryFilename));
            while ((count = in.read(buffer, 0, bufferLength)) != -1) {
                out.write(buffer, 0, count);
            }
            out.flush();
            out.close();
            in.closeEntry();
        }

        in.close();
    }
}
