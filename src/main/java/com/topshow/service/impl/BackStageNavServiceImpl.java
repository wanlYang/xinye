package com.topshow.service.impl;

import com.topshow.entity.BackStageNav;
import com.topshow.mapper.BackStageNavMapper;
import com.topshow.service.BackStageNavService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackStageNavServiceImpl implements BackStageNavService {
    @Autowired
    private BackStageNavMapper adminNavMapper;

    public List<BackStageNav> getAllTopLevel() {
        List<BackStageNav> list = this.adminNavMapper.getTopLevel(0);
        return list;
    }


    public List<BackStageNav> getSecondLevel(Integer menuId) {
        if (menuId != null) {
            List<BackStageNav> allNav = getAllNav();
            return treeBackStageNavs(allNav, menuId, false);
        }

        return null;
    }

    public List<BackStageNav> getNav(Integer id) {
        if (id != null) {
            return this.adminNavMapper.getNav(id);
        }
        return null;
    }

    public List<BackStageNav> getAllNav(Integer page, Integer limit) {
        List<BackStageNav> backStageNavs = null;
        if (page != null && limit != null) {
            backStageNavs = this.adminNavMapper.findAllNav(Integer.valueOf((page.intValue() - 1) * limit.intValue()),
                    limit);
        }
        return backStageNavs;
    }

    public Integer getNavCount() {
        return this.adminNavMapper.findNavCount();
    }

    public List<BackStageNav> getAllTopLevel(Integer flag) {
        List<BackStageNav> topNav = new ArrayList<BackStageNav>();
        if (flag != null && flag.intValue() == 2) {
            List<BackStageNav> topLevel = this.adminNavMapper.getTopLevel(0);
            topNav.addAll(topLevel);
            if (topLevel != null && !topLevel.isEmpty()) {
                Iterator<BackStageNav> iterator = topLevel.iterator();
                while (iterator.hasNext()) {
                    BackStageNav backStageNav = (BackStageNav) iterator.next();
                    List<BackStageNav> secondLevel = this.adminNavMapper.getSecondLevel(backStageNav.getNavId());
                    topNav.addAll(secondLevel);
                }
            }
        }
        return topNav;
    }


    public BackStageNav getBackStageNav(Integer id) {
        BackStageNav backStageNav = null;
        if (id != null) {
            backStageNav = this.adminNavMapper.findNavById(id);
        }
        return backStageNav;
    }

    public Integer delBackStageNav(Integer id) {
        Integer row = null;
        if (id != null) {
            List<BackStageNav> findAllNav = this.adminNavMapper.findAll();
            List<BackStageNav> child = treeBackStageNavs(findAllNav, id, true);
            if (child != null) {
                return Integer.valueOf(-2);
            }
            row = this.adminNavMapper.delete(id);
        }
        return row;
    }

    public List<BackStageNav> treeBackStageNavs(List<BackStageNav> backStageNavs, Integer parentId, boolean isSuper) {
        List<BackStageNav> childNavList = new ArrayList<BackStageNav>();
        Iterator<BackStageNav> iterator = backStageNavs.iterator();
        while (iterator.hasNext()) {
            BackStageNav backStageNav = (BackStageNav) iterator.next();
            if (backStageNav.getParentId().intValue() != 0
                    && backStageNav.getParentId().intValue() == parentId.intValue()) {
                childNavList.add(backStageNav);
            }
        }

        for (BackStageNav backStageNav : childNavList) {
            if (backStageNav.getHref().equals("0")) {
                backStageNav.setChildren(treeBackStageNavs(backStageNavs, backStageNav.getNavId(), isSuper));
            }
        }
        if (childNavList.size() == 0) {
            return null;
        }
        return childNavList;
    }

    public List<BackStageNav> getAllNav() {
        return this.adminNavMapper.findAll();
    }

    public Integer editNav(BackStageNav backStageNav) {
        Integer row = null;
        if (backStageNav != null) {
            row = this.adminNavMapper.updateNav(backStageNav);
        }
        return row;
    }


    
}
