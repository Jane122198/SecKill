package com.example.demo.service;

import com.example.demo.dao.GoodsDao;
import com.example.demo.entity.SaleGoods;
import com.example.demo.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVO> listGoodsVO(){
        return goodsDao.listGoodsVO();
    }

    public GoodsVO getGoodsVOByGoodsId(long goodsId) {
        return goodsDao.getGoodsVOByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVO goods) {
        SaleGoods g = new SaleGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }
}
