package com.topshow.service;

import com.topshow.entity.Information;

import java.util.List;

public interface InformationService{


    /**
     * 根据获取新晔资讯根据分页数据
     * @param page
     * @param limit
     * @return
     */
    List<Information> getAllInfoByPageAndLimit(Integer page, Integer limit);

    /**
     * 添加
     * @param information
     * @return
     */
    Integer insert(Information information);

    /**
     * 编辑
     * @param information
     * @return
     */
    Integer update(Information information);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    Information get(String id);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer delete(String id);
}
