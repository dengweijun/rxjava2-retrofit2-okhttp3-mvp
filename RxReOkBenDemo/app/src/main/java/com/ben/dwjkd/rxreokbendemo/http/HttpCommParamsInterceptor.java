package com.ben.dwjkd.rxreokbendemo.http;

import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.ben.dwjkd.rxreokbendemo.MyApplication;
import com.ben.dwjkd.rxreokbendemo.utils.StringUtils;
import com.ben.dwjkd.rxreokbendemo.utils.SystemUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpCommParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        String versionName;
        int versionCode;
        String MEID = null;
        try {
            PackageInfo pi = SystemUtils.getApplicationPackage(MyApplication.getInstance());
            versionName = pi.versionName;
            versionCode = pi.versionCode;
            if (ActivityCompat.checkSelfPermission(MyApplication.getInstance(), Manifest
                    .permission.READ_PHONE_STATE) ==
                    PackageManager.PERMISSION_GRANTED) {
                MEID = SystemUtils.getTelephonyManager(MyApplication.getInstance()).getDeviceId();
                // MEID为空，再取MacAddress
                if (StringUtils.isEmptyString(MEID)) {
                    MEID = SystemUtils.getMacAddress(MyApplication.getInstance());
                }
            }

        } catch (PackageManager.NameNotFoundException e) {
            versionName = "none";
            versionCode = -1;
            MEID = "none";
        }

        Request oldRequest = chain.request();
        HttpUrl.Builder urlBuilder = oldRequest.url().newBuilder();
        urlBuilder.addQueryParameter("versionName", versionName);
        urlBuilder.addQueryParameter("versionCode", versionCode + "");
        urlBuilder.addQueryParameter("deviceType", "1");// 1为android，2为ios
        urlBuilder.addQueryParameter("MEID", MEID);
        HttpUrl url = urlBuilder.build();

        Request newRequest = oldRequest.newBuilder().url(url).build();
        return chain.proceed(newRequest);
    }

}
