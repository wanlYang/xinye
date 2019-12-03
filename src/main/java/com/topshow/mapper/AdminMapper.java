package com.topshow.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.topshow.entity.Admin;

/**
 * 管理员数据源操作
 * @author Administrator
 *
 */
@Repository
public interface AdminMapper {

    /**
     * 管理员登陆方法
     * @param admin_name
     * @param admin_password
     * @return
     */
    public Admin findAdminByNameAndPassword(@Param("admin_name")String admin_name, @Param("admin_password")String admin_password);
    
    

}
