package com.rent.mytestapplication.retrofit.interceptor;

import android.support.v4.util.ArrayMap;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommonParamsInterceptor implements Interceptor {
    private Map<String, String> mParams, mBody;

    public CommonParamsInterceptor() {
        mParams = new ArrayMap<>();
        mBody = new ArrayMap<>();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (mParamsBuilder == null) {
            return chain.proceed(request);
        }
        this.clear();
        mParamsBuilder.build(request.method(), mParams, mBody);

        Request.Builder builder = request.newBuilder();
        // build HttpUrl
        HttpUrl httpUrl = buildUrl(request.url().newBuilder(), mParams);
        builder.url(httpUrl);
        // build RequestBody
        RequestBody requestBody = buildRequestBody(request, mBody);
        // set method
        builder.method(request.method(), requestBody);
        request = builder.build();
        this.clear();
        return chain.proceed(request);
    }

    private HttpUrl buildUrl(HttpUrl.Builder builder, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    /**
     * 重新构建RequestBody，添加通用的参数
     */
    private RequestBody buildRequestBody(Request request, Map<String, String> params) {
        if (request.body() == null || !(request.body() instanceof FormBody)) {
            return request.body();
        }
        FormBody.Builder builder = new FormBody.Builder();
        // 通用的Params
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                // add方法会对参数进行编码
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        // 客户端传的Params
        FormBody body = (FormBody) request.body();
        for (int i = 0; i < body.size(); i++) {
            // addEncoded方法用来增加已经编码过的key-value，不会对传进去的数据进行编码
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
        }
        return builder.build();
    }

    private void clear() {
        mParams.clear();
        mBody.clear();
    }

    private ParamsBuilder mParamsBuilder;

    public interface ParamsBuilder {
        void build(String method, Map<String, String> params, Map<String, String> body);
    }

    public CommonParamsInterceptor setParamsBuilder(ParamsBuilder paramsBuilder) {
        this.mParamsBuilder = paramsBuilder;
        return this;
    }
}