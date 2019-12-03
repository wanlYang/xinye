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
    private String adminname;
    //管理员密码
    private String password;
    //管理员电话
    private String phone;
    //管理员邮箱
    private String email;
    //管理员实际状态:1 = 异常;0 = 正常
    private Integer status;
    //注册日期/管理员账号开始生效日期
    private Date forceTime;
    //管理员上次登录时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAdminname() {
        return adminname;
    }
    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    public Date getForceTime() {
        return forceTime;
    }
    public void setForceTime(Date forceTime) {
        this.forceTime = forceTime;
    }
    @Override
    public String toString() {
        return "Admin [id=" + id + ", adminname=" + adminname + ", password=" + password + ", phone=" + phone
                + ", email=" + email + ", status=" + status + ", forceTime=" + forceTime + ", lastLoginTime="
                + lastLoginTime + "]";
    }

}
