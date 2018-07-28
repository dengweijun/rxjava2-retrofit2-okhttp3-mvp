package com.ben.dwjkd.rxreokbendemo.http;

import com.ben.dwjkd.rxreokbendemo.MyApplication;
import com.ben.dwjkd.rxreokbendemo.config.UrlConfig;
import com.ben.dwjkd.rxreokbendemo.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static final String CACHE_NAME = "RxReOkBenDemo";
    private Retrofit mRetrofit;
    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private HttpManager() {

        File cacheFile = new File(MyApplication.getInstance().getApplicationContext()
                .getExternalCacheDir(), CACHE_NAME);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpCommParamsInterceptor())// 公共参数
                .cache(cache).addInterceptor(new CacheInterceptor(CACHE_NAME))
                .retryOnConnectionFailure(true)// 错误重连
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(UrlConfig.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }

    /**
     * 创建接口对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    /**
     * 设置线程切换
     *
     * @param observable
     * @return
     */
    public Observable setThread(Observable observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
