package com.topshow.service.impl;

import com.topshow.entity.Cources;
import com.topshow.mapper.CourcesMapper;
import com.topshow.service.CourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourcesServiceImpl implements CourcesService {

    @Autowired
    private CourcesMapper courcesMapper;

    @Override
    public List<Cources> selectAllClass() {
        return courcesMapper.selectAllClass();
    }

    @Override
    public int deleteClass(int id) {
        return courcesMapper.deleteClass(id);
    }

    @Override
    public int insertClass(String className, Date classTime, String classPhoto) {
        return courcesMapper.insertClass(className,classTime,classPhoto);
    }

    @Override
    public int updateClass(Cources cources) {
        return courcesMapper.updateClass(cources);
    }
}
