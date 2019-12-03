package com.topshow.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlUtil {
    private static final String ENCODE = "UTF-8";

    public static String getURLEncoderString(String url) {
        String result = "";
        if (null == url) {
            return "";
        }
        try {
            result = URLEncoder.encode(url, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getURLDecoderString(String url) {
        String result = "";
        if (null == url) {
            return "";
        }
        try {
            result = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "https://www.theaic.cn/admin/qq/oauth2/login";
        System.out.println(getURLEncoderString(str));
        System.out.println(getURLDecoderString(str));
    }
}
