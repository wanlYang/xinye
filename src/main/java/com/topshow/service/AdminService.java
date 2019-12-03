package com.topshow.service;

import com.topshow.entity.Result;

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

}
