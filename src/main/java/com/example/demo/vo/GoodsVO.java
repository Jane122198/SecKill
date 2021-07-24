package com.example.demo.vo;

import com.example.demo.entity.Goods;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsVO extends Goods {

    private Double salePrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
