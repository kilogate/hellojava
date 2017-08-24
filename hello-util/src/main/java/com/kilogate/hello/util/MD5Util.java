package com.kilogate.hello.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * MD5 消息摘要工具类
 *
 * @author fengquanwei
 * @create 2017/8/7 11:58
 **/
public class MD5Util {
    public static byte[] md5(String message) throws NoSuchAlgorithmException {
        return md5(message, StandardCharsets.UTF_8);
    }

    public static byte[] md5(String message, Charset charset) throws NoSuchAlgorithmException {
        MessageDigest md5MessageDigest = MessageDigest.getInstance("MD5");
        md5MessageDigest.update(message.getBytes(charset));
        return md5MessageDigest.digest();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String message = "你好gdgdfgsadgsdgsdfsdfs";
        byte[] md5Bytes = md5(message);
        System.out.println(Arrays.toString(md5Bytes));
    }
}
