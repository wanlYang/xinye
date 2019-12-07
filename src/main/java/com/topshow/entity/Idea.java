package com.topshow.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("idea")
public class Idea {
    private  int id;
    private String ideaName;
    private String ideaSex;
    private String ideaCall;
    private String ideaContent;
    private Date ideaTime;

    public Idea(int id, String ideaName, String ideaSex, String ideaCall, String ideaContent, Date ideaTime) {
        this.id = id;
        this.ideaName = ideaName;
        this.ideaSex = ideaSex;
        this.ideaCall = ideaCall;
        this.ideaContent = ideaContent;
        this.ideaTime = ideaTime;
    }

    public Idea() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdeaName() {
        return ideaName;
    }

    public void setIdeaName(String ideaName) {
        this.ideaName = ideaName;
    }

    public String getIdeaSex() {
        return ideaSex;
    }

    public void setIdeaSex(String ideaSex) {
        this.ideaSex = ideaSex;
    }

    public String getIdeaCall() {
        return ideaCall;
    }

    public void setIdeaCall(String ideaCall) {
        this.ideaCall = ideaCall;
    }

    public String getIdeaContent() {
        return ideaContent;
    }

    public void setIdeaContent(String ideaContent) {
        this.ideaContent = ideaContent;
    }

    public Date getIdeaTime() {
        return ideaTime;
    }

    public void setIdeaTime(Date ideaTime) {
        this.ideaTime = ideaTime;
    }



    @Override
    public String toString() {
        return "Idea{" +
                "id=" + id +
                ", ideaName='" + ideaName + '\'' +
                ", ideaSex='" + ideaSex + '\'' +
                ", ideaCall=" + ideaCall +
                ", ideaContent='" + ideaContent + '\'' +
                ", ideaTime=" + ideaTime +
                '}';
    }
}
