package com.zao.pressure0306;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.zao.bean.MessageEvent;
import com.zao.utils.AdminUtils;
import com.zao.utils.DateUtil;
import com.zao.utils.LogZ;
import com.zao.zouz.R;

import org.greenrobot.eventbus.EventBus;


/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/27 11:14
 */
public class PressureDiagramActivity extends AppCompatActivity {
    static {
        System.loadLibrary("PressureDiagram");
    }
    private ProgressBar pb_pressure_0130;
    private int pb_max_0130 = 800;
    private EditText et_pressure_0130;
    private Button btn_pdstart_0130;
    private MyPressureView pb_mypressure_0130;

    String pressureS;
    String time;

    private MyThread myThread;
    private boolean stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogZ.e("好好学习，天天向上");
        setContentView(R.layout.activity_pressurediagram_0306);

        btn_pdstart_0130 = (Button)findViewById(R.id.btn_pdstart_0130);
        et_pressure_0130 = (EditText) findViewById(R.id.et_pressure_0130);
        pb_pressure_0130 = (ProgressBar) findViewById(R.id.pb_pressure_0130);
        pb_mypressure_0130 = (MyPressureView) findViewById(R.id.pb_mypressure_0130);
        pb_pressure_0130.setMax(pb_max_0130);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogZ.e("好好学习，天天向上");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMonitor();
        if(myThread !=  null){
            myThread.close();
        }
        LogZ.e("好好学习，天天向上");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogZ.e("好好学习，天天向上");
    }

    public  void  startOrStop(View view){
        String btn_text = btn_pdstart_0130.getText().toString().trim();
        if(btn_text.equals("Start")|| btn_text.equals("开始")){
            btn_pdstart_0130.setText("Stop");
            stop = false;
            myThread = new MyThread();
            myThread.start();

/*            new  Thread(){
                public void run(){
                    startMonitor();
                }
            }.start();*/
        } else if (btn_text.equals("Stop")||btn_text.equals("结束")){
            stopMonitor();
            myThread.close();
            btn_pdstart_0130.setText("Start");
        }
    }

    /**
     * C方法运行在主线程，主线程不能做耗时操作。不然会堵塞
     * 控制线程的创建和关闭
     */
    private class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (!stop) {
                    startMonitor();
                    LogZ.e("线程正在运行中");
               }
        }

        public void close() {
            stop = true;
            LogZ.e("线程取消----" + DateUtil.getTodayDateTime());
        }
    }


/* public  void  start(View view){
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        //C方法运行在主线程，主线程不能做耗时操作。不然会堵塞
        new  Thread(){
            public void run(){
                startMonitor();
            }
        }.start();
    }

    public  void  stop(View  view){
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        stopMonitor();
    }*/

    public void setPressure(final int pressure){
        pb_pressure_0130.setProgress(pressure);
        pb_mypressure_0130.setPressure(pressure);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pressureS = String.valueOf(pressure);
                time = DateUtil.getTodayDateTime();
                et_pressure_0130.setText(pressureS + " || " + time);
            }
        });

//        EventBus.getDefault().post(new MessageEvent(pressureS, "蜗牛",time));
    }

    //模拟开启，模拟停止
    public  native void  startMonitor();
    public  native void  stopMonitor();

    //加载顶部菜单，添加菜单的点击事件。
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
/*        //设置左上角的图标的点击事件  ActionBar
         //Activity中才有，AppCompatActivity暂时没有这个。
        ActionBar actionBar = this.getActionBar();
        actionBar.setHomeButtonEnabled(true);*/
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdminUtils.AdminMenu(PressureDiagramActivity.this, item);
        return super.onOptionsItemSelected(item);
    }
}
