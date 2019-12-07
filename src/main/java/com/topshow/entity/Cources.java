package com.topshow.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("cources")
public class Cources {
    private int id;
    private String className;
    private Date classTime;
    private String classPhoto;

    public Cources(int id, String className, Date classTime, String classPhoto) {
        this.id = id;
        this.className = className;
        this.classTime = classTime;
        this.classPhoto = classPhoto;
    }

    public Cources() {

    }

    @Override
    public String toString() {
        return "Cources{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", classTime='" + classTime + '\'' +
                ", classPhoto='" + classPhoto + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getClassTime() {
        return classTime;
    }

    public void setClassTime(Date chassTime) {
        this.classTime = chassTime;
    }

    public String getClassPhoto() {
        return classPhoto;
    }

    public void setClassPhoto(String classPhoto) {
        this.classPhoto = classPhoto;
    }
}
