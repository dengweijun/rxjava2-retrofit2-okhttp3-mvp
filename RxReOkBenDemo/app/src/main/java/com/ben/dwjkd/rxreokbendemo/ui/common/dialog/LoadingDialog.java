package com.ben.dwjkd.rxreokbendemo.ui.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ben.dwjkd.rxreokbendemo.R;

/**
 * 加载数据时，弹出的dialog
 *
 * @author dengweijun
 */
public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_loading, null);
        setContentView(view);
        setScreenBrightness();
        setCanceledOnTouchOutside(false);
    }

    private void setScreenBrightness() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        /**
         * 此处设置亮度值。dimAmount代表黑暗数量，也就是昏暗的多少，设置为0则代表完全明亮。 范围是0.0到1.0
         */
        lp.dimAmount = 0.2f;
        window.setAttributes(lp);
    }
}
