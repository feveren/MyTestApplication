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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        HttpClient.init(new HttpClient.Config()
                .paramsBuilder(new CommonParamsInterceptor.ParamsBuilder() {

                    @Override
                    public void build(String method, Map<String, String> params, Map<String, String> body) {
                        String token = "cdebf6d4265d8fc2367dc20e52c41ef0";

                        params.put("userId", "46f1c9142d255075c-220e");
                        if ("GET".equals(method)) {
                            params.put("appVersion", "5.9.12");
                            params.put("appOs", "android");
                            params.put("token", token);
                        } else {
                            body.put("appVersion", "5.9.12");
                            body.put("appOs", "android");
                            body.put("token", token);
                        }
                    }
                }));
    }
}
