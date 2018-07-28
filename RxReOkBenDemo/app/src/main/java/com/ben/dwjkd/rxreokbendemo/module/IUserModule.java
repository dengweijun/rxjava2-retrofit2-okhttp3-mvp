package com.ben.dwjkd.rxreokbendemo.module;

import com.ben.dwjkd.rxreokbendemo.bean.TokenInfoBean;

import io.reactivex.Observable;

public interface IUserModule {

    Observable<TokenInfoBean> login(String username, String password);

    Observable<Void> logout(String phone);

}
