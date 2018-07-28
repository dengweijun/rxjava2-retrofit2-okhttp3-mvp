package com.ben.dwjkd.rxreokbendemo;

import com.ben.dwjkd.rxreokbendemo.bean.TokenInfoBean;
import com.ben.dwjkd.rxreokbendemo.ui.base.IPresenter;
import com.ben.dwjkd.rxreokbendemo.ui.base.IView;

public interface UserContact {

    interface View extends IView {
        void loginSuccess(TokenInfoBean tokenInfoBean);
    }

    interface Presenter extends IPresenter {
        void login(String username, String password);
    }

}
