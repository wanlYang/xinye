package com.topshow.controller;

import com.topshow.entity.About;
import com.topshow.entity.Result;
import com.topshow.entity.Training;
import com.topshow.service.AboutService;
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
@RequestMapping("/admin/about")
public class BackStageAboutController {

    @Autowired
    private AboutService aboutService;

    /**
     * 获取列表
     * @return
     */
    @RequestMapping(value = "/list/get", method = RequestMethod.POST)
    @ResponseBody
    public Result getList() {
        Result result = new Result();
        List<About> abouts = aboutService.getAll();
        for (About about:abouts) {
            about.setContent(BackStageInformationController.delHTMLTag(about.getContent()));
        }
        result.setStatus(200);
        result.setMessage("获取成功@!");
        result.setCount(abouts.size());
        result.setData(abouts);
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
        About about = aboutService.get(id);
        result.setStatus(200);
        result.setMessage("获取成功@!");
        result.setCount(1);
        result.setData(about);
        return result;
    }

    /**
     * 编辑
     * @param about
     * @return
     */
    @RequestMapping(value = "/edit/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(About about) {
        Result result = new Result();
        Integer row = aboutService.update(about);
        result.setStatus(200);
        result.setMessage("编辑成功@!");
        result.setCount(row);
        result.setData(row);
        return result;
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
        File newFile = new File(rootPath + File.separator + "about" + File.separator + newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(newFile);
            if (url.intValue() == 1) {
                String fileUrl = "/admin/uploads/about/" + newFileName;
                return new Result(200, "上传成功!", 0, request.getScheme() + "://" + request.getServerName() + request.getContextPath() + fileUrl + "," + newFileName);
            }
            String fileUrl = "/admin/uploads/about/" + newFileName;
            return new Result(200, "上传成功!", 0, fileUrl + "," + newFileName);
        } catch (Exception e) {
            return null;
        }
    }

}
