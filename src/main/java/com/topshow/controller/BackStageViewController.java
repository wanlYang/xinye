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
    
    /**
     * 后台舞蹈培训师团队列表
     * @return
     */
    @RequestMapping(value = "/danceteacher/list",method = RequestMethod.GET)
    public String teacherList() {
        
        
        return "admin/teacher/teacher_list";
    }
    /**
     * 后台舞蹈培训师添加页面
     * @return
     */
    @RequestMapping(value = "/danceteacher/add",method = RequestMethod.GET)
    public String addTeacher() {
        
        
        return "admin/teacher/add_teacher";
    }
    
    /**
     * 后台舞蹈培训师编辑页面
     * @return
     */
    @RequestMapping(value = "/danceteacher/edit",method = RequestMethod.GET)
    public String editTeacher() {
        
        
        return "admin/teacher/edit_teacher";
    }
    
    /**
     * 后台舞蹈培训项目列表页面
     * @return
     */
    @RequestMapping(value = "/dancetraining/list",method = RequestMethod.GET)
    public String danceTraining() {
        return "admin/training/training_list";
    }
    
    /**
     * 后台舞蹈培训项目添加页面
     * @return
     */
    @RequestMapping(value = "/dancetraining/add",method = RequestMethod.GET)
    public String addTraining() {
        return "admin/training/add_training";
    }
    
    /**
     * 后台舞蹈培训项目编辑页面
     * @return
     */
    @RequestMapping(value = "/dancetraining/edit",method = RequestMethod.GET)
    public String editTraining() {
        return "admin/training/edit_training";
    }
    
    /**
     * 后台跳转banner列表
     * @return
     */
    @RequestMapping(value = "/banner/list",method = RequestMethod.GET)
    public String bannerList() {
        
        
        return "admin/banner/banner_list";
    }
    
    /**
     * 添加Banner
     * @return
     */
    @RequestMapping(value = "/banner/add",method = RequestMethod.GET)
    public String addBanner() {
        return "admin/banner/add_banner";
    }
    
    /**
     * 后台跳转视频编辑列表
     * @return
     */
    @RequestMapping(value = "/video/list",method = RequestMethod.GET)
    public String videoList() {
        return "admin/video/video_list";
    }
    
    /**
     * 添加视频页面
     * @return
     */
    @RequestMapping(value = "/video/add",method = RequestMethod.GET)
    public String addVideo() {
        return "admin/video/add_video";
    }
    
    @RequestMapping(value = "/cources/list",method = RequestMethod.GET)
    public String courcesList() {
        return "admin/cources/cources_list";
    }
    
    @RequestMapping(value = "/cources/add",method = RequestMethod.GET)
    public String addCources() {
        return "admin/cources/add_cources";
    }
    
    @RequestMapping(value = "/about/page",method = RequestMethod.GET)
    public String about() {
        return "admin/about/edit_about";
    }
    
    @RequestMapping(value = "/suggestion/list",method = RequestMethod.GET)
    public String suggestionList() {
        
        return "admin/suggestion/suggestion_list";
    }
    
    @RequestMapping(value = "/news/list",method = RequestMethod.GET)
    public String newsList() {
        
        return "admin/news/news_list";
    }
    
    @RequestMapping(value = "/news/add",method = RequestMethod.GET)
    public String addNews() {
        
        return "admin/news/add_news";
    }
    
    @RequestMapping(value = "/news/edit",method = RequestMethod.GET)
    public String editNews() {
        
        return "admin/news/edit_news";
    }
}
