package com.example.demo.controller;

import com.example.demo.entity.OrderInfo;
import com.example.demo.entity.SaleOrderInfo;
import com.example.demo.entity.User;
import com.example.demo.service.BuyService;
import com.example.demo.service.GoodsService;
import com.example.demo.service.OrderService;
import com.example.demo.util.CodeMsg;
import com.example.demo.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/buy")
public class BuyController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    BuyService buyService;

    @RequestMapping("/buyAction")
    public String buy(Model model, User user, @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if(user == null) {
            return "login";
        }
        //判断库存
        GoodsVO goods = goodsService.getGoodsVOByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.SALE_OVER.getMsg());
            return "buy_fail";
        }
        //判断是否已经秒杀到了
        SaleOrderInfo order = orderService.getSaleOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "buy_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = buyService.showSaleOrder(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
}
