package com.topshow.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.topshow.constant.TopShowConstant;
import com.topshow.entity.Admin;

/**
 * 后台页面跳转控制
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin")
public class BackStageViewController {
    
    
    /**
     * 后台登陆页面跳转
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpSession session) {
    	Admin attribute = (Admin) session.getAttribute(TopShowConstant.ADMIN_SESSION);
        if (attribute == null) {
        	return "admin/login/login";
		}
        return "redirect:/admin/index";
    }
    /**
     * 后台主页面跳转
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        
        
        return "admin/index";
    }
    
    /**
     * 后台main页面跳转
     * @return
     */
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main() {


        return "admin/main";
    }
    @RequestMapping(value = "/banner/list",method = RequestMethod.GET)
    public String banner() {


        return "admin/banner/banner_list";
    }

}
