package com.kilogate.hello.java.javase.jdkapi.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要用法
 *
 * @author fengquanwei
 * @create 2017/8/6 16:44
 **/
public class MessageDigestUsage {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        // 消息摘要（SHA-1）
        MessageDigest sha1MessageDigest = MessageDigest.getInstance("SHA-1");
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/kilogate/Documents/Tmp/tmp1.txt"));
        sha1MessageDigest.update(bytes);
        byte[] sha1DigestBytes = sha1MessageDigest.digest();

        String sha1Digest = getHexStringFromBytes(sha1DigestBytes);
        System.out.println(sha1Digest);

        // 消息摘要（MD5）
        MessageDigest md5MessageDigest = MessageDigest.getInstance("MD5");
        String message = "1";
        md5MessageDigest.update(message.getBytes(StandardCharsets.UTF_8));
        byte[] md5DigestBytes = md5MessageDigest.digest();

        String md5Digest = getHexStringFromBytes(md5DigestBytes);
        System.out.println(md5Digest);
    }

    private static String getHexStringFromBytes(byte[] bytes) {
        String result = "";

        int length = bytes.length;
        if (bytes != null && length > 0) {
            for (int i = 0; i < length; i++) {
                int v = bytes[i] & 0xFF;
                if (v < 16) {
                    result += "0";
                }
                result += Integer.toString(v, 16).toUpperCase() + " ";
            }
        }

        return result;
    }
}
