package com.example.demo.redis;


public class LoginUserKey extends BasePrefix{

    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;
    private LoginUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static LoginUserKey token = new LoginUserKey(TOKEN_EXPIRE, "tk");


}
