package com.zao.pressure0306;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zao.base.MyApp;
import com.zao.utils.DisplayUtils;
import com.zao.utils.LogZ;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/27 11:15
 */
public class MyPressureView extends View {
    private int pressure = 300;
    int height;
    int width;
    public MyPressureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //多了一个defStyleAttr  在xml文件中声明一个样式的时候，解析xml之后，就会转换成这个defStyleAttr
    }

    public MyPressureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //AttributeSet  属性集合，解析xml文件时，属性都会封装在这个AttributeSet中
    }

    public MyPressureView(Context context) {
        super(context);
        //程序员在代码中动态创建控件的时候使用
    }

    //设置压力大小
    public void setPressure(int pressure) {
        this.pressure = pressure;
        //当pressure发生变化的时候，需要调用onDraw方法重新画一下控件View图像
        //使当前的View无效，如果View是可见的，在未来的某一时刻，会自动调用onDraw重新绘制界面
        //		invalidate();这个是在主线程使用的，子线程不能修改UI，如果是子线程，需要调用postInvalidate();
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        height = DisplayUtils.getDisplayHight(MyApp.getContext());
        width = DisplayUtils.getDisplayWidth(MyApp.getContext());
        int bottom = height / 20 * 13;
        float i = (bottom - height / 5)/ 100 ;
        LogZ.e("屏幕高度：" + height + "，屏幕宽度：" + width + ",i :" + i);
        Paint paint = new  Paint();
        if(pressure > 75){
            paint.setColor(Color.RED);
        }  else if (pressure > 50){
            paint.setColor(Color.YELLOW);
        }  else if (pressure > 25){
            paint.setColor(Color.BLUE);
        }   else  {
            paint.setColor(Color.GREEN);
        }
//		canvas.drawRect(left, top, right, bottom, paint);
        canvas.drawRect(width/3, (100 - pressure) * i , width / 3 * 2, bottom , paint);

        paint.setTextSize(40);
        paint.setColor(Color.BLACK);
        canvas.drawText("当前压力值："+ pressure, width / 4, height / 32 * 23, paint);
    }
}

