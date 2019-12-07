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

    @RequestMapping(value = "/dancetraining/list",method = RequestMethod.GET)
    public String dance() {
        return "admin/training/training_list";
    }

    @RequestMapping(value = "/dancetraining/add",method = RequestMethod.GET)
    public String danceAdd() {
        return "admin/training/add_training";
    }
    @RequestMapping(value = "/dancetraining/edit",method = RequestMethod.GET)
    public String danceEdit() {
        return "admin/training/edit_training";
    }

    @RequestMapping(value = "/about/list",method = RequestMethod.GET)
    public String aboutPage() {
        return "admin/about/about_list";
    }

    @RequestMapping(value = "/about/edit",method = RequestMethod.GET)
    public String aboutEdit() {
        return "admin/about/edit_about";
    }
    @RequestMapping(value = "danceteacher/list",method = RequestMethod.GET)
    public String teacherList() {
        return "admin/teacher/teacher_list";
    }
    @RequestMapping(value = "danceteacher/add",method = RequestMethod.GET)
    public String teacherAdd() {
        return "admin/teacher/add_teacher";
    }
    @RequestMapping(value = "danceteacher/edit",method = RequestMethod.GET)
    public String teacherEdit() {
        return "admin/teacher/edit_teacher";
    }

    @RequestMapping(value = "video/list",method = RequestMethod.GET)
    public String videoList() {
        return "admin/video/video_list";
    }
    @RequestMapping(value = "video/add",method = RequestMethod.GET)
    public String videoAdd() {
        return "admin/video/add_video";
    }

    @RequestMapping(value = "video/edit",method = RequestMethod.GET)
    public String videoEdit() {
        return "admin/video/edit_video";
    }
    @RequestMapping(value = "cources/list",method = RequestMethod.GET)
    public String courcesList() {
        return "admin/cources/cources_list";
    }
    @RequestMapping(value = "cources/add",method = RequestMethod.GET)
    public String courcesAdd() {
        return "admin/cources/add_cources";
    }
    @RequestMapping(value = "cources/edit",method = RequestMethod.GET)
    public String courcesEdit() {
        return "admin/cources/edit_cources";
    }
    @RequestMapping(value = "honor/list",method = RequestMethod.GET)
    public String honorList() {
        return "admin/honor/honor_list";
    }
    @RequestMapping(value = "honor/add",method = RequestMethod.GET)
    public String honorAdd() {
        return "admin/honor/add_honor";
    }
    @RequestMapping(value = "honor/edit",method = RequestMethod.GET)
    public String honorEdit() {
        return "admin/honor/edit_honor";
    }
    @RequestMapping(value = "idea/list",method = RequestMethod.GET)
    public String ideaList() {
        return "admin/idea/idea_list";
    }
    @RequestMapping(value = "activity/list",method = RequestMethod.GET)
    public String activityList() {
        return "admin/activity/activity_list";
    }
    @RequestMapping(value = "activity/add",method = RequestMethod.GET)
    public String activityAdd() {
        return "admin/activity/add_activity";
    }
    @RequestMapping(value = "activity/edit",method = RequestMethod.GET)
    public String activityEdit() {
        return "admin/activity/edit_activity";
    }
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String adminList() {
        return "admin/admin/admin_list";
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String adminAdd() {
        return "admin/admin/add_admin";
    }
    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String adminEdit() {
        return "admin/admin/edit_admin";
    }
}
