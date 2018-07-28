package com.ben.dwjkd.rxreokbendemo.api;

import com.ben.dwjkd.rxreokbendemo.bean.TokenInfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("api/access/login")
    Observable<TokenInfoBean> login(@Query("username") String username, @Query("password") String
            password);

    @GET("api/access/logout")
    Observable<Void> logout(@Query("phone") String phone);

}
