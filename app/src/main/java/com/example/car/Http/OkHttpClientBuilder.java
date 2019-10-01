package com.example.car.Http;



import com.example.car.model.AccessTokenUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by ching on 2017/1/17.
 * OkHttpClient封装类
 *
 * @author chingxuds chingxuds@gmail.com
 * @version 1.0
 */

public class OkHttpClientBuilder {
    private final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    private final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    private int timeout;// 超时时长
    private TimeUnit timeUnit;
    private HttpLoggingInterceptor.Level level;// 日志级别
    private String accessToken;

    public OkHttpClientBuilder() {
        this.timeout = 30;
        this.timeUnit = TimeUnit.SECONDS;
        this.level = HttpLoggingInterceptor.Level.BODY;
        this.level = HttpLoggingInterceptor.Level.HEADERS;

    }

    public OkHttpClientBuilder(int timeOut, TimeUnit timeUnit, HttpLoggingInterceptor.Level level) {
        this.timeout = timeOut;
        this.timeUnit = timeUnit;
        this.level = level;
    }

    public OkHttpClient build() {
        this.httpClientBuilder.connectTimeout(this.timeout, this.timeUnit);// 设置超时时间
        this.httpClientBuilder.readTimeout(this.timeout, this.timeUnit);
        this.httpClientBuilder.writeTimeout(this.timeout, this.timeUnit);
        httpLoggingInterceptor.setLevel(level);
        this.httpClientBuilder.addInterceptor(this.httpLoggingInterceptor);// 添加拦截器
        this.httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
//                        .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                        .addHeader("Accept-Encoding", "gzip, deflate")
//                        .addHeader("Connection", "keep-alive")
//                        .addHeader("Accept", "*/*")
//                        .addHeader("Cookie", "add cookies here")
                        .addHeader("Authorization", AccessTokenUtils.readAccessToken())
                        .build();
                return chain.proceed(request);
            }

        });
        return this.httpClientBuilder.build();
    }
}
