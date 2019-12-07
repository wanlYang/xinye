package com.topshow.mapper;

import com.topshow.entity.Honor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HonorMapper {

    List<Honor> selectAllHonor();

    int updateHonor(Honor honor);

    int deleteHonor(int id);

    int insertHonor(@Param("honorName") String honorName, @Param("honorPhoto") String honorPhoto);
}
