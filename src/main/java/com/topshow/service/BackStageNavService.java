package com.topshow.service;

import com.topshow.entity.BackStageNav;
import java.util.List;

public interface BackStageNavService {
  List<BackStageNav> getAllTopLevel();
  
  List<BackStageNav> getSecondLevel(Integer paramInteger);
  
  List<BackStageNav> getNav(Integer paramInteger);
  
  List<BackStageNav> getAllNav(Integer paramInteger1, Integer paramInteger2);
  
  List<BackStageNav> getAllNav();
  
  Integer getNavCount();
  
  List<BackStageNav> getAllTopLevel(Integer paramInteger);
  
  BackStageNav getBackStageNav(Integer paramInteger);
  
  Integer delBackStageNav(Integer paramInteger);
  
  List<BackStageNav> treeBackStageNavs(List<BackStageNav> paramList, Integer paramInteger, boolean paramBoolean);
  
  Integer editNav(BackStageNav paramBackStageNav);
  
  
}

