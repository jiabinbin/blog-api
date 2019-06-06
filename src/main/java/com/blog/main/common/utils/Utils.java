package com.blog.main.common.utils;

import org.springframework.util.DigestUtils;

public class Utils {
    private static final String MD5_SLAT = "blog fronted pass";
    public static String md5 (String password) {
        String baseStr = password.trim() + "/" + MD5_SLAT;
        String md5Str = DigestUtils.md5DigestAsHex(baseStr.getBytes());
        return md5Str;
    }
}
