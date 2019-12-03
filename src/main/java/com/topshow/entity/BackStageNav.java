package com.topshow.entity;

import java.util.List;

/**
 * 后台导航
 * @author Administrator
 *
 */
public class BackStageNav {
    private Integer navId;
    private Integer parentId;
    private String title;
    private String index;
    private String href;
    private String icon;
    private String target;
    private boolean spread;
    private String rolesString;
    private List<BackStageNav> children;


    @Override
    public String toString() {
        return "BackStageNav [navId=" + navId + ", parentId=" + parentId + ", title=" + title + ", index=" + index
                + ", href=" + href + ", icon=" + icon + ", target=" + target + ", spread=" + spread + ", rolesString="
                + rolesString + ", children=" + children + "]";
    }

    public String getRolesString() {
        return this.rolesString;
    }

    public void setRolesString(String rolesString) {
        this.rolesString = rolesString;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isSpread() {
        return this.spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<BackStageNav> getChildren() {
        return this.children;
    }

    public void setChildren(List<BackStageNav> children) {
        this.children = children;
    }

    public Integer getNavId() {
        return this.navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
