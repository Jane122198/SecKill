package com.example.demo.dao;

import com.example.demo.entity.OrderInfo;
import com.example.demo.entity.SaleOrderInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {

    @Select("select * from sale_order_info where user_id=#{userId} and goods_id=#{goodsId}")
    SaleOrderInfo getSaleOrderByUserIdAndGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice} ,#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    long insert(OrderInfo orderInfo);

    @Insert("insert into sale_order_info (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    int insertMiaoshaOrder(SaleOrderInfo saleOrderInfo);

}
