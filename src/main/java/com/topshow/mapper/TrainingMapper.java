package com.topshow.mapper;

import com.topshow.entity.Training;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingMapper {


    /**
     * 获取所有舞种
     * @param start
     * @param limit
     * @return
     */
    List<Training> findAllListByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 添加数据
     * @param training
     * @return
     */
    Integer insert(Training training);

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    Training findById(String id);

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
    Integer del(Integer id);

    /**
     * 获取总数
     * @return
     */
    Integer getCount();
}
