package com.zao.httpdownload;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/26 14:29
 */
public class ApiMethods {
    /**
     * 封装线程管理和订阅的过程
     */
    public static void ApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     *
     * @param observer 由调用者传过来的观察者对象
     * @param start    起始位置
     * @param count    获取长度
     */
    public static void getTopMovie(Observer<Movie> observer, String url, int start, int count) {
        ApiSubscribe(RetrofitApi.getApiService().getTopMovie(url, start, count), observer);
    }

    /**
     * 用于获取版本更新接口数据
     * @param observer
     * @param url
     * @param curVersion   当前手机安装的版本
     */
    public static void getCheckVersion(Observer<Version> observer, String url, int curVersion) {
        ApiSubscribe(VersionApi.getApiService().getCheckVersion(url, curVersion), observer);
    }
}
