package com.topshow.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {
    private static final Base64.Encoder encoder = Base64.getEncoder();

    public static String decoder(String data) {
        byte[] dataBytes = null;
        try {
            dataBytes = data.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoder.encodeToString(dataBytes);
    }
}
