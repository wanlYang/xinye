package com.topshow.controller;

import javax.servlet.http.HttpSession;

import com.topshow.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topshow.constant.TopShowConstant;
import com.topshow.entity.Admin;
import com.topshow.service.AdminService;

import java.util.List;

/**
 * 后台管理员登陆
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    /**
     * 后台管理员登陆提交处理
     */
    @RequestMapping(value = "/login/submit",method = RequestMethod.POST)
    @ResponseBody
    public Result login(String admin_name, String admin_password, HttpSession session) {
        Result result = null;
        result = adminService.login(admin_name,admin_password);
        if (result.getStatus().intValue() == 200) {
            session.setAttribute(TopShowConstant.ADMIN_SESSION, (Admin)result.getData());
        }
        session.setMaxInactiveInterval(600);
        return result;
    }
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public Result insert(Admin admin) {
        Result result = new Result();
        Integer row = adminService.insert(admin);
        result.setStatus(200);
        result.setMessage("添加成功!");
        result.setCount(row);
        result.setData(row);
        return result;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(Admin admin) {
        Result result = new Result();
        Integer row = adminService.update(admin);
        result.setStatus(200);
        result.setMessage("编辑成功!");
        result.setCount(row);
        result.setData(row);
        return result;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(String id) {
        Result result = new Result();
        Integer row = adminService.delete(id);
        result.setStatus(200);
        result.setMessage("删除成功!");
        result.setCount(row);
        result.setData(row);
        return result;
    }

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    @ResponseBody
    public Result getList() {
        Result result = new Result();
        List<Admin> admins = adminService.findAll();
        result.setStatus(200);
        result.setMessage("获取成功!");
        result.setCount(admins.size());
        result.setData(admins);
        return result;
    }

}
