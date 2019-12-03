package com.topshow.controller;

import com.topshow.entity.BackStageNav;
import com.topshow.entity.Result;
import com.topshow.service.BackStageNavService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/admin/manager/nav" })
public class BackStageNavController {
    @Autowired
    private BackStageNavService backStageNavService;

    @RequestMapping(value = { "/get/top" }, method = { RequestMethod.GET })
    @ResponseBody
    public Result name() {
        Result result = new Result();
        List<BackStageNav> adminNavs = this.backStageNavService.getAllTopLevel();
        result.setStatus(Integer.valueOf(200));
        result.setMessage("获取成功!");
        result.setData(adminNavs);
        return result;
    }

    @RequestMapping(value = { "/get/other" }, method = { RequestMethod.POST })
    @ResponseBody
    public Result other(@RequestParam("menuId") Integer menuId) {
        Result result = new Result();
        List<BackStageNav> adminSecondLevel = this.backStageNavService.getSecondLevel(menuId);
        result.setStatus(Integer.valueOf(200));
        result.setMessage("获取成功!");
        result.setData(adminSecondLevel);
        return result;
    }

}
