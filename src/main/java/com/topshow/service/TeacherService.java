package com.topshow.service;

import com.topshow.entity.Teacher;

import java.util.List;

public interface TeacherService {

    //全部查找教练数据
    List<Teacher> selectAllTeacher(Integer page, Integer limit);

    //添加教练数据
    int insertTeacher(String teacherName, String teacherDance, String teacherPcPhoto, String teacherModPhoto);

    //修改教练资料
    int updateTeacher(int id, String teacherName, String teacherDance, String teacherPcPhoto, String teacherModPhoto);

    //删除教练
    int deleteTeacher(int id);

    /**
     * 获取数量
     * @return
     */
    Integer getCount();
}
