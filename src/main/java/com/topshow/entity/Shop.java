package com.topshow.entity;

import org.apache.ibatis.type.Alias;

@Alias("shop")
public class Shop {

    private Integer id;
    private String shopName;
    private String shopPhoto;
    private String shopAdd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhoto() {
        return shopPhoto;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    public String getShopAdd() {
        return shopAdd;
    }

    public void setShopAdd(String shopAdd) {
        this.shopAdd = shopAdd;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", shopPhoto='" + shopPhoto + '\'' +
                ", shopAdd='" + shopAdd + '\'' +
                '}';
    }
}