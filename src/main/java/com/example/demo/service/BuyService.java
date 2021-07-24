package com.example.demo.service;

import com.example.demo.entity.OrderInfo;
import com.example.demo.entity.User;
import com.example.demo.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuyService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo showSaleOrder(User user, GoodsVO goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        return orderService.createOrder(user, goods);
    }
}
