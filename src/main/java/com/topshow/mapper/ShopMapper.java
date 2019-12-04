package com.topshow.mapper;

import com.topshow.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMapper {


    List<Shop> findAll();

    Integer insert(Shop shop);

    Integer update(Shop shop);

    Integer delete(String id);
}
