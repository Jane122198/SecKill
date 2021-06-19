package com.example.demo.util;

public class CodeMsg {
    private int code;
    private String message;

    private CodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CodeMsg SUCCESS=new CodeMsg(0,"success");
    public static CodeMsg SERVER_ERROR=new CodeMsg(500100,"server_error");

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
