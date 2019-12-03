package com.topshow.utils;

import java.util.Random;
import java.util.UUID;

public class UUIDUtils {
    public static String getUid() {
        return UUID.randomUUID().toString();
    }

    public static String getOrderid() {
        return UUID.randomUUID().toString();
    }

    public static String getOrderItemid() {
        return UUID.randomUUID().toString();
    }

    public static String getPid() {
        return UUID.randomUUID().toString();
    }

    public static String getCateGoryId() {
        return UUID.randomUUID().toString();
    }

    public static String getCode() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(generateNumberUUID(""));
    }

    public static String generateUID() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 8; i++) {
            result = result + (random.nextInt(9) + 1);
        }
        return result;
    }

    public static String generateNumberUUID(String type) {
        String no = "";
        int[] num = new int[9];
        int c = 0;
        for (int i = 0; i < 9; i++) {
            num[i] = (new Random()).nextInt(10) + 1;
            c = num[i];
            for (int j = 0; j < i; j++) {
                if (num[j] == c) {
                    i--;
                    break;
                }
            }
        }
        if (num.length > 0) {
            for (int i = 0; i < num.length; i++) {
                no = no + num[i];
            }
        }
        if (type.equals("ADMIN_ID")) {
            String wanl = "ADMIN";
            return wanl = wanl + no;
        }
        if (type.equals("DANCE_TEACHER_ID")) {
            String wanl = "DANCETEACHER";
            return wanl = wanl + no;
        }
        if (type.equals("DANCE_TRAINING_ID")) {
            String wanl = "DANCETRAINING";
            return wanl = wanl + no;
        }
        return no + "" +System.currentTimeMillis();
    }
}
