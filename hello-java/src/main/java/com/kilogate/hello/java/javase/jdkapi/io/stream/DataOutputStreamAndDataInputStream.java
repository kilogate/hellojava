package com.kilogate.hello.java.javase.jdkapi.io.stream;

import java.io.*;

/**
 * DataOutputStream and DataInputStream
 *
 * @author fengquanwei
 * @create 2017/6/21 15:32
 **/
public class DataOutputStreamAndDataInputStream {
    public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream("/Users/kilogate/Documents/Tmp/iotest"));
        out.writeUTF("严"); // UTF-8 编码，最前面加两位表示长度（字节数）
        out.writeChars("严"); // Unicode 编码
        out.writeChar('严'); // Unicode 编码

        DataInputStream in = new DataInputStream(new FileInputStream("/Users/kilogate/Documents/Tmp/iotest"));
        String s = in.readUTF();
        char c = in.readChar();
        char c1 = in.readChar();
    }
}
