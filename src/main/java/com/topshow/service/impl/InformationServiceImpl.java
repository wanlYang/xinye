package com.topshow.service.impl;

import com.topshow.entity.Information;
import com.topshow.mapper.InformationMapper;
import com.topshow.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper informationMapper;
    /**
     * 根据获取新晔资讯根据分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Information> getAllInfoByPageAndLimit(Integer page, Integer limit) {
        List<Information> informationList = null;
        if (page != null && limit != null) {
            informationList = this.informationMapper.findAllListByPage(Integer.valueOf((page.intValue() - 1) * limit.intValue()), limit);
        }
        return (informationList != null) ? informationList : null;
    }

    /**
     * 添加
     *
     * @param information
     * @return
     */
    @Override
    public Integer insert(Information information) {
        information.setTime(new Date());

        return informationMapper.insert(information);
    }

    /**
     * 编辑
     *
     * @param information
     * @return
     */
    @Override
    public Integer update(Information information) {


        return informationMapper.update(information);
    }

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    @Override
    public Information get(String id) {

        return informationMapper.findById(id);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return informationMapper.delete(id);
    }

    /**
     * 获取总数
     *
     * @return
     */
    @Override
    public Integer getCount() {
        return informationMapper.getCount();
    }
}
