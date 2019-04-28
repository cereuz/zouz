package com.zao.admin;

import android.app.Application;
import android.content.Context;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/25 10:40
 */
public class MyApp extends Application {
    private static MyApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    /**
     * 单例模式
     * 双重锁
     * @return
     */
    public static Context getContext() {
        if (sInstance == null) {
            synchronized (AppManager.class) {
                if (sInstance == null) {
                    sInstance = new MyApp();
                }
            }
        }
        return sInstance;
    }
}
