package com.topshow.entity;

import org.apache.ibatis.type.Alias;

@Alias("video")
public class Video {
    private int id;
    private String videoName;
    private String videoLink;
    private String videoPhoto;
    private String teacherName;
    private String videoTime;
    /**
     * 1--外教视频
     * 2--教师视频
     * 3--学员视频
     */
    private String person;


    public Video() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getVideoPhoto() {
        return videoPhoto;
    }

    public void setVideoPhoto(String videoPhoto) {
        this.videoPhoto = videoPhoto;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", videoName='" + videoName + '\'' +
                ", videoLink='" + videoLink + '\'' +
                ", videoPhoto='" + videoPhoto + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", videoTime='" + videoTime + '\'' +
                ", person='" + person + '\'' +
                '}';
    }
}
