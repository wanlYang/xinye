package com.topshow.service.impl;

import com.topshow.entity.About;
import com.topshow.mapper.AboutMapper;
import com.topshow.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutMapper aboutMapper;

    /**
     * 获取公司简介
     *
     * @param id
     * @return
     */
    @Override
    public About get(String id) {


        return aboutMapper.findById(Integer.valueOf(id));
    }

    /**
     * 更新
     *
     * @param about
     * @return
     */
    @Override
    public Integer update(About about) {


        return aboutMapper.update(about);
    }

    /**
     * 获取公司简介列表
     *
     * @return
     */
    @Override
    public List<About> getAll() {


        return aboutMapper.findAll();
    }
}
