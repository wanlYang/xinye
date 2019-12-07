package com.topshow.service;

import com.topshow.entity.Video;

import java.util.Date;
import java.util.List;

public interface VideoService {
    List<Video> selectAllVideo();

    int insertVideo(String videoName, String videoLink, String videoPhoto, String teacherName, Date videoTime, String person);

    int updateVideo(Video video);

    int deleteVideo(int id);
}
