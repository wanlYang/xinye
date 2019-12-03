package com.topshow.mapper;

import com.topshow.entity.BackStageNav;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BackStageNavMapper {
    List<BackStageNav> getTopLevel(int paramInt);

    List<BackStageNav> getSecondLevel(Integer paramInteger);

    List<BackStageNav> getNav(Integer paramInteger);

    List<BackStageNav> findAllNav(@Param("start") Integer paramInteger1, @Param("limit") Integer paramInteger2);

    List<BackStageNav> findAll();

    Integer findNavCount();

    Integer createNav(BackStageNav paramBackStageNav);

    BackStageNav findNavById(Integer paramInteger);

    Integer delete(Integer paramInteger);

    Integer updateNav(BackStageNav paramBackStageNav);

}

