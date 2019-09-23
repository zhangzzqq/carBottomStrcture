package com.example.car.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Created by Harrison.Pan on 2016/9/9.
 */
public class ErrorUtils {
    public static final String ERROR_FROM_SERVER = "服务器错误：%s，%s";
    public static final String ERROR_FROM_NET = "网络错误：%s，%s";
    public static final String ERROR_FROM_APP = "程序错误：%s";
    private static final String NO_DEF_ERROR = "没有定义错误信息";

    public ErrorUtils() {
    }

    public static String buildError(@NonNull String format, Object... args) {
        return String.format(format, args);
    }

    public static String buildServerError(String code, String message) {
        String msg = TextUtils.isEmpty(message) ? NO_DEF_ERROR : message;
        return buildError(ERROR_FROM_SERVER, code, msg);
    }

    @NonNull
    public static String buildNetError(String code) {
        return buildNetError(code, "");
    }

    public static String buildNetError(String code, String message) {
        String msg;
        if ("-1".equals(code)) {
            msg = "连接异常，请检查网络连接正确性";
        } else if ("-2".equals(code)) {
            msg = "连接超时";
        } else {
            msg = TextUtils.isEmpty(message) ? NO_DEF_ERROR : message;
        }

        return buildError(ERROR_FROM_NET, code, msg);
    }

    public static String buildAppError(String message) {
        return buildError(ERROR_FROM_APP, message);
    }

//    @NonNull
//    public static String getErrorMessage(@Nullable VolleyError error) {
//        return error == null ? "未知的错误" :
//                (error.networkResponse != null ?
//                        String.format("error code is %s", error.networkResponse.statusCode) :
//                        (TextUtils.isEmpty(error.getMessage()) ? error.toString() : error.getMessage()));
//    }

}
