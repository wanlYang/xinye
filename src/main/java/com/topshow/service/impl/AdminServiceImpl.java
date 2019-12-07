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

import java.util.List;

import static com.topshow.utils.MD5Util.MD5Encode;

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
        Admin admin = adminMapper.findAdminByNameAndPassword(admin_name,Base64Util.decoder(MD5Encode(admin_password)));
        if (admin == null) {
            return new Result(-2,"用户名或密码错误!",0,null);
        }
        return new Result(200,"管理员账号登录成功!",1,admin);
    }

    /**
     * 添加
     *
     * @param admin
     * @return
     */
    @Override
    public Integer insert(Admin admin) {

        admin.setPassword(Base64Util.decoder(MD5Encode(admin.getPassword())));
        return adminMapper.insert(admin);
    }

    /**
     * 获取全部
     *
     * @return
     */
    @Override
    public List<Admin> findAll() {

        return adminMapper.getAllAdmin();
    }

    /**
     * 编辑
     *
     * @param admin
     * @return
     */
    @Override
    public Integer update(Admin admin) {

        admin.setPassword(Base64Util.decoder(MD5Encode(admin.getPassword())));
        return adminMapper.update(admin);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return adminMapper.delete(Integer.valueOf(id));
    }
}
