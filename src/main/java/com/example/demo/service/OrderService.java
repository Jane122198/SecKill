package com.example.demo.service;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.OrderInfo;
import com.example.demo.entity.SaleOrderInfo;
import com.example.demo.entity.User;
import com.example.demo.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    public SaleOrderInfo getSaleOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderDao.getSaleOrderByUserIdAndGoodsId(userId, goodsId);
    }

    @Transactional
    public OrderInfo createOrder(User user, GoodsVO goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliverAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSalePrice());
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderDao.insert(orderInfo);
        SaleOrderInfo miaoshaOrder = new SaleOrderInfo();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());
        orderDao.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
