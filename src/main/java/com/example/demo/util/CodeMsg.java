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

    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "Parameter verification exception:%s");

    //Login Module 5002XX
    public static CodeMsg PASSWORD_EMPTY=new CodeMsg(500201,"password can not be empty.");
    public static CodeMsg MOBILE_EMPTY=new CodeMsg(500202,"phone number can not be empty.");
    public static CodeMsg MOBILE_ERROR=new CodeMsg(500203,"phone number is error.");
    public static CodeMsg USER_NOT_EXIST=new CodeMsg(500204,"this user does not exist");
    public static CodeMsg PASSWORD_ERROR=new CodeMsg(500205,"password error");
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session not exist or invalid");

    //Buy Module 5005XX
    public static CodeMsg SALE_OVER = new CodeMsg(500500, "The goods are sold out");
    public static CodeMsg REPEATE_MIAOSHA = new CodeMsg(500501, "One user can only buy once");

    //Order Module 5006XX
    public static CodeMsg ORDER_NOT_EXIST = new CodeMsg(500600, "Order not exist");



    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

}
