package com.topshow.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UtilsResult {
    private Integer status;
    private String message;
    private Integer count;
    private Object data;

    public UtilsResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public UtilsResult() {
    }

    public static String failed() {
        return failed("解析失败");
    }

    public static String failed(String msg) {
        return failed(Integer.valueOf(-1), msg);
    }

    public static String failed(Integer code, String msg) {
        Gson gson = (new GsonBuilder()).disableHtmlEscaping().create();
        return gson.toJson(new UtilsResult(code, msg, Integer.valueOf(0), null));
    }

    public String toString() {
        return "Result [status=" + this.status + ", message=" + this.message + ", count=" + this.count + ", data="
                + this.data + "]";
    }

    public UtilsResult(Integer status, String message, Integer count, Object data) {
        this.status = status;
        this.message = message;
        this.count = count;
        this.data = data;
    }
}
