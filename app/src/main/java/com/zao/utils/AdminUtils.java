package com.zao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import com.zao.activity.BannerActivity;
import com.zao.activity.BubbleActivity;
import com.zao.pressure0306.PressureDiagramActivity;
import com.zao.zouz.R;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/23 15:00
 */
public class AdminUtils {
    public static boolean AdminMenu(Context context , MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_diary) {
            Toast.makeText(context, "日记。。。", Toast.LENGTH_SHORT).show();
 /*			Intent  intent  =  new  Intent(this,WritediaryActivity.class);
 			startActivity(intent);*/
            return true;
        }  else  if (id == R.id.action_start) {
            Toast.makeText(context, "查询。。。", Toast.LENGTH_SHORT).show();
/* 			Intent  intent  =  new Intent(context,TestMeActivity.class);
 			context.startActivity(intent);*/
            Intent intent = new Intent(context, BubbleActivity.class);
            context.startActivity(intent);
            return true;
        }  else  if (id == R.id.action_pressure) {
            Toast.makeText(context, "设置。。。", Toast.LENGTH_SHORT).show();
 			Intent  intent  =  new  Intent(context, PressureDiagramActivity.class);
 			context.startActivity(intent);
            return true;
        }  else  if (id == R.id.action_about) {
            Toast.makeText(context, "关于。。。", Toast.LENGTH_SHORT).show();
            Intent  intent  =  new  Intent(context, BannerActivity.class);
            context.startActivity(intent);
            return true;
        }  else  if (id == android.R.id.home) {
            Toast.makeText(context, "主页,显示全部", Toast.LENGTH_SHORT).show();
 			/*Intent  intent  =  new  Intent(this,AboutActivity.class);
 			startActivity(intent);*/
            //finish()方法目前是在Activity才有的方法，需要使用Actitvity强转一下。
            ((Activity)context).finish();
            return true;
        }  else  if (id ==  R.id.action_search) {
            Toast.makeText(context, "搜索一下，onezao，去除重复", Toast.LENGTH_SHORT).show();
            return true;
        }  else  if (id ==  R.id.action_search) {
            Toast.makeText(context, "自动添加测试数据", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}

