package com.kilogate.hello.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * AES 加密解密工具类
 *
 * @author fengquanwei
 * @create 2017/8/7 12:05
 **/
public class AESUtil {
    // AES 密钥长度必须是 128，192 或 256 位（但是美国软件出口限制不能使用大于 128 位的密钥）
    private static final int KEY_SIZE = 128;

    /**
     * 加密
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return crypt(data, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     */
    public static byte[] decrypt(byte[] encrypt, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        return crypt(encrypt, key, Cipher.DECRYPT_MODE);
    }

    private static byte[] crypt(byte[] bytes, byte[] key, int mode) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(mode, secretKeySpec);
        return cipher.doFinal(bytes);
    }

    /**
     * 根据种子生成随机的 AES 密钥
     */
    public static byte[] generateKey(byte[] seed) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();

        return secretKey.getEncoded();
    }

    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] data = "data".getBytes(StandardCharsets.UTF_8);
        byte[] seed = "seed".getBytes(StandardCharsets.UTF_8);
        byte[] key = generateKey(seed);

        byte[] encrypt = encrypt(data, key);
        System.out.println(Arrays.toString(encrypt));

        byte[] decrypt = decrypt(encrypt, key);
        System.out.println(new String(decrypt, StandardCharsets.UTF_8));

    }
}
