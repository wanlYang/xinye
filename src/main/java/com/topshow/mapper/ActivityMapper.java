package com.topshow.mapper;

import com.topshow.entity.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ActivityMapper {

    int insertActivity(Activity activity);

    int deleteActivity(int id);

    int updateActivity(Activity activity);

    List<Activity> selectAllActivity();

    Activity get(String id);
}
