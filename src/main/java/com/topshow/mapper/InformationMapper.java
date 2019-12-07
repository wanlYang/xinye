package com.topshow.mapper;

import com.topshow.entity.Information;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationMapper {


    List<Information> findAllListByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    Integer insert(Information information);

    Integer update(Information information);

    Information findById(String id);

    Integer delete(String id);

    Integer getCount();
}
