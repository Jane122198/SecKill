package com.example.demo.redis;

public interface KeyPrefix {

    int getExpireSeconds() ;

    String getPrefix() ;

}
