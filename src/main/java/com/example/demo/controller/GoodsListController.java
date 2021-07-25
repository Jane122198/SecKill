package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.redis.GoodsKey;
import com.example.demo.redis.RedisService;
import com.example.demo.service.GoodsService;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import com.example.demo.vo.GoodsDetailVO;
import com.example.demo.vo.GoodsVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value = "/showList",produces="text/html")
    public @ResponseBody String list(HttpServletRequest request, HttpServletResponse response, Model model, User user) {
        model.addAttribute("user", user);
        //取缓存
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }
        List<GoodsVO> goodsList = goodsService.listGoodsVO();
        model.addAttribute("goodsList", goodsList);
        WebContext ctx = new WebContext(request,response,request.getServletContext(),request.getLocale(), model.asMap());
        //手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsList, "", html);
        }
        return html;
    }

    @RequestMapping(value = "/detail2/{goodsId}",produces = "text/html")
    public @ResponseBody String detail2(HttpServletRequest request, HttpServletResponse response, Model model, User user, @PathVariable("goodsId")long goodsId) {
        model.addAttribute("user", user);

        //取缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }
        //手动渲染
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

        WebContext ctx = new WebContext(request,response,request.getServletContext(),request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);

        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
        }
        return html;
    }

    @RequestMapping(value="/detail/{goodsId}")
    public @ResponseBody Result<GoodsDetailVO> detail(HttpServletRequest request, HttpServletResponse response, Model model,User user,@PathVariable("goodsId")long goodsId) {
        GoodsVO goods = goodsService.getGoodsVOByGoodsId(goodsId);
        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int saleGoodsStatus = 0;
        int remainSeconds = 0;
        if(now < startTime ) {//秒杀还没开始，倒计时
            saleGoodsStatus = 0;
            remainSeconds = (int)((startTime - now )/1000);
        }else  if(now > endTime){//秒杀已经结束
            saleGoodsStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            saleGoodsStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVO vo = new GoodsDetailVO(saleGoodsStatus,remainSeconds,goods,user);
        return Result.success(vo);
    }
}
