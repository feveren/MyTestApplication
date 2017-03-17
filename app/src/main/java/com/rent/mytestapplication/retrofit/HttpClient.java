package com.rent.mytestapplication.retrofit;

import com.rent.mytestapplication.retrofit.interceptor.CommonParamsInterceptor;
import com.rent.mytestapplication.retrofit.interceptor.LoggingInterceptor;
import com.rent.mytestapplication.retrofit.interceptor.NetworkInfoInterceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 网络连接工具类
 * Created by RenTao on 16/6/10.
 */
public class HttpClient {

    private static OkHttpClient HTTP_CLIENT;

    private HttpClient(Config config) {
        if (HTTP_CLIENT != null) {
            throw new IllegalStateException("HttpClient has been initialized");
        }

        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        loggingInterceptor.setLevel(LoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder clientBuilder =  new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new CommonParamsInterceptor().setParamsBuilder(config.paramsBuilder))
                .addInterceptor(new NetworkInfoInterceptor())
//                .addInterceptor(loggingInterceptor)
                ;
        HTTP_CLIENT = clientBuilder.build();
    }

    public static HttpClient init(Config config) {
        return new HttpClient(config);
    }

    public static OkHttpClient getHttpClient() {
        return HTTP_CLIENT;
    }

    public static Call get(String url, Map<String, String> params, Callback callback) {
        Request request = newBuilder(buildUrlParams(url, params)).get().build();
        Call call = HTTP_CLIENT.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public static Call post(String url, Map<String, String> params, Callback callback) {
        Request request = newBuilder(url).post(buildBody(params, true)).build();
        Call call = HTTP_CLIENT.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public static Call put(String url, Map<String, String> params, Callback callback) {
        return put(url, params, callback, false);
    }

    public static Call put(String url, Map<String, String> params, Callback callback, boolean encode) {
        Request request = newBuilder(url).put(buildBody(params, encode)).build();
        Call call = HTTP_CLIENT.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public static Call delete(String url, Map<String, String> params, Callback callback) {
        Request request = newBuilder(buildUrlParams(url, params)).delete().build();
        Call call = HTTP_CLIENT.newCall(request);
        call.enqueue(callback);
        return call;
    }

    private static Request.Builder newBuilder(String url) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        return builder;
    }

    private static String buildUrlParams(String url, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        StringBuilder builder = new StringBuilder(url);
        if (url.contains("?")) {
            builder.append("&");
        } else {
            builder.append("?");
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                builder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    private static RequestBody buildBody(Map<String, String> params, boolean encode) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                if (encode) {
                    // add方法会对参数进行编码
                    builder.add(entry.getKey(), entry.getValue());
                } else {
                    // addEncoded不会编码
                    builder.addEncoded(entry.getKey(), entry.getValue());
                }
            }
        }
        return builder.build();
    }

    public static class Config {
        CommonParamsInterceptor.ParamsBuilder paramsBuilder;

        public Config paramsBuilder(CommonParamsInterceptor.ParamsBuilder paramsBuilder) {
            this.paramsBuilder = paramsBuilder;
            return this;
        }
    }
}
