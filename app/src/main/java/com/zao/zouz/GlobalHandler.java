package com.zao.zouz;

import android.os.Handler;
import android.os.Message;

import com.zao.utils.DateUtil;
import com.zao.utils.LogZ;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/4/27 12:32
 */
public class GlobalHandler extends Handler {
    private static GlobalHandler INSTANCE;
    private HandleMsgListener listener;

    //使用单例模式创建GlobalHandler
    private GlobalHandler(){
        LogZ.e("GlobalHandler创建");
    }

    private static class Holder{
        private static final GlobalHandler HANDLER = new GlobalHandler();
    }

    public static GlobalHandler getGlobalHandler(){
        if (INSTANCE == null) {
            synchronized (GlobalHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GlobalHandler();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (getHandleMsgListener() != null){
            getHandleMsgListener().handleMsg(msg);
        }else {
            LogZ.e("请传入HandleMsgListener对象");
        }
    }

    public interface HandleMsgListener{
        void handleMsg(Message msg);
    }

    public void setHandleMsgListener(HandleMsgListener listener){
        this.listener = listener;
    }

    public HandleMsgListener getHandleMsgListener(){
        return listener;
    }

    public String countDown(int count,long delayMillis){
        count++;
        Message  msg  =  Message.obtain();
        msg.what = 0;
        //sendMessageDelayed 发送一条延迟执行的消息，这条消息会延迟1000毫秒执行
        INSTANCE.sendMessageDelayed(msg, delayMillis);

        return DateUtil.timeMinute2(System.currentTimeMillis());
    }
}

