package com.topshow.mapper;

import com.topshow.entity.Cources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface CourcesMapper {

    List<Cources> selectAllClass();

    int deleteClass(int id);

    int insertClass(@Param("className") String className, @Param("classTime") Date classTime, @Param("classPhoto") String classPhoto);

    int updateClass(Cources cources);
}
