package com.topshow.entity;

import org.apache.ibatis.type.Alias;

@Alias("honor")
public class Honor {
    private int id;
    private String honorName;
    private String honorPhoto;

    public Honor(int id, String honorName, String honorPhoto) {
        this.id = id;
        this.honorName = honorName;
        this.honorPhoto = honorPhoto;
    }

    public Honor(String honorName) {
        this.honorName = honorName;
    }

    public Honor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public String getHonorPhoto() {
        return honorPhoto;
    }

    public void setHonorPhoto(String honorPhoto) {
        this.honorPhoto = honorPhoto;
    }


    @Override
    public String toString() {
        return "Honor{" +
                "id=" + id +
                ", honorName='" + honorName + '\'' +
                ", honorPhoto='" + honorPhoto + '\'' +
                '}';
    }
}
