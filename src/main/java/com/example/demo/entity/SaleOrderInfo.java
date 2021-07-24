package com.example.demo.entity;

import lombok.Data;

@Data
public class SaleOrderInfo {

    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;
}
