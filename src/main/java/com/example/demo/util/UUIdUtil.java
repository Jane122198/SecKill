package com.example.demo.util;

import java.util.UUID;

public class UUIdUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
