package com.ben.dwjkd.rxreokbendemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ben.dwjkd.rxreokbendemo.bean.TokenInfoBean;
import com.ben.dwjkd.rxreokbendemo.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity<UserContact.Presenter> implements UserContact.View {

    @Override
    protected UserContact.Presenter createPresenter(Intent intent) {
        return new UserPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    /**
     * 点击登录按钮
     *
     * @param view
     */
    public void login(View view) {
        mPresenter.login("ben_deng", "123456");
    }

    @Override
    public void loginSuccess(TokenInfoBean tokenInfoBean) {
        // TODO 做登录之后的操作，跳转到MainActivity界面
    }
}
