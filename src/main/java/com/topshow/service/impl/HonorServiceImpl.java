package com.topshow.service.impl;

import com.topshow.entity.Honor;
import com.topshow.mapper.HonorMapper;
import com.topshow.service.HonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HonorServiceImpl implements HonorService {

    @Autowired
    private HonorMapper honorMapper;

    @Override
    public List<Honor> selectAllHonor() {
        return honorMapper.selectAllHonor();
    }

    @Override
    public int updateHonor(Honor honor) {
        return honorMapper.updateHonor(honor);
    }

    @Override
    public int deleteHonor(int id) {
        return honorMapper.deleteHonor(id);
    }

    @Override
    public int insertHonor(String honorName, String honorPhoto) {
        return honorMapper.insertHonor(honorName,honorPhoto);
    }
}
