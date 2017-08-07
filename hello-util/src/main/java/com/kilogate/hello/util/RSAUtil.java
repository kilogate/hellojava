package com.kilogate.hello.util;

import javax.crypto.*;
import java.security.*;
import java.util.Arrays;

/**
 * RSA 加密解密工具类
 *
 * @author fengquanwei
 * @create 2017/8/7 16:26
 **/
public class RSAUtil {
    private static final int KEY_SIZE = 1024;

    /**
     * 生成公钥私钥对
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 使用公钥加密
     */
    public static byte[] encrypt(byte[] data, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 使用私钥解密
     */
    public static byte[] decrypt(byte[] encrypt, PrivateKey privateKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encrypt);
    }

    /**
     * 使用公钥封包
     */
    public static byte[] wrap(Key key, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.WRAP_MODE, publicKey);
        return cipher.wrap(key);
    }

    /**
     * 使用私钥解包
     */
    public static Key unwrap(byte[] unwrap, PrivateKey privateKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.UNWRAP_MODE, privateKey);
        return cipher.unwrap(unwrap, "AES", Cipher.SECRET_KEY);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        // 生成公私钥
        KeyPair keyPair = generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("Public Key: " + Arrays.toString(publicKey.getEncoded()));
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("Private Key: " + Arrays.toString(privateKey.getEncoded()));

        byte[] data = "Lask".getBytes();

        // 加密
        byte[] encrypt = encrypt(data, publicKey);
        System.out.println("Encrypt: " + Arrays.toString(encrypt));

        // 解密
        byte[] decrypt = decrypt(encrypt, privateKey);
        System.out.println("Decrypt: " + new String(decrypt));

        System.out.println("\n===================================\n");

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(new SecureRandom());
        SecretKey secretKey = keyGenerator.generateKey();
        System.out.println("Key: " + Arrays.toString(secretKey.getEncoded()));

        // 封包
        byte[] wrap = wrap(secretKey, publicKey);
        System.out.println("Wrap: " + Arrays.toString(wrap));

        // 解包
        Key unwrap = unwrap(wrap, privateKey);
        System.out.println("Unwrap: " + Arrays.toString(unwrap.getEncoded()));
    }
}
