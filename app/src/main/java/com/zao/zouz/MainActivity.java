package com.zao.zouz;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zao.admin.AdminActivity;

public class MainActivity extends AppCompatActivity implements GlobalHandler.HandleMsgListener{

    private GlobalHandler mHandler;
    TextView tv;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         *  1.隐藏标题栏以及状态栏
         *  2.标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题
         *  3.必须填写在setContentView()之前。
         */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());  //获取从C++传来的数据，并显示
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAuto();
    }

    /**
     * 自动跳转
     */
    private void initAuto() {
        mHandler = GlobalHandler.getGlobalHandler();
        mHandler.setHandleMsgListener(this);
        mHandler.sendEmptyMessage(0);
        mHandler.sendEmptyMessageDelayed(1,3 * 1000);
    }

    @Override
    public void handleMsg(Message msg) {
        switch (msg.what){
            case 0:
                tv.setText(DateUtil.getTodayDateHello() + "\n\t\t\t\t\t" + mHandler.countDown(0,1000));
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, AdminActivity.class));
                break;
        }
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
