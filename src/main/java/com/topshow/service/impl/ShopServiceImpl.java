package com.topshow.service.impl;

import com.topshow.entity.Shop;
import com.topshow.mapper.ShopMapper;
import com.topshow.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl  implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<Shop> getAllShop() {


        return shopMapper.findAll();
    }

    /**
     * 添加店面图
     *
     * @param shop
     * @return
     */
    @Override
    public Integer insert(Shop shop) {

        return shopMapper.insert(shop);
    }

    /**
     * 编辑店面
     *
     * @param shop
     * @return
     */
    @Override
    public Integer update(Shop shop) {


        return shopMapper.update(shop);
    }

    /**
     * 删除店面
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return shopMapper.delete(id);
    }
}
