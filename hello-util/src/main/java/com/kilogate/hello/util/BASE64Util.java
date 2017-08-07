package com.kilogate.hello.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * BASE64 编码解码工具类
 * a-zA-Z0-9/+
 * 三字节 -> 四字节（不足三字节补 '='）
 *
 * @author fengquanwei
 * @create 2017/7/25 11:00
 **/
public class BASE64Util {
    public static String encode(String data) {
        return encode(data, StandardCharsets.UTF_8);
    }

    public static String encode(String data, Charset charset) {
        return encode(data.getBytes(charset));
    }

    public static String encode(byte[] data) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(data);
    }

    public static String decodeToString(String encode) throws IOException {
        return decodeToString(encode, StandardCharsets.UTF_8);
    }

    public static String decodeToString(String encode, Charset charset) throws IOException {
        return new String(decode(encode), charset);
    }

    public static byte[] decode(String encode) throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        return base64Decoder.decodeBuffer(encode);
    }

    public static void main(String[] args) throws IOException {
        String data = "你好";

        // 编码
        String encode = encode(data);
        System.out.println(encode);

        // 解码
        String decode = decodeToString(encode);
        System.out.println(decode);
    }
}
