package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.CodeMsg;
import com.example.demo.util.Result;
import com.example.demo.util.ValidatorUtil;
import com.example.demo.vo.LoginVO;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    public @ResponseBody Result<Boolean> verifyLogin(HttpServletResponse response, @Valid LoginVO loginVO){
        logger.info(loginVO.toString());
        userService.login(response,loginVO);
        return Result.success(true);
    }

    @RequestMapping("/info")
    public @ResponseBody Result<User> info(Model model, User user) {
        return Result.success(user);
    }

}
