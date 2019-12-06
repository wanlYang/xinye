package com.topshow.service;

import com.topshow.entity.About;

import java.util.List;

public interface AboutService {


    /**
     * 获取公司简介
     * @param id
     * @return
     */
    About get(String id);

    /**
     * 更新
     * @param about
     * @return
     */
    Integer update(About about);

    /**
     * 获取公司简介列表
     * @return
     */
    List<About> getAll();
}
