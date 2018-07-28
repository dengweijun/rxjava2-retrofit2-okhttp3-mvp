package com.ben.dwjkd.rxreokbendemo.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ben.dwjkd.rxreokbendemo.MyApplication;


/**
 * Toast封装
 */
public class Prompt {

    private static Toast toast;

    /**
     * @param tipStr 提示内容
     */
    public static void showToast(String tipStr) {
        if (!TextUtils.isEmpty(tipStr)) {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.getInstance(), tipStr, Toast.LENGTH_SHORT);
            } else {
                toast.setText(tipStr);
            }
            toast.show();
        }
    }

    /**
     * 自定义文本内入，例如：
     *
     * @param view
     */
    public static void showToast(View view) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(), "", Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, toast.getXOffset(), toast.getYOffset());
        LinearLayout toastView = (LinearLayout) toast.getView();
        toastView.addView(view, 0);
        toast.show();
    }

    /**
     * @param tip
     */
    public static void showToast(int tip) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getInstance(), tip, Toast.LENGTH_SHORT);
        } else {
            toast.setText(tip);
        }
        toast.show();
    }

}
