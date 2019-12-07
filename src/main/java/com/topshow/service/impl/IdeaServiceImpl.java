package com.topshow.service.impl;

import com.topshow.entity.Idea;
import com.topshow.mapper.IdeaMapper;
import com.topshow.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IdeaServiceImpl implements IdeaService {

    @Autowired
    private IdeaMapper ideaMapper;

    @Override
    public List<Idea> selectAllIdea() {
        return ideaMapper.selectAllIdea();
    }

    @Override
    public int updateIdea(Idea idea) {
        return ideaMapper.updateIdea(idea);
    }

    @Override
    public int deleteIdea(int id) {
        return ideaMapper.deleteIdea(id);
    }

    @Override
    public int insertIdea(Idea idea) {
        return ideaMapper.insertIdea(idea);
    }
}
