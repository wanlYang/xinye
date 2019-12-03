package com.topshow.entity;

import org.apache.ibatis.type.Alias;

/**
 * banner导航图
 */
@Alias("banner")
public class Banner {
    private int id;

    private String bannerName;

    private String bannerMol;

    private String bannerPc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getBannerMol() {
        return bannerMol;
    }

    public void setBannerMol(String bannerMol) {
        this.bannerMol = bannerMol;
    }

    public String getBannerPc() {
        return bannerPc;
    }

    public void setBannerPc(String bannerPc) {
        this.bannerPc = bannerPc;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", bannerName='" + bannerName + '\'' +
                ", bannerMol='" + bannerMol + '\'' +
                ", bannerPc='" + bannerPc + '\'' +
                '}';
    }

    public Banner() {
    }

    public Banner(int id, String bannerName, String bannerMol, String bannerPc) {
        this.id = id;
        this.bannerName = bannerName;
        this.bannerMol = bannerMol;
        this.bannerPc = bannerPc;
    }
}
