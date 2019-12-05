package com.topshow.service.impl;

import com.topshow.entity.Student;
import com.topshow.mapper.StudentMapper;
import com.topshow.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 获取所有学员风采
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Student> getAllStudentByPageAndLimit(Integer page, Integer limit) {
        List<Student> students = null;
        if (page != null && limit != null) {
            students = this.studentMapper.findAllListByPage(Integer.valueOf((page.intValue() - 1) * limit.intValue()), limit);
        }
        return (students != null) ? students : null;
    }

    /**
     * 添加学员
     *
     * @param student
     * @return
     */
    @Override
    public Integer insert(Student student) {

        return studentMapper.insert(student);
    }

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    @Override
    public Student get(String id) {

        return studentMapper.findById(id);
    }

    /**
     * 更新
     *
     * @param student
     * @return
     */
    @Override
    public Integer update(Student student) {


        return studentMapper.update(student);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer del(String id) {


        return studentMapper.del(id);
    }
}
