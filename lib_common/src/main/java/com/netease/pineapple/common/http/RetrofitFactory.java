package com.netease.pineapple.common.http;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.netease.pineapple.common.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitFactory {

    private static OkHttpClient sOkHttpClient;
    public static String sBASE_URL = "";
    private volatile static Retrofit sRetrofit;

    public static void init(String baseUrl) {
        sBASE_URL = baseUrl;

        // log监听
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.i(message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new NetMonitorInterceptor())
                .addInterceptor(logInterceptor)
                .retryOnConnectionFailure(true);

        sOkHttpClient = builder.build();
    }

    @NonNull
    public static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (sRetrofit == null) {
                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(sBASE_URL)
                            .client(sOkHttpClient)
                            // 添加string的转换器
                            .addConverterFactory(ScalarsConverterFactory.create())
                            // 添加Gson转换器
                            .addConverterFactory(GsonConverterFactory.create())
                            // 添加Retrofit到RxJava的转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return sRetrofit;
    }

    public static Retrofit getRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(sBASE_URL)
                .client(sOkHttpClient)
                // 添加string的转换器
                .addConverterFactory(ScalarsConverterFactory.create())
                // 添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create(gson))
                // 添加Retrofit到RxJava的转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
