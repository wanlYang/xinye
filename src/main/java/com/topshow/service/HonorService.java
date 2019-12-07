package com.topshow.service;

import com.topshow.entity.Honor;

import java.util.List;

public interface HonorService {
    List<Honor> selectAllHonor();

    int updateHonor(Honor honor);

    int deleteHonor(int id);

    int insertHonor(String honorName, String honorPhoto);

}
