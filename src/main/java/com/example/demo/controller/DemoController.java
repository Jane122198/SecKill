package com.example.demo.controller;

import com.example.demo.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class DemoController {

    @RequestMapping("/hello")
    public @ResponseBody
    Result<String> hello() {
        return Result.success("helloaaaa");
    }

    @RequestMapping("/a")
    public String a(){
        return "test";
    }
}
