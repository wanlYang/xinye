package com.topshow.controller;

import com.topshow.entity.Information;
import com.topshow.entity.Result;
import com.topshow.entity.Student;
import com.topshow.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

@Controller
@RequestMapping("/admin/student")
public class BackStageStudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 后台获取资讯列表
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list/get", method = RequestMethod.POST)
    @ResponseBody
    public Result getList(Integer page, Integer limit) {
        Result result = new Result();
        List<Student> students = studentService.getAllStudentByPageAndLimit(page, limit);
        for (Student student:students) {
            student.setContent(BackStageInformationController.delHTMLTag(student.getContent()));
        }

        result.setStatus(200);
        result.setMessage("获取成功@!");
        result.setCount(studentService.getCount());
        result.setData(students);
        return result;
    }

    /**
     * 添加学员风采
     * @param student
     * @return
     */
    @RequestMapping(value = "/add/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Student student) {
        Result result = new Result();
        Integer row = studentService.insert(student);
        result.setStatus(200);
        result.setMessage("添加成功@!");
        result.setCount(row);
        result.setData(row);
        return result;
    }

    /**
     * 编辑
     * @param student
     * @return
     */
    @RequestMapping(value = "/edit/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(Student student) {
        Result result = new Result();
        Integer row = studentService.update(student);
        result.setStatus(200);
        result.setMessage("编辑成功@!");
        result.setCount(row);
        result.setData(row);
        return result;
    }

    /**
     * 删除学员
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result del(String id) {
        Result result = new Result();
        Integer row = studentService.del(id);
        result.setStatus(200);
        result.setMessage("删除成功@!");
        result.setCount(row);
        result.setData(row);
        return result;
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Result get(String id) {
        Result result = new Result();
        Student student = studentService.get(id);
        result.setStatus(200);
        result.setMessage("获取成功@!");
        result.setCount(1);
        result.setData(student);
        return result;
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
        File newFile = new File(rootPath + File.separator + "student" + File.separator + newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(newFile);
            if (url.intValue() == 1) {
                String fileUrl = "/admin/uploads/student/" + newFileName;
                return new Result(200, "上传成功!", 0, request.getScheme() + "://" + request.getServerName() + request.getContextPath() + fileUrl + "," + newFileName);
            }
            String fileUrl = "/admin/uploads/student/" + newFileName;
            return new Result(200, "上传成功!", 0, fileUrl + "," + newFileName);
        } catch (Exception e) {
            return null;
        }
    }
}
