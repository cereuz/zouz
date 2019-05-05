package com.zao.utils;

import android.os.Environment;

import com.zao.base.MyApp;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/4/29 13:11
 */
public class Constant {
    /**
     * 访问Servlet的接口
     */
    public static final String BASE_URL_JSON = "http://192.168.60.22:8080/zou0306/TestJsonUber";

    public static final String BASE_URL = "http://download1.bankcomm.com";
    public final static String APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + MyApp.getContext().getPackageName();
    public final static String DOWNLOAD_DIR = "/download/";
    public final static String CHANNEL_ID_ZOU_CHAT = "zouChat";
    public static final String CHANNEL_ID_ZOU_SUBSCRIBE = "zouSub";
}
