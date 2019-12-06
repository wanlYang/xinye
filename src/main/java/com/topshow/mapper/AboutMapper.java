package com.topshow.mapper;

import com.topshow.entity.About;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutMapper {
    About findById(Integer id);

    Integer update(About about);

    List<About> findAll();
}
