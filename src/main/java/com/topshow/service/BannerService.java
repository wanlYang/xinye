package com.topshow.service;

import com.topshow.entity.Banner;

import java.util.List;

public interface BannerService {


    List<Banner> getAllList();

    Integer addBanner(Banner banner);

    Integer delete(String id);

    Integer editBanner(Banner banner);
}
