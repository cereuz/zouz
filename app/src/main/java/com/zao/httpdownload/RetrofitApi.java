package com.zao.httpdownload;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/26 14:27
 */
public class RetrofitApi {
    public static String baseUrl = "https://api.douban.com/v2/movie/";

    public static ApiService apiService;
    //单例
    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (RetrofitApi.class) {
                if (apiService == null) {
                    new RetrofitApi();
                }
            }
        }
        return apiService;
    }

    private RetrofitApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                //适配RxJava2.0,RxJava1.x则为RxJavaCallAdapterFactory.create()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

}