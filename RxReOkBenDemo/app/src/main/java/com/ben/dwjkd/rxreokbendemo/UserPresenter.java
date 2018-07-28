package com.ben.dwjkd.rxreokbendemo;

import com.ben.dwjkd.rxreokbendemo.bean.TokenInfoBean;
import com.ben.dwjkd.rxreokbendemo.module.IUserModule;
import com.ben.dwjkd.rxreokbendemo.module.UserModuleImpl;
import com.ben.dwjkd.rxreokbendemo.ui.base.BasePresenter;
import com.ben.dwjkd.rxreokbendemo.utils.Prompt;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class UserPresenter extends BasePresenter<UserContact.View> implements UserContact
        .Presenter {

    private IUserModule userModule;

    public UserPresenter(UserContact.View view) {
        super(view);
        userModule = UserModuleImpl.getInstance();
    }

    @Override
    public void login(String username, String password) {
        mView.showLoading();
        Disposable subscribe = userModule.login(username, password).subscribe(new Consumer<TokenInfoBean>() {
            @Override
            public void accept(TokenInfoBean tokenInfoBean) throws Exception {
                if (checkActive()) {
                    mView.hideLoading();
                    mView.loginSuccess(tokenInfoBean);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                if (checkActive()) {
                    mView.hideLoading();
                    Prompt.showToast(throwable.getMessage());
                }

            }
        });
        mSubscription.add(subscribe);
    }

    @Override
    public void doFirst() {

    }
}
