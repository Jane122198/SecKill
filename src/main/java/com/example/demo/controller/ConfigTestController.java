package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.redis.RedisService;
import com.example.demo.redis.UserKey;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class ConfigTestController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/hello")
    public @ResponseBody
    Result<String> hello() {
        return Result.success("helloaaaa");
    }

    @RequestMapping("/a")
    public String a(){
        return "test";
    }

    @RequestMapping("/getUser")
    public @ResponseBody Result<User> getUser(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/redis/get")
    public @ResponseBody Result<User> redisGet(){
        User user=redisService.get(UserKey.getById,"key1",User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    public @ResponseBody Result<User> redisSet(){
        User u=new User(1,"zhong","19981221zj");
        redisService.set(UserKey.getByName,"key2",u);
        User user=redisService.get(UserKey.getByName,"key2",User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/incr")
    public @ResponseBody Result<Long> redisIncr(){
        redisService.set(UserKey.getById,"1",1);


        long res=redisService.incr(UserKey.getById,"1");
        return Result.success(res);
    }
}
