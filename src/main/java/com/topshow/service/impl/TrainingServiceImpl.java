package com.topshow.service.impl;

import com.topshow.entity.Training;
import com.topshow.mapper.TrainingMapper;
import com.topshow.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingMapper trainingMapper;

    /**
     * 获取所有舞种
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Training> getAllDanceByPageAndLimit(Integer page, Integer limit) {
        List<Training> trainings = null;
        if (page != null && limit != null) {
            trainings = this.trainingMapper.findAllListByPage(Integer.valueOf((page.intValue() - 1) * limit.intValue()), limit);
        }
        return (trainings != null) ? trainings : null;
    }

    /**
     * 添加
     *
     * @param training
     * @return
     */
    @Override
    public Integer insert(Training training) {


        return trainingMapper.insert(training);
    }

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    @Override
    public Training get(String id) {


        return trainingMapper.findById(id);
    }

    /**
     * 修改
     *
     * @param training
     * @return
     */
    @Override
    public Integer update(Training training) {


        return trainingMapper.update(training);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer del(String id) {


        return trainingMapper.del(Integer.valueOf(id));
    }

    /**
     * 获取总数
     *
     * @return
     */
    @Override
    public Integer getCount() {
        return trainingMapper.getCount();
    }
}
