package com.topshow.interceptor;

import com.topshow.constant.TopShowConstant;
import com.topshow.entity.Admin;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.topshow.entity.Result;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 管理员登陆拦截器
 * @author Administrator
 *
 */
public class AuthInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(TopShowConstant.ADMIN_SESSION);
        if (admin != null) {
            return true;
        }
        if (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            String unauthorized = Result.failed(Integer.valueOf(401), "请登录!");
            writer.write(unauthorized);
            writer.close();
            return false;
        }
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
