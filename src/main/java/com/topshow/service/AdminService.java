package com.topshow.service;

import com.topshow.entity.Admin;
import com.topshow.entity.Result;

import java.util.List;

/**
 * 管理员操作接口
 * @author Administrator
 *
 */
public interface AdminService {

    /**
     * 管理员登陆方法
     * @param admin_name
     * @param admin_password
     * @return
     */
    Result login(String admin_name, String admin_password);

    /**
     * 添加
     * @param admin
     * @return
     */
    Integer insert(Admin admin);

    /**
     * 获取全部
     * @return
     */
    List<Admin> findAll();

    /**
     * 编辑
     * @param admin
     * @return
     */
    Integer update(Admin admin);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer delete(String id);
}
