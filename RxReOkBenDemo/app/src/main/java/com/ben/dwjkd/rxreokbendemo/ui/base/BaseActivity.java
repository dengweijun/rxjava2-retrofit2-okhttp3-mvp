package com.ben.dwjkd.rxreokbendemo.ui.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ben.dwjkd.rxreokbendemo.helper.ActivityManagerHelper;
import com.ben.dwjkd.rxreokbendemo.ui.common.dialog.LoadingDialog;
import com.ben.dwjkd.rxreokbendemo.utils.Prompt;

public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements
        IView {

    protected T mPresenter;
    private LoadingDialog mLoading;
    private boolean isDestroyed = false;

    protected abstract T createPresenter(Intent intent);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        ActivityManagerHelper.getInstance().addActivity(this);
        mPresenter = createPresenter(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.subscribe();
        }
    }

    @Override
    protected void onPause() {
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        isDestroyed = true;
        if (mPresenter != null) {
            mPresenter.destroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean isDestroyed() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            super.isDestroyed();
        }
        return isDestroyed;
    }

    /**
     * 返回当前布局资源ID
     */
    protected abstract int getLayoutResID();

    @Override
    public boolean isActive() {
        return !isDestroyed();
    }

    @Override
    public void showMessage(String msg) {
        Prompt.showToast(msg);
    }

    @Override
    public void showLoading() {
        try {
            if (mLoading == null) {
                mLoading = new LoadingDialog(this);
            }
            if (!mLoading.isShowing()) {
                mLoading.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLoading() {
        try {
            if (mLoading != null) {
                if (mLoading.isShowing()) {
                    mLoading.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        ActivityManagerHelper.getInstance().removeActivity(this);
    }
}
