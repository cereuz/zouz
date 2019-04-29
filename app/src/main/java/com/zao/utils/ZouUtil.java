package com.zao.utils;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.zao.zouz.AdminOneActivity;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/4/28 14:52
 */
public class ZouUtil {
    /**
     * 打开APP或者跳转到官网或者主页
     */
    public static void initItem(Context context,String url,String packageName,String launchActivity) {
             //ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
             //startAgentWeb("熊猫签证","https://www.11visa.com/");
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName(packageName,launchActivity);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e){
            if(!TextUtils.isEmpty(url)){
                Toast.makeText(context, "检查到您手机没有安装这个APP，正在跳转到官网", Toast.LENGTH_LONG).show();
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "这个应用官网也没有！正在跳转到主页", Toast.LENGTH_LONG).show();
                context.startActivity(new Intent(context, AdminOneActivity.class));
            }


        }
    }
}
