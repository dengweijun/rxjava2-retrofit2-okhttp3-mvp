package com.ben.dwjkd.rxreokbendemo.ui.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T extends IView> implements IPresenter {
    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeDisposable mSubscription;
    protected T mView;

    public BasePresenter(T view) {
        mView = view;
        mSubscription = new CompositeDisposable();
    }

    public abstract void doFirst();

    @Override
    public void subscribe() {
        doFirst();
    }

    @Override
    public void unSubscribe() {
        mSubscription.clear(); // 自动移除未完成的异步任务
    }

    public boolean checkActive() {
        return mView != null && mView.isActive();
    }

    @Override
    public void destroy() {
        mSubscription.dispose();
        mView = null;
    }
}