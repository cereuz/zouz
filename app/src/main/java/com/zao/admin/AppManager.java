package com.zao.admin;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/22 17:32
 */
public class AppManager {

    private static AppManager INSTANCE;
    private static List<Activity> activityStack;

    private AppManager() {

    }

    /*  单例模式
    双重锁
    * */
    public static AppManager getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (AppManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppManager();
                    activityStack = new LinkedList<>();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new LinkedList<Activity>();
        }
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定的activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有的activity
     */
    public void finishAllActivity() {
        Iterator<Activity> activityIterator = activityStack.iterator();
        while (activityIterator.hasNext()) {
            Activity target = activityIterator.next();
            if (target != null) {
                target.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
//            SingleThreadPool.getIntance().shutDownThreadPool();
            /*MobclickAgent.onKillProcess(context);
            Picasso.with(context).shutdown();*/
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
        }
    }
}
