package com.ben.dwjkd.rxreokbendemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ben.dwjkd.rxreokbendemo.MyApplication;

/**
 * 网络检测
 *
 * @author dengweijun
 */
public class NetUtils {
    /**
     * 检查设备是否连接网络
     *
     * @return 联网状态
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication
                .getInstance().getApplicationContext().getSystemService(Context
                        .CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

}
