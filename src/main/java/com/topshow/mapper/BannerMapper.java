package com.topshow.mapper;

import com.topshow.entity.Banner;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * banner数据库交互
 */
@Repository
public interface BannerMapper {


    List<Banner> findAll();

    Integer insert(Banner banner);

    Integer delete(String id);

    Integer update(Banner banner);
}
