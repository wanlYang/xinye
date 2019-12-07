package com.topshow.mapper;

import com.topshow.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {


    List<Student> findAllListByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    Integer insert(Student student);

    Student findById(String id);

    Integer update(Student student);

    Integer del(String id);

    Integer getCount();
}
