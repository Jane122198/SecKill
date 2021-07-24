package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.redis.RedisService;
import com.example.demo.service.GoodsService;
import com.example.demo.service.UserService;
import com.example.demo.vo.GoodsVO;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsListController {
    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/showList")
    public String list(Model model,User user) {
        model.addAttribute("user", user);
        List<GoodsVO> goodsList = goodsService.listGoodsVO();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/detail/{goodsId}")
    public String detail(Model model,User user,@PathVariable("goodsId")long goodsId) {
        model.addAttribute("user", user);

        GoodsVO goods = goodsService.getGoodsVOByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int saleGoodsStatus = 0;
        int remainSeconds = 0;
        if(now < startTime ) {
            saleGoodsStatus = 0;
            remainSeconds = (int)((startTime - now )/1000);
        }else  if(now > endTime){
            saleGoodsStatus = 2;
            remainSeconds = -1;
        }else {
            saleGoodsStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("saleGoodsStatus", saleGoodsStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
}
