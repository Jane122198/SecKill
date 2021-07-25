package com.example.demo.redis;

public class OrderKey extends BasePrefix {

	public OrderKey(String prefix) {
		super(prefix);
	}


	public static OrderKey getSaleOrderByUidGid = new OrderKey("soug");
}
