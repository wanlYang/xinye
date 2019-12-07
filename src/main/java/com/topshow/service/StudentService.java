package com.topshow.service;

import com.topshow.entity.Student;

import java.util.List;

public interface StudentService {


    /**
     * 获取所有学员风采
     * @param page
     * @param limit
     * @return
     */
    List<Student> getAllStudentByPageAndLimit(Integer page, Integer limit);

    /**
     * 添加学员
     * @param student
     * @return
     */
    Integer insert(Student student);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    Student get(String id);

    /**
     * 更新
     * @param student
     * @return
     */
    Integer update(Student student);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer del(String id);

    /**
     * 获取总数
     * @return
     */
    Integer getCount();
}
