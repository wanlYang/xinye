package com.topshow.mapper;

import com.topshow.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper {

    //全部查找教练数据
    List<Teacher> selectAllTeacher(@Param("start") Integer start, @Param("limit") Integer limit);

    //添加教练数据
    int insertTeacher(@Param("teacherName") String teacherName, @Param("teacherDance") String teacherDance, @Param("teacherPcPhoto") String teacherPcPhoto, @Param("teacherModPhoto") String teacherModPhoto);

    //修改教练数据
    int updateTeacher(@Param("id") int id, @Param("teacherName") String teacherName, @Param("teacherDance") String teacherDance, @Param("teacherPcPhoto") String teacherPcPhoto, @Param("teacherModPhoto") String teacherModPhoto);

    //删除教练
    int deleteTeacher(int id);

    /**
     * 获取数量
     * @return
     */
    Integer getCount();
}
