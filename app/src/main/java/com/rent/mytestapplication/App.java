package com.rent.mytestapplication;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.rent.mytestapplication.retrofit.HttpClient;
import com.rent.mytestapplication.retrofit.interceptor.CommonParamsInterceptor;

import java.util.Map;

/**
 *
 * Created by RenTao on 16/10/10.
 */
public class App extends Application {
    public static App app;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        HttpClient.init(new HttpClient.Config()
                .paramsBuilder(new CommonParamsInterceptor.ParamsBuilder() {

                    @Override
                    public void build(String method, Map<String, String> params, Map<String, String> body) {
                        // 请求后面都需要拼上着四个参数
                        params.put("userId", "46f1c9142d255075c-220e");
                        params.put("appVersion", "5.9.12");
                        params.put("appOs", "android");
                        params.put("token", "2a9995190d931de3ea42511dc4319d21");
                        // post和put方法需要在body中放userId
                        if ("POST".equals(method) || "PUT".equals(method)) {
                            body.put("userId", "46f1c9142d255075c-220e");
                        }
                    }
                }));
    }
}
