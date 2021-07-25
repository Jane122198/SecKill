package com.example.demo.controller;

import com.example.demo.entity.OrderInfo;
import com.example.demo.entity.SaleOrderInfo;
import com.example.demo.entity.User;
import com.example.demo.service.BuyService;
import com.example.demo.service.GoodsService;
import com.example.demo.service.OrderService;
import com.example.demo.util.CodeMsg;
import com.example.demo.util.Result;
import com.example.demo.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/buy")
public class BuyController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    BuyService buyService;

    @RequestMapping(value = "/buyAction",method = RequestMethod.POST)
    public @ResponseBody Result<OrderInfo> buy(Model model, User user, @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        //判断库存
        GoodsVO goods = goodsService.getGoodsVOByGoodsId(goodsId);//10个商品，req1 req2
        int stock = goods.getStockCount();
        if(stock <= 0) {
            return Result.error(CodeMsg.SALE_OVER);
        }
        //判断是否已经秒杀到了
        SaleOrderInfo order = orderService.getSaleOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = buyService.buy(user, goods);
        return Result.success(orderInfo);
    }
}
