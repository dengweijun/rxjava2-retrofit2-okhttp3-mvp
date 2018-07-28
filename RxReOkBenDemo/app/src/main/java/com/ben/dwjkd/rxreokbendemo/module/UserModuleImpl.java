package com.ben.dwjkd.rxreokbendemo.module;

import com.ben.dwjkd.rxreokbendemo.api.UserService;
import com.ben.dwjkd.rxreokbendemo.bean.TokenInfoBean;
import com.ben.dwjkd.rxreokbendemo.http.HttpManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserModuleImpl implements IUserModule {

    private static UserModuleImpl instance;

    private UserModuleImpl() {
    }

    ;

    public static UserModuleImpl getInstance() {
        if (instance == null) {
            synchronized (UserModuleImpl.class) {
                if (instance == null) {
                    instance = new UserModuleImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Observable<TokenInfoBean> login(String username, String password) {
        Observable<TokenInfoBean> login = HttpManager.getInstance().createService(UserService
                .class).login(username, password);
        return login.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Void> logout(String phone) {
        Observable<Void> logout = HttpManager.getInstance().createService(UserService
                .class).logout(phone);
        logout.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return logout;
    }
}
