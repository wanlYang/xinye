package com.topshow.service.impl;

import com.topshow.entity.Teacher;
import com.topshow.mapper.TeacherMapper;
import com.topshow.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    //全部查找教练数据
    @Override
    public List<Teacher> selectAllTeacher(Integer page, Integer limit) {
        List<Teacher> teachers = null;
        if (page != null && limit != null) {
            teachers = this.teacherMapper.selectAllTeacher(Integer.valueOf((page.intValue() - 1) * limit.intValue()), limit);
        }
        return (teachers != null) ? teachers : null;
    }

    //添加教练数据
    @Override
    public int insertTeacher(String teacherName, String teacherDance, String teacherPcPhoto, String teacherModPhoto) {
        return teacherMapper.insertTeacher(teacherName,teacherDance,teacherPcPhoto,teacherModPhoto);
    }

    //修改教练数据
    @Override
    public int updateTeacher(int id, String teacherName, String teacherDance, String teacherPcPhoto, String teacherModPhoto) {
        return teacherMapper.updateTeacher(id,teacherName,teacherDance,teacherPcPhoto,teacherModPhoto);
    }

    //删除教练
    @Override
    public int deleteTeacher(int id) {
        return teacherMapper.deleteTeacher(id);
    }

    /**
     * 获取数量
     *
     * @return
     */
    @Override
    public Integer getCount() {

        return teacherMapper.getCount();
    }
}
