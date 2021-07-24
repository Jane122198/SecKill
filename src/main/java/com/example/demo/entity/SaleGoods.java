package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SaleGoods {

    private Long id;
    private Long goodsId;
    private Double salePrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
