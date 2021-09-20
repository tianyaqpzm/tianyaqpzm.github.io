package com.pei.learn.safe;


import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class CipherTest {

    private final static String pwd = "test在";

    /**
     * 不安全
     *
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private void DES() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
        Cipher des = Cipher.getInstance("DES");
        des.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encode = pwd.getBytes("UTF-8");
        byte[] bytes = des.doFinal(encode);
    }


    private static final int GCM_TAG_LEN = 16;
    private static final int GCM_IV_LEN = 12;

    /**
     * 安全：GCM模式， 不能使用ECB模式
     *
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private static byte[] AESEncryptGcm(SecretKey secretKey, String plain) {
        byte[] cipher = null;
        try {
            Cipher aes = Cipher.getInstance("AES/GCM/NoPadding");

            byte[] iv = new byte[GCM_IV_LEN];
            (new SecureRandom()).nextBytes(iv);

            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LEN * Byte.SIZE, iv);
            aes.init(Cipher.ENCRYPT_MODE, secretKey, spec);
            byte[] encode = plain.getBytes(StandardCharsets.UTF_8);
            cipher = new byte[iv.length + aes.getOutputSize(encode.length)];
            for (int i = 0; i < iv.length; i++) {
                cipher[i] = iv[i];
            }
            aes.doFinal(encode, 0, encode.length, cipher, iv.length);
        } catch (ShortBufferException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return cipher;
    }

    private static String AESDecryptGcm(SecretKey secretKey, byte[] cipher) {
        try {
            Cipher aes = Cipher.getInstance("AES/GCM/NoPadding");

            byte[] iv = Arrays.copyOfRange(cipher, 0, GCM_IV_LEN);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LEN * Byte.SIZE, iv);
            aes.init(Cipher.DECRYPT_MODE, secretKey, spec);
            byte[] plain = aes.doFinal(cipher, GCM_IV_LEN, cipher.length - GCM_IV_LEN);
            return new String(plain);
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalStateException(e.toString());
        }
    }

    public static void main(String[] args) {
    }


}
