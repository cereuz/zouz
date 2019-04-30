package com.zao.httpdownload;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/26 15:24
 */
public class VersionApi {

    public static String baseUrl = "https://coding.net";

    public static ApiService apiService;
    //单例
    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (VersionApi.class) {
                if (apiService == null) {
                    new VersionApi();
                }
            }
        }
        return apiService;
    }

    private VersionApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                //适配RxJava2.0,RxJava1.x则为RxJavaCallAdapterFactory.create()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }
}
