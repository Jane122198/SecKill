package com.example.demo.vo;

import com.example.demo.entity.OrderInfo;
import lombok.Data;

@Data
public class OrderDetailVO {
	private GoodsVO goods;
	private OrderInfo order;
}
