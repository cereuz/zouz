package com.zao.httpdownload;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/25 10:31
 */
public interface ApiService {

    //    public static final String BASE_URL = "http://dldir1.qq.com";
    @Streaming
    @GET
    Observable<ResponseBody> executeDownload(@Header("Range") String range, @Url() String url);

    @GET
    Observable<Movie> getTopMovie(@Url() String url, @Query("start") int start, @Query("count") int count);

    @GET
    Observable<Version> getCheckVersion(@Url() String url, @Query("versioncode") int curVersionCode);


    @GET("{url}")
    Observable<ResponseBody> executeGet(
            @Path("url") String url,
            @QueryMap Map<String, String> maps);


    @POST("{url}")
    Observable<ResponseBody> executePost(
            @Path("url") String url,
            @FieldMap Map<String, String> maps);
}
