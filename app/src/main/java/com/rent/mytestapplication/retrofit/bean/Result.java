package com.rent.mytestapplication.retrofit.bean;

/**
 * 网络请求返回数据基类Bean
 * Created by RenTao on 17/1/9.
 */
public class Result<T> {
    public final static String KEY_CODE = "code";
    public final static String KEY_MESSAGE = "message";
    public final static String KEY_LAST_QUERY_TIME = "lastQueryTime";
    public final static String KEY_RESULT = "result";
    public final static String KEY_ARRAY_RESULT = "results";

    public int code;
    public String message;
    public long lastQueryTime;
    public T data;

    public boolean hasData() {
        return data != null;
    }

    public void copy(Result<?> result) {
        result.code = code;
        result.message = message;
        result.lastQueryTime = lastQueryTime;
    }
}
