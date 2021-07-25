package com.example.demo.controller;

import com.example.demo.entity.OrderInfo;
import com.example.demo.entity.User;
import com.example.demo.redis.RedisService;
import com.example.demo.service.GoodsService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.util.CodeMsg;
import com.example.demo.util.Result;
import com.example.demo.vo.GoodsVO;
import com.example.demo.vo.OrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVO> info(Model model, User user,@RequestParam("orderId") long orderId) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	OrderInfo order = orderService.getOrderById(orderId);
    	if(order == null) {
    		return Result.error(CodeMsg.ORDER_NOT_EXIST);
    	}
    	long goodsId = order.getGoodsId();
    	GoodsVO goods = goodsService.getGoodsVOByGoodsId(goodsId);
    	OrderDetailVO vo = new OrderDetailVO();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Result.success(vo);
    }
    
}
