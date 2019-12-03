package com.topshow.service.impl;

import com.topshow.entity.Banner;
import com.topshow.mapper.BannerMapper;
import com.topshow.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * banner接口实现类
 */
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> getAllList() {


        return bannerMapper.findAll();
    }
}
