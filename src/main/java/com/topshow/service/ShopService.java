package com.topshow.service;

import com.topshow.entity.Shop;

import java.util.List;

public interface ShopService {

    /**
     * 获取所有店面信息
     * @return
     */
    List<Shop> getAllShop();

    /**
     * 添加店面图
     * @param shop
     * @return
     */
    Integer insert(Shop shop);

    /**
     * 编辑店面
     * @param shop
     * @return
     */
    Integer update(Shop shop);

    /**
     * 删除店面
     * @param id
     * @return
     */
    Integer delete(String id);
}
