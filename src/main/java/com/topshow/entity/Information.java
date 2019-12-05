package com.topshow.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("information")
public class Information {
    private Integer id;
    private String name;
    private String photo;
    private Date time;
    private String content;

    /**
     * 1--行业动态
     * 2--公司新闻
     * 3--健康知识
     */
    private int person;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", person=" + person +
                '}';
    }
}
