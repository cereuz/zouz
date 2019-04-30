package com.zao.httpdownload;

import android.content.Context;

import com.zao.utils.LogZ;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/26 14:39
 */
public class MyObserver<T> implements Observer<T> {
    private ObserverOnNextListener listener;
    private Context context;

    public MyObserver(Context context, ObserverOnNextListener listener) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        LogZ.e( "onSubscribe: ");
        //添加业务处理
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        LogZ.e(  "onError: ", e);
        //添加业务处理
    }

    @Override
    public void onComplete() {
        LogZ.e(  "onComplete: ");
        //添加业务处理
    }
}