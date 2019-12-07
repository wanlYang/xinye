package com.topshow.service.impl;

import com.topshow.entity.Activity;
import com.topshow.mapper.ActivityMapper;
import com.topshow.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> selectAllActivity() {
        return activityMapper.selectAllActivity();
    }

    @Override
    public int updateActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }

    @Override
    public int deleteActivity(int id) {
        return activityMapper.deleteActivity(id);
    }

    @Override
    public int insertActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    @Override
    public Activity get(String id) {

        return activityMapper.get(id);
    }
}
