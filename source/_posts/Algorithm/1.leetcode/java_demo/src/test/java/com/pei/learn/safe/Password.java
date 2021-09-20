package com.pei.learn.safe;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class Password {
    //    使用char[] 而不是String存储密码，敏感数据使用后立刻清0
    @Test
    @DisplayName("ReDos攻击")
    public void test_binary_redos() {
        String pwd = "password";
        try {
            Field value = String.class.getDeclaredField("value");
            value.setAccessible(true);
            char[] pwds = (char[]) value.get("pwd");
            for (int i = 0; i < pwds.length; i++) {
                pwds[i] = 0;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("ReDos攻击")
    public void test_binary_secRandom() {
        byte[] bytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        System.out.println(new String(bytes));
    }


    @Test
    @DisplayName("ReDos攻击")
    public void test_binary_hash() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String pwd = "password1";
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[8];
        secureRandom.nextBytes(salt);

        int iter = 10000;

        PBEKeySpec sp = new PBEKeySpec(pwd.toCharArray(), salt, iter, 256);
        //        JDK1.8 z支持
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] encoded = skf.generateSecret(sp).getEncoded();
        System.out.println(new String(encoded)); // [B@36d4b5c

    }

}
