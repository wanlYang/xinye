package com.topshow.service.impl;

import com.topshow.entity.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topshow.entity.Admin;
import com.topshow.mapper.AdminMapper;
import com.topshow.service.AdminService;
import com.topshow.utils.Base64Util;
import com.topshow.utils.MD5Util;

/**
 * 管理员操作服务实现类
 * @author Administrator
 *
 */
@Service
public class AdminServiceImpl implements AdminService{
    
    
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Result login(String admin_name, String admin_password) {
        if(!StringUtils.isNotBlank(admin_name) || !StringUtils.isNotBlank(admin_password)) {
            return new Result(-1,"用户名或密码不能为空!",0,null);
        }
        Admin admin = adminMapper.findAdminByNameAndPassword(admin_name,Base64Util.decoder(MD5Util.MD5Encode(admin_password)));
        if (admin == null) {
            return new Result(-2,"用户名或密码错误!",0,null);
        }
        return new Result(200,"管理员账号登录成功!",1,admin);
    }
    
    

}
