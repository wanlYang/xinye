package com.topshow.controller;


import com.topshow.entity.Idea;
import com.topshow.entity.Result;
import com.topshow.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/idea")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;


    //查询所有留言
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public Result selectAllIdea(){
        List<Idea> idea=ideaService.selectAllIdea();
        if (idea!=null)
            return new Result(200,"查询成功",0,idea);
        else
            return new Result(-1,"查询失败",0,idea);
    }

    //修改留言信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result updateIdea(Idea idea){
        int x =ideaService.updateIdea(idea);
        if (x>0)
            return  new Result(200,"修改成功",0,x);
        else
            return  new Result(-1,"修改失败",0,x);
    }


    //删除留言信息
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result deleteStudent(int id){
        int x = ideaService.deleteIdea(id);
        if (x>0) {
            return new Result(200,"删除成功",0,x);
        }else
            return new Result(-1,"删除失败",0,x);
    }

    //添加留言数据
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Object InsertActivity(Idea idea){
        int x = ideaService.insertIdea(idea);
        if (x>0)
            return  new Result(200,"添加成功",0,x);
        else
            return  new Result(-1,"添加失败",0,x);
    }

}
