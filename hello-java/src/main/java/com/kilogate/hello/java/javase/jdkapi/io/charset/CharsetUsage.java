package com.kilogate.hello.java.javase.jdkapi.io.charset;

import java.nio.charset.Charset;
import java.util.Set;
import java.util.SortedMap;

/**
 * 字符集用法
 *
 * @author fengquanwei
 * @create 2017/6/21 11:02
 **/
public class CharsetUsage {
    public static void main(String[] args) {
        // 可用字符集
        SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
        Set<String> charsetKeys = availableCharsets.keySet();
        for (String charsetKey : charsetKeys) {
            Charset charset = availableCharsets.get(charsetKey);
            System.out.println(charset.aliases());
        }

        // Unicode 替代字符 �（Unicode 无法识别的字符转换成替代字符）
        char c = '\uFFFD';
        System.out.println(c);

        // Unicode 编码 4e25 -> 0100 1110 0010 0101
        // UTF-8 编码 e4b8a5 -> 1110 0100 1011 1000 1010 0101
        // DataOutputStream.writeUTF 前两位表示长度（字节数），最多有 65536 个字节，即 64KB
        // writeUTF 编码 0003e4b8a5 -> 0000 0000 0000 0011 1110 0100 1011 1000 1010 0101

        System.out.println(Integer.toHexString('严')); // Unicode 字符编码
        String s = "严"; // UTF-16 编码，0x4e25 = 20005，虚拟机默认编码方式
        byte[] bytes = "严".getBytes(Charset.forName("UTF-8")); // UTF-8 编码 e4b8a5

        // UTF-8 编码
        // 单字节：0xxxxxxx
        // 双字节：110xxxxx 10xxxxxx
        // 三字节：1110xxxx 10xxxxxx 10xxxxxx
        // 四字节：11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
    }
}
