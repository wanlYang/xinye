package com.topshow.controller;

import com.topshow.entity.Banner;
import com.topshow.entity.Result;
import com.topshow.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
