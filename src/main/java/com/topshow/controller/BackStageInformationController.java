package com.topshow.controller;

import com.topshow.entity.Information;
import com.topshow.entity.Result;
import com.topshow.service.InformationService;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 后台资讯管理控制器
 * 1、行业动态
 * 2、公司健康
 * 3、健康知识
 */
@Controller
@RequestMapping("/admin/information")
public class BackStageInformationController {

    @Autowired
    private InformationService informationService;

    //将html转换为纯文本，此方法最后保留了&nbps空格，使用时注意将空格替换掉
    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

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
        List<Information> informations = informationService.getAllInfoByPageAndLimit(page, limit);
        for (Information information:informations) {
            information.setContent(delHTMLTag(information.getContent()));
        }

        result.setStatus(200);
        result.setMessage("获取成功@!");
        result.setCount(informations.size());
        result.setData(informations);
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
        Information information = informationService.get(id);
        result.setStatus(200);
        result.setMessage("获取成功@!");
        result.setCount(1);
        result.setData(information);
        return result;
    }

    /**
     * 添加
     * @param information
     * @return
     */
    @RequestMapping(value = "/add/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Information information) {
        Result result = new Result();
        Integer row = informationService.insert(information);
        result.setStatus(200);
        result.setMessage("添加成功@!");
        result.setCount(row);
        result.setData(row);
        return result;
    }

    /**
     * 编辑
     * @param information
     * @return
     */
    @RequestMapping(value = "/edit/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(Information information) {
        Result result = new Result();
        Integer row = informationService.update(information);
        result.setStatus(200);
        result.setMessage("编辑成功@!");
        result.setCount(row);
        result.setData(row);
        return result;
    }

    @RequestMapping(value = "/del/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(String id) {
        Result result = new Result();
        Integer row = informationService.delete(id);
        result.setStatus(200);
        result.setMessage("删除功@!");
        result.setCount(row);
        result.setData(row);
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
        File newFile = new File(rootPath + File.separator + "information" + File.separator + newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(newFile);
            if (url.intValue() == 1) {
                String fileUrl = "/admin/uploads/information/" + newFileName;
                return new Result(200, "上传成功!", 0, request.getScheme() + "://" + request.getServerName() + request.getContextPath() + fileUrl + "," + newFileName);
            }
            String fileUrl = "/admin/uploads/information/" + newFileName;
            return new Result(200, "上传成功!", 0, fileUrl + "," + newFileName);
        } catch (Exception e) {
            return null;
        }
    }
}
