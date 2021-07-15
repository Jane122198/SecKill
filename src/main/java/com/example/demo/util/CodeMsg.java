package com.example.demo.util;

import lombok.Data;

@Data
public class CodeMsg {
    private int code;
    private String msg;

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static CodeMsg SUCCESS=new CodeMsg(0,"success");
    public static CodeMsg SERVER_ERROR=new CodeMsg(500100,"server_error");

    //Login Module 5002XX
    public static CodeMsg PASSWORD_EMPTY=new CodeMsg(500201,"password can not be empty.");
    public static CodeMsg MOBILE_EMPTY=new CodeMsg(500202,"phone number can not be empty.");
    public static CodeMsg MOBILE_ERROR=new CodeMsg(500203,"phone number is error.");
    public static CodeMsg USER_NOT_EXIST=new CodeMsg(500204,"this user does not exist");
    public static CodeMsg PASSWORD_ERROR=new CodeMsg(500205,"password error");


}
