package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.exception.GlobalException;
import com.example.demo.redis.LoginUserKey;
import com.example.demo.redis.RedisService;
import com.example.demo.redis.UserKey;
import com.example.demo.util.CodeMsg;
import com.example.demo.util.MD5Util;
import com.example.demo.util.UUIdUtil;
import com.example.demo.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    public User getById(long id){
        return userDao.getById(id);
    }

    public User getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        User user = redisService.get(LoginUserKey.token, token, User.class);
        if(user != null) {
            addCookie(response, token, user);
        }

        return user;
    }

    public boolean login(HttpServletResponse response,LoginVO loginVO){
        if(loginVO==null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        String mobile=loginVO.getMobile();
        String tempPassword=loginVO.getPassword();
        User user=getById(Long.parseLong(mobile));
        if(user==null){
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }

        String dbPass=user.getPassword();
        String salt=user.getSalt();
        if(!MD5Util.formPassToDBPass(tempPassword,salt).equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token= UUIdUtil.uuid();
        addCookie(response,token,user);
        return true;

    }

    private void addCookie(HttpServletResponse response, String token, User user) {
        redisService.set(LoginUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(LoginUserKey.token.getExpireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
