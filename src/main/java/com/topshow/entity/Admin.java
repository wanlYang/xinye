package com.topshow.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 管理员实体类
 * 
 * @author Administrator
 *
 */
public class Admin {
    //id
    private String id;
    //管理员用户名
    private String name;
    //管理员密码
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
