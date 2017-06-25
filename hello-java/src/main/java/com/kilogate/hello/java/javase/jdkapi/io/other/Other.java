package com.kilogate.hello.java.javase.jdkapi.io.other;

import java.io.File;
import java.io.IOException;

/**
 * 其他说明
 *
 * @author fengquanwei
 * @create 2017/6/19 20:59
 **/
public class Other {
    public static void main(String[] args) throws IOException {
        // IO 相对路径：用户工作目录
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        // 行结束符号
        String lineSeparator = System.getProperty("line.separator");
        System.out.println(lineSeparator);
        // 程序运行平台文件分隔符
        String fileSeparator = File.separator;
        System.out.println(fileSeparator);
    }
}