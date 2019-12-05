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
    @RequestMapping(value = "/banner/add/page",method = RequestMethod.GET)
    public String addBanner() {


        return "admin/banner/add_banner";
    }
    @RequestMapping(value = "/banner/edit/page",method = RequestMethod.GET)
    public String editBanner() {


        return "admin/banner/edit_banner";
    }
    @RequestMapping(value = "/shop/list",method = RequestMethod.GET)
    public String shop() {

        return "admin/shop/shop_list";
    }

    @RequestMapping(value = "/shop/add",method = RequestMethod.GET)
    public String addShop() {

        return "admin/shop/add_shop";
    }
    @RequestMapping(value = "/shop/edit",method = RequestMethod.GET)
    public String editShop() {

        return "admin/shop/edit_shop";
    }

    @RequestMapping(value = "/topshow/information",method = RequestMethod.GET)
    public String topshowInfo() {

        return "admin/information/information_list";
    }

    @RequestMapping(value = "/topshow/information/add",method = RequestMethod.GET)
    public String addTopshowInfo() {

        return "admin/information/add_information";
    }
    @RequestMapping(value = "/topshow/information/edit",method = RequestMethod.GET)
    public String editTopshowInfo() {

        return "admin/information/edit_information";
    }

    @RequestMapping(value = "/student/cadetstyle",method = RequestMethod.GET)
    public String student() {

        return "admin/student/cadetstyle_list";
    }

    @RequestMapping(value = "/student/add",method = RequestMethod.GET)
    public String addStudent() {
        return "admin/student/add_cadetstyle";
    }

    @RequestMapping(value = "/student/edit",method = RequestMethod.GET)
    public String editStudent() {
        return "admin/student/edit_cadetstyle";
    }



}
