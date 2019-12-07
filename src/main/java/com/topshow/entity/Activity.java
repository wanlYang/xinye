package com.topshow.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("activity")
public class Activity {
    private int id;
    private String activeName;
    private  String activePhoto;
    private String activeTime;
    private String activeContent;
    /**
     * 1--已发布
     * 2--未发布
     */
    private String activeState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    public String getActivePhoto() {
        return activePhoto;
    }

    public void setActivePhoto(String activePhoto) {
        this.activePhoto = activePhoto;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getActiveContent() {
        return activeContent;
    }

    public void setActiveContent(String activeContent) {
        this.activeContent = activeContent;
    }

    public String getActiveState() {
        return activeState;
    }

    public void setActiveState(String activeState) {
        this.activeState = activeState;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activeName='" + activeName + '\'' +
                ", activePhoto='" + activePhoto + '\'' +
                ", activeTime=" + activeTime +
                ", activeContent='" + activeContent + '\'' +
                ", activeState='" + activeState + '\'' +
                '}';
    }
}

