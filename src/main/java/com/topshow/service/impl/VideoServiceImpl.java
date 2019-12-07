package com.topshow.service.impl;

import com.topshow.entity.Video;
import com.topshow.mapper.VideoMapper;
import com.topshow.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> selectAllVideo() {
        return videoMapper.selectAllVideo();
    }

    @Override
    public int insertVideo(String videoName, String videoLink, String videoPhoto, String teacherName, Date videoTime, String person) {
        return videoMapper.insertVideo(videoName,videoLink,videoPhoto,teacherName,videoTime,person);
    }

    @Override
    public int updateVideo(Video video) {
        return videoMapper.updateVideo(video);
    }

    @Override
    public int deleteVideo(int id) {
        return videoMapper.deleteVideo(id);
    }
}
