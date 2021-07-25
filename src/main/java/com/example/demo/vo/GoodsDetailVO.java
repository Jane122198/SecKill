package com.example.demo.vo;


import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailVO {
	private int saleGoodsStatus = 0;
	private int remainSeconds = 0;
	private GoodsVO goods ;
	private User user;

}
