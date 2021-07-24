package com.example.demo.dao;

import com.example.demo.entity.SaleGoods;
import com.example.demo.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("select g.*,s.stock_count, s.start_date, s.end_date,s.sale_price from sale_goods s left join goods g on s.goods_id = g.id")
    List<GoodsVO> listGoodsVO();

    @Select("select g.*,s.stock_count, s.start_date, s.end_date,s.sale_price from sale_goods s left join goods g on s.goods_id = g.id where g.id = #{goodsId}")
    GoodsVO getGoodsVOByGoodsId(@Param("goodsId")long goodsId);

    @Update("update sale_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    int reduceStock(SaleGoods g);
}
