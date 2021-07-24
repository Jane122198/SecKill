package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {

    private Long id;
    private Long userId;
    private Long goodsId;
    private Long  deliverAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
