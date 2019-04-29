package com.zao.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/25 16:21
 */
public class ToastUtil {

    /**
     * 打印吐司
     * @param context  上下文环境
     * @param msg   打印文本内容
     */
    public static void showT(Context context, String msg){
        Toast.makeText(context,msg,0).show();
    }
}
