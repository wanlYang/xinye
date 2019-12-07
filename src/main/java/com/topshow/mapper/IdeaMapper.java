package com.topshow.mapper;

import com.topshow.entity.Idea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface IdeaMapper {
    List<Idea> selectAllIdea();

    int updateIdea(Idea idea);

    int deleteIdea(int id);

    int insertIdea(Idea idea);
}
