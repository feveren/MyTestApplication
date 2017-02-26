package com.rent.mytestapplication.retrofit;

import com.rent.mytestapplication.retrofit.adapter.RxJava2CallAdapterFactory;
import com.rent.mytestapplication.retrofit.converter.JsonConverterFactory;

import retrofit2.Retrofit;

/**
 *
 * Created by RenTao on 17/1/14.
 */
public class Requester {
    private static volatile Requester requester;
    private Retrofit mRetrofit;

    private Requester() {
        mRetrofit = new Retrofit.Builder()
                // 假如@GET、@POST等标签设置了url，并包含host，会baseUrl将不起作用
                // 如果没有设置host，则url为baseUrl + 设置的url
                .baseUrl("https://t.zm.gaiay.cn/")
                .addConverterFactory(new JsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(HttpClient.getHttpClient())
                .build();
    }

    public static Requester get() {
        if (requester == null) {
            synchronized (Requester.class) {
                if (requester == null) {
                    requester = new Requester();
                }
            }
        }
        return requester;
    }

    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }
}
