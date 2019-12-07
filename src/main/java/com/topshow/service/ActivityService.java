package com.topshow.service;

import com.topshow.entity.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> selectAllActivity();

    int updateActivity(Activity activity);

    int deleteActivity(int id);

    int insertActivity(Activity activity);

    Activity get(String id);
}
