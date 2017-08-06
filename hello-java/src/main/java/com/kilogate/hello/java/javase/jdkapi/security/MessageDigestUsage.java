package com.kilogate.hello.java.javase.jdkapi.security;

import java.io.IOException;
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
        MessageDigest sha1MessageDigest = MessageDigest.getInstance("SHA-1");

        byte[] bytes = Files.readAllBytes(Paths.get("/Users/kilogate/Documents/Tmp/tmp1.txt"));
        sha1MessageDigest.update(bytes);
        byte[] digestHash = sha1MessageDigest.digest();

        String digest = "";
        for (int i = 0; i < digestHash.length; i++) {
            int v = digestHash[i] & 0xFF;
            if (v < 16) {
                digest += "0";
            }
            digest += Integer.toString(v, 16).toUpperCase() + " ";
        }
        System.out.println(digest);
    }
}
