package com.kilogate.hello.java.javase.jdkapi.security;

import javax.crypto.*;
import java.io.*;
import java.security.*;

/**
 * RSA 公共密钥算法
 *
 * @author fengquanwei
 * @create 2017/8/6 22:52
 **/
public class RsaCipherUsage {
    private static final int KEYSIZE = 512;

    public static void main(String[] args) {
        try {
            String rsaPublicKeyPath = "/Users/kilogate/Downloads/public.key";
            String rsaPrivateKeyPath = "/Users/kilogate/Downloads/private.key";
            String plaintextFilePath = "/Users/kilogate/Downloads/plaintextFile";
            String encryptedFilePath = "/Users/kilogate/Downloads/encryptedFile";
            String decryptedFilePath = "/Users/kilogate/Downloads/decryptedFile";

            // 生成 RSA 公钥和密钥
            KeyPairGenerator rsaKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom rsaSecureRandom = new SecureRandom();
            rsaKeyPairGenerator.initialize(KEYSIZE, rsaSecureRandom);
            KeyPair rsaKeyPair = rsaKeyPairGenerator.generateKeyPair();
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rsaPublicKeyPath))) {
                out.writeObject(rsaKeyPair.getPublic());
            }
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rsaPrivateKeyPath))) {
                out.writeObject(rsaKeyPair.getPrivate());
            }

            // 加密
            try (ObjectInputStream rsaPublicKeyIn = new ObjectInputStream(new FileInputStream(rsaPublicKeyPath));
                 InputStream in = new FileInputStream(plaintextFilePath);
                 DataOutputStream out = new DataOutputStream(new FileOutputStream(encryptedFilePath))) {
                // 生成 AES 密钥
                KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES");
                SecureRandom aesSecureRandom = new SecureRandom();
                aesKeyGenerator.init(aesSecureRandom);
                SecretKey aesSecretKey = aesKeyGenerator.generateKey();

                // 使用 RSA 公钥加密 AES 密钥
                Key rsaPublicKey = (Key) rsaPublicKeyIn.readObject();
                Cipher rsaCipher = Cipher.getInstance("RSA");
                rsaCipher.init(Cipher.WRAP_MODE, rsaPublicKey);
                byte[] wrappedAesSecretKey = rsaCipher.wrap(aesSecretKey);
                out.writeInt(wrappedAesSecretKey.length);
                out.write(wrappedAesSecretKey);

                // 使用 AES 密钥对数据加密
                Cipher aesCipher = Cipher.getInstance("AES");
                aesCipher.init(Cipher.ENCRYPT_MODE, aesSecretKey);
                AesCryptUtil.crypt(in, out, aesCipher);
            }

            // 解密
            try (ObjectInputStream rsaPrivateKeyIn = new ObjectInputStream(new FileInputStream(rsaPrivateKeyPath));
                 DataInputStream in = new DataInputStream(new FileInputStream(encryptedFilePath));
                 OutputStream out = new FileOutputStream(decryptedFilePath)) {
                // 使用 RSA 密钥解密加密的 AES 密钥
                int length = in.readInt();
                byte[] wrappedAesSecretKey = new byte[length];
                in.read(wrappedAesSecretKey, 0, length);

                Key rsaPrivateKey = (Key) rsaPrivateKeyIn.readObject();
                Cipher rsaCipher = Cipher.getInstance("RSA");
                rsaCipher.init(Cipher.UNWRAP_MODE, rsaPrivateKey);
                Key aesSecretKey = rsaCipher.unwrap(wrappedAesSecretKey, "AES", Cipher.SECRET_KEY);

                // 使用 AES 密钥对数据解密
                Cipher aesCipher = Cipher.getInstance("AES");
                aesCipher.init(Cipher.DECRYPT_MODE, aesSecretKey);
                AesCryptUtil.crypt(in, out, aesCipher);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (ShortBufferException e) {
            e.printStackTrace();
        }
    }
}
