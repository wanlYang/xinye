package com.topshow.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String MD5Encode(String plainText) {
        byte[] secretBytes = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(plainText.getBytes());

            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }

        String md5code = (new BigInteger(1, secretBytes)).toString(16);

        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String as = "1";
        System.out.println(Base64Util.decoder(MD5Encode(as)));
        System.out.println();
    }
}
