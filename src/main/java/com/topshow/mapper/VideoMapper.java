package com.topshow.mapper;

import com.topshow.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface VideoMapper {

    List<Video> selectAllVideo();

    int insertVideo(@Param("videoName") String videoName, @Param("videoLink") String videoLink, @Param("videoPhoto") String videoPhoto, @Param("teacherName") String teacherName, @Param("videoTime") Date videoTime, @Param("person") String person);

    int updateVideo(Video video);

    int deleteVideo(int id);
}
