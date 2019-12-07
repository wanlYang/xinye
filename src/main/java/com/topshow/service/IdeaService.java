package com.topshow.service;

import com.topshow.entity.Idea;

import java.util.List;

public interface IdeaService {
    List<Idea> selectAllIdea();

    int updateIdea(Idea idea);

    int deleteIdea(int id);

    int insertIdea(Idea idea);
}
