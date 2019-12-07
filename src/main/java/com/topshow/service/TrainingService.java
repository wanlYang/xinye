package com.topshow.service;

import com.topshow.entity.Training;

import java.util.List;

public interface TrainingService {


    /**
     * 获取所有舞种
     * @param page
     * @param limit
     * @return
     */
    List<Training> getAllDanceByPageAndLimit(Integer page, Integer limit);

    /**
     * 添加
     * @param training
     * @return
     */
    Integer insert(Training training);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    Training get(String id);

    /**
     * 修改
     * @param training
     * @return
     */
    Integer update(Training training);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer del(String id);

    /**
     * 获取总数
     * @return
     */
    Integer getCount();
}
