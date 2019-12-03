package com.topshow.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.topshow.constant.TopShowConstant;

@Controller
public class UploadController {
	/**
	 * java-sdk 上传到七牛
	 */
	private static Logger logger = LogManager.getLogger(UploadController.class.getName());
	@RequestMapping(value = "/upload/up",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> qiniuUpload(
	        MultipartFile file, HttpServletRequest request
	){
	    String domian = TopShowConstant.QINIU_VIDEO;
        Map<String, String> upload = upload(file,request,domian);
        return upload;
	}
	@RequestMapping(value = "/upload/img/up",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> qiniuUploadImg(
	        MultipartFile file, HttpServletRequest request
	){
	    String domian = TopShowConstant.QINIU_IMG;
	    Map<String, String> upload = upload(file,request,domian);
	    return upload;
	}
	public Map<String,String> upload(MultipartFile file, HttpServletRequest request,String domian) {
	  //设置好账号的ACCESS_KEY和SECRET_KEY
        String ACCESS_KEY = "C2b8fn4-lRQHc0XgTy98wsTXcOoHUqowVukDT6WQ";
        String SECRET_KEY = "1gMkVc99HlF9TDA1DJR9xjUm8D7uuswATml30m4Z";
        //要上传的空间
        String bucketname = "jinyanwudao";
        //上传到七牛后保存的文件名
        String key;
        //上传文件的路径
        String FilePath;
        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        //自动识别要上传的空间的初村区域是华东、华北、华南
        Zone z = Zone.autoZone();
        Configuration  c  = new Configuration(z);
        //创建上传对象
        UploadManager uploadManager = new UploadManager(c);
        //获取upToken
        String upToken = auth.uploadToken(bucketname);
        //开始时间
        long  startTime=System.currentTimeMillis() + 1000;
        logger.info("______________开始时间:={}",startTime);
        Map<String ,String> map = new HashMap<String, String>();
        logger.info("______________文件名:={}",file.getOriginalFilename());
        //获取当前路径
        String uploadPath = request.getServletContext().getRealPath("/admin/uploads");
        logger.info("______________路径:={}",uploadPath);
    
        if (!file.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
            String res = sdf.format(new Date());
            String originalFilename = file.getOriginalFilename();
            String fileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));
            try {
                String path=uploadPath+File.separator+"video"+File.separator+fileName;
                File tempFile = new File(path);
                if (!tempFile.getParentFile().exists()) {
                    tempFile.getParentFile().mkdirs();
                }
                file.transferTo(tempFile);
                key = fileName;
                FilePath = path;
                //上传到七牛
                //调用put方法
                try {
                    Response response = uploadManager.put(FilePath,key,upToken);
                    logger.info("___________________response={}",response.bodyString());
                    map.put("state","1");
                    map.put("info","上传七牛成功!");
                    map.put("fileName",fileName);
                    map.put("qiniuUrl",domian+fileName);
                    
                }catch (QiniuException e){
                    map.put("state","0");
                    map.put("info","上传七牛失败!");
                    Response r = e.response;
                    logger.error("上传七牛异常={}",r.toString());
    
                }finally {
                    //上传七牛完成后删除本地文件
                    File deleteFile = new File(path);
                    if (deleteFile.exists()){
                        deleteFile.delete();
                    }
                }
    
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        //结束时间
        long endTime=System.currentTimeMillis();
        logger.info("______________用时:={}",endTime-startTime+"ms");
	    
        return map;
    }
}

