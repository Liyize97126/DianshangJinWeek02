package com.bawei.dianshangjinweek02.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 */
public class RetrofitUtil {
    //定义
    private Retrofit retrofit;
    //单例
    private static final RetrofitUtil RETROFIT_UTIL = new RetrofitUtil();
    private RetrofitUtil() {
        //日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //定义OkHttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        //初始化Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://blog.zhaoliang5156.cn/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    public static RetrofitUtil getRetrofitUtil() {
        return RETROFIT_UTIL;
    }
    //请求
    public <T> T create(final Class<T> service){
        return retrofit.create(service);
    }
    //网络判断
    public boolean isNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null&&activeNetworkInfo.isAvailable()) {
            return true;
        }else{
            return false;
        }
    }
}
