package com.topshow.entity;

import org.apache.ibatis.type.Alias;

@Alias("teacher")
public class Teacher {
    private int id;

    private String teacherName;

    private String teacherDance;

    private String teacherPcPhoto;

    private String teacherModPhoto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherDance() {
        return teacherDance;
    }

    public void setTeacherDance(String teacherDance) {
        this.teacherDance = teacherDance;
    }

    public String getTeacherPcPhoto() {
        return teacherPcPhoto;
    }

    public void setTeacherPcPhoto(String teacherPcPhoto) {
        this.teacherPcPhoto = teacherPcPhoto;
    }

    public String getTeacherModPhoto() {
        return teacherModPhoto;
    }

    public void setTeacherModPhoto(String teacherModPhoto) {
        this.teacherModPhoto = teacherModPhoto;
    }


    public Teacher(int id, String tercherName, String teacherDance, String teacherPcPhoto, String teacherModPhoto) {
        this.id = id;
        this.teacherName = tercherName;
        this.teacherDance = teacherDance;
        this.teacherPcPhoto = teacherPcPhoto;
        this.teacherModPhoto = teacherModPhoto;
    }

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                ", teacherDance='" + teacherDance + '\'' +
                ", teacherPcPhoto='" + teacherPcPhoto + '\'' +
                ", teacherModPhoto='" + teacherModPhoto + '\'' +
                '}';
    }
}
