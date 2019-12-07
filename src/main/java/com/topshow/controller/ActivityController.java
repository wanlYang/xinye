package com.topshow.controller;


import com.topshow.entity.Activity;
import com.topshow.entity.Result;
import com.topshow.service.ActivityService;
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
@RequestMapping("/admin/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    //查询所活动
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Result selectAllActivity(){
        List<Activity> activities=activityService.selectAllActivity();
        for (Activity activity:activities) {
            activity.setActiveContent(BackStageInformationController.delHTMLTag(activity.getActiveContent()));
        }
        if (activities!=null)
            return new Result(200,"查询成功",0,activities);
        else
            return new Result(-1,"查询失败",0,null);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public Result get(String id){
        Activity activity=activityService.get(id);
        if (activity!=null)
            return new Result(200,"查询成功",0,activity);
        else
            return new Result(-1,"查询失败",0,activity);
    }

    //修改活动信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result updateActivity(Activity activity){
        int x =activityService.updateActivity(activity);
        if (x>0)
            return  new Result(200,"修改成功",0,x);
        else
            return  new Result(-1,"修改失败",0,x);
    }


    //删除活动信息
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result deleteActivity(int id){
        int x = activityService.deleteActivity(id);
        if (x>0) {
            return new Result(200,"删除成功",0,x);
        }else
            return new Result(-1,"删除失败",0,x);
    }

    //添加活动数据
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Object InsertActivity(Activity activity){
        int x = activityService.insertActivity(activity);
        if (x>0)
            return  new Result(200,"添加成功",0,x);
        else
            return  new Result(-1,"添加失败",0,x);
    }

    @ResponseBody
    @RequestMapping(value = {"/upload/img"}, method = {RequestMethod.POST})
    public Map<String, Object> changeImg(HttpServletRequest request, @RequestParam("file") MultipartFile file, HttpSession session)
            throws IllegalStateException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        Result changeImgFunction = changeImgFunction(request, file, 0);
        map.put("code", Integer.valueOf(0));
        map.put("msg", "上传成功!");
        Map<String, Object> mapData = new HashMap<String, Object>();
        String fileName = (String) changeImgFunction.getData();
        mapData.put("src", request.getContextPath() + fileName.split(",")[0]);
        mapData.put("title", fileName.split(",")[1]);
        mapData.put("src_save", fileName.split(",")[0]);
        map.put("data", mapData);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/upload/content/img"}, method = {RequestMethod.POST})
    public Map<String, Object> changeImgContent(HttpServletRequest request, @RequestParam("upload_file") MultipartFile file, HttpSession session)
            throws IllegalStateException, IOException {
        Result changeImgFunction = changeImgFunction(request, file, 1);
        Map<String, Object> map_data = new HashMap<String, Object>();
        map_data.put("success", Boolean.valueOf(true));
        map_data.put("msg", "上传成功!");
        String url = (String) changeImgFunction.getData();
        map_data.put("file_path", url.split(",")[0]);
        map_data.put("title", url.split(",")[1]);
        return map_data;
    }

    public Result changeImgFunction(HttpServletRequest request, MultipartFile file, Integer url)
            throws IllegalStateException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String rootPath = request.getServletContext().getRealPath("/admin/uploads/");
        String res = sdf.format(new Date());
        String originalFilename = file.getOriginalFilename();
        String newFileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));
        File newFile = new File(rootPath + File.separator + "activity" + File.separator + newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(newFile);
            if (url.intValue() == 1) {
                String fileUrl = "/admin/uploads/activity/" + newFileName;
                return new Result(200, "上传成功!", 0, request.getScheme() + "://" + request.getServerName() + request.getContextPath() + fileUrl + "," + newFileName);
            }
            String fileUrl = "/admin/uploads/activity/" + newFileName;
            return new Result(200, "上传成功!", 0, fileUrl + "," + newFileName);
        } catch (Exception e) {
            return null;
        }
    }

}
