package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.exception.GlobalException;
import com.example.demo.util.CodeMsg;
import com.example.demo.util.MD5Util;
import com.example.demo.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(long id){
        return userDao.getById(id);
    }

    public boolean login(LoginVO loginVO){
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
        return true;

    }
}
