package com.ben.dwjkd.rxreokbendemo.ui.base;

public interface IView {
    boolean isActive();

    void showMessage(String msg);

    void showLoading();

    void hideLoading();

    void close();
}
