package com.ben.dwjkd.rxreokbendemo.helper;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ActivityManagerHelper {

    private List<Activity> activityList = new LinkedList<Activity>();
    private static ActivityManagerHelper instance;

    private ActivityManagerHelper() {
    }

    // 单例模式中获取唯一的ActivityUtils实例
    public static ActivityManagerHelper getInstance() {
        if (null == instance) {
            instance = new ActivityManagerHelper();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(0, activity);
    }

    public void removeActivity(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    public void exitBefore(int index) {
        for (int i = 0; i < activityList.size(); i++) {
            if (i < index && activityList.get(i) != null) {
                activityList.get(i).finish();
            }
        }
    }

    public Activity getTopActivity() {
        if (activityList.size() > 0) {
            return activityList.get(0);
        }
        return null;

    }

    /* 得到顶部Activity的名称 */
    public String getCurrentActivityName(Context context) {

        if (context == null) {
            return "";
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;

        return cn.getClassName();
    }

    /**
     * activity列表
     */
    public List<Activity> getActivityList() {
        return activityList;

    }

    /**
     * 干掉所有的activity
     */
    public void finishAllActivity() {
        int count = ActivityManagerHelper.getInstance().getActivityList().size();
        while (count > 0) {
            ActivityManagerHelper.getInstance().getActivityList().get(0).finish();
            count--;
        }
    }

    /**
     * 干掉所有的activity
     */
    public void finishAllActivityButThis(Activity activity) {
        List<Activity> bRemove = new ArrayList<>();
        for (Activity act : ActivityManagerHelper.getInstance().getActivityList()) {
            if (act.equals(activity)) {
                continue;
            } else {
                bRemove.add(act);
            }
        }
        for (Activity act : bRemove) {
            act.finish();
        }
    }
}
