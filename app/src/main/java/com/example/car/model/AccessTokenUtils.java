package com.example.car.model;

/**
 * 保存用户信息
 * Created by liuwenjie on 2017/6/29.
 */

public class AccessTokenUtils {

    private static String accessToken = "";

    public static void writeAccessToken(String accessToken) {
        AccessTokenUtils.accessToken = accessToken;
    }

    public static String readAccessToken() {
        return AccessTokenUtils.accessToken;
    }
}
