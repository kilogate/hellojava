package com.kilogate.hello.java.javase.jdkapi.security;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES 对称密钥算法用法
 *
 * @author fengquanwei
 * @create 2017/8/6 21:36
 **/
public class AesCipherUsage {
    public static void main(String[] args) {
        try {
            // 生成密钥
            KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom aesSecureRandom = new SecureRandom();
            aesKeyGenerator.init(aesSecureRandom);
            SecretKey aesSecretKey = aesKeyGenerator.generateKey();

            // 加密
            Cipher aesEncryptCipher = Cipher.getInstance("AES");
            aesEncryptCipher.init(Cipher.ENCRYPT_MODE, aesSecretKey);
            try (InputStream in = new FileInputStream("/Users/kilogate/Downloads/plaintextFile");
                 OutputStream out = new FileOutputStream("/Users/kilogate/Downloads/encryptedFile")) {
                CryptUtil.crypt(in, out, aesEncryptCipher);
            }

            //解密
            Cipher aesDecryptCipher = Cipher.getInstance("AES");
            aesDecryptCipher.init(Cipher.DECRYPT_MODE, aesSecretKey);
            try (InputStream in = new FileInputStream("/Users/kilogate/Downloads/encryptedFile");
                 OutputStream out = new FileOutputStream("/Users/kilogate/Downloads/decryptedFile")) {
                CryptUtil.crypt(in, out, aesDecryptCipher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ShortBufferException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
