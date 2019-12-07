package com.topshow.controller;


import com.topshow.entity.Result;
import com.topshow.entity.Teacher;
import com.topshow.service.TeacherService;
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
@RequestMapping("/admin/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    //全部查找教练数据
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Result SelectAllTeacher(Integer page, Integer limit){
        List<Teacher> teacher = teacherService.selectAllTeacher(page,limit);
        if (teacher!=null)
            return  new Result(200,"删除成功",teacherService.getCount(),teacher);
        else
            return  new Result(-1,"失败!@",0,teacher);
    }

    //添加教练数据
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Object InsertTeacher(String teacherName,String teacherDance,String teacherPcPhoto,String teacherModPhoto){
        int x = teacherService.insertTeacher(teacherName,teacherDance,teacherPcPhoto,teacherModPhoto);
        if (x>0)
            return  new Result(200,"添加成功",0,x);
        else
            return  new Result(-1,"添加失败",0,x);
    }

    //修改教练信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object UpdateTeacher(int id, String teacherName,String teacherDance,String teacherPcPhoto,String teacherModPhoto){
        int x = teacherService.updateTeacher(id,teacherName,teacherDance,teacherPcPhoto,teacherModPhoto);
        if (x>0)
            return  new Result(200,"修改成功",0,x);
        else
            return  new Result(-1,"修改失败",0,x);
    }

    //删除教练
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result DeleteTeacher(int id){
        int x = teacherService.deleteTeacher(id);
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
        File newFile = new File(rootPath + File.separator + "teacher" + File.separator + newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        file.transferTo(newFile);
        String fileUrl = "/admin/uploads/teacher/" + newFileName;

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
