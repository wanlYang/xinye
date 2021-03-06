package com.topshow.controller;

import com.topshow.entity.Banner;
import com.topshow.entity.Result;
import com.topshow.service.BannerService;
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

/**
 * 后台banner控制器
 */
@Controller
@RequestMapping("/admin/banner")
public class BackStageBannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/list/get/submit",method = RequestMethod.POST)
    @ResponseBody
    private Result bannerList(){
        Result result = new Result();
        List<Banner> banners = bannerService.getAllList();
        result.setStatus(200);
        result.setMessage("获取成功!");
        result.setCount(banners.size());
        result.setData(banners);
        return  result;
    }

    @RequestMapping(value = "/add/submit",method = RequestMethod.POST)
    @ResponseBody
    private Result addBanner(Banner banner){
        Result result = new Result();
        Integer row = bannerService.addBanner(banner);
        result.setStatus(200);
        result.setMessage("添加成功!");
        result.setCount(row);
        result.setData(banner);
        return  result;
    }

    @RequestMapping(value = "/edit/submit",method = RequestMethod.POST)
    @ResponseBody
    private Result editBanner(Banner banner){
        Result result = new Result();
        Integer row = bannerService.editBanner(banner);
        result.setStatus(200);
        result.setMessage("编辑成功!");
        result.setCount(row);
        result.setData(banner);
        return  result;
    }

    @RequestMapping(value = "/delete/submit",method = RequestMethod.POST)
    @ResponseBody
    private Result delete(String id){
        Result result = new Result();
        Integer row = bannerService.delete(id);
        result.setStatus(200);
        result.setMessage("删除成功!");
        result.setCount(row);
        result.setData(row);
        return  result;
    }

    @ResponseBody
    @RequestMapping(value = { "/upload/img" }, method = { RequestMethod.POST })
    public Map<String, Object> changeImg(HttpServletRequest request, @RequestParam("file") MultipartFile file,String flag, HttpSession session)
            throws IllegalStateException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String rootPath = request.getServletContext().getRealPath("/admin/uploads");
        String res = sdf.format(new Date());
        String originalFilename = file.getOriginalFilename();
        String newFileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));
        File newFile = new File(rootPath + File.separator + "banner" + File.separator + newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        file.transferTo(newFile);
        String fileUrl = "/admin/uploads/banner/" + newFileName;

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
