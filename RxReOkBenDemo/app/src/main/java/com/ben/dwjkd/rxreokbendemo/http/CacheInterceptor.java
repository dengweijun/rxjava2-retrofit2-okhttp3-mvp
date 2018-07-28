package com.ben.dwjkd.rxreokbendemo.http;

import com.ben.dwjkd.rxreokbendemo.MyApplication;
import com.ben.dwjkd.rxreokbendemo.utils.NetUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor {

    private String CACHE_NAME;

    public CacheInterceptor(String cacheName) {
        CACHE_NAME = cacheName;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (!NetUtils.isNetworkAvailable()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (NetUtils.isNetworkAvailable()) {
            int maxAge = 0;
            // 有网络时 设置缓存超时时间0个小时
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader(CACHE_NAME)// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            // 无网络时，设置超时为4周
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" +
                            maxStale)
                    .removeHeader(CACHE_NAME)
                    .build();
        }
        return response;
    }
}
