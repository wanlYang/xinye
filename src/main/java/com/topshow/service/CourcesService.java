package com.topshow.service;

import com.topshow.entity.Cources;

import java.util.Date;
import java.util.List;

public interface CourcesService {
    List<Cources> selectAllClass();

    int deleteClass(int id);

    int insertClass(String className, Date classTime, String classPhoto);

    int updateClass(Cources cources);
}
