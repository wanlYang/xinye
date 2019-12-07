package com.topshow.controller;


import com.topshow.entity.Result;
import com.topshow.entity.Video;
import com.topshow.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    //全部查找视频
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Object SelectAllTeacher(){
        List<Video> video = videoService.selectAllVideo();
        if (video!=null)
            return  new Result(200,"查询成功",0,video);
        else
            return  new Result(-1,"查询失败",0,video);
    }

    //添加视频
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Object InsertVideo(String videoName,String videoLink,String videoPhoto,String teacherName,String person){

        int x = videoService.insertVideo(videoName,videoLink,videoPhoto,teacherName,new Date(),person);
        if (x>0)
            return  new Result(200,"添加成功",0,x);
        else
            return  new Result(-1,"添加失败",0,x);
    }

    //修改视频信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result UpdateVideo(Video video){
        int x = videoService.updateVideo(video);
        if (x>0)
            return  new Result(200,"修改成功",0,x);
        else
            return  new Result(-1,"修改失败",0,x);
    }

    //删除视频
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result DeleteVideo(int id){
        int x = videoService.deleteVideo(id);
        if (x>0) {
            return new Result(200,"删除成功",0,x);
        }else
            return new Result(-1,"删除失败",0,x);
    }

    @ResponseBody
    @RequestMapping(value = { "/upload/img" }, method = { RequestMethod.POST })
    public Map<String, Object> changeImg(HttpServletRequest request, @RequestParam("file") MultipartFile file, String flag, HttpSession session)
            throws IllegalStateException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String rootPath = request.getServletContext().getRealPath("/admin/uploads");
        String res = sdf.format(new Date());
        String originalFilename = file.getOriginalFilename();
        String newFileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));
        File newFile = new File(rootPath + File.separator + "video" + File.separator + newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        file.transferTo(newFile);
        String fileUrl = "/admin/uploads/video/" + newFileName;

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("code", Integer.valueOf(0));
        map.put("msg", "上传成功!");
        Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("src", request.getContextPath() + fileUrl);
        mapData.put("title", newFileName);
        map.put("data", mapData);
        return map;
    }


}
