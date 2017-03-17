package com.rent.mytestapplication.retrofit.observer.event;

/**
 * <p> 执行顺序
 * <p> 1. {@link #onPreRequest()}
 * <p> 2. {@link #onPostRequest()}
 * <p> 3. {@link #onSuccess(T)} / {@link #onError(int, String)}
 * <p> 4. {@link #onComplete()}
 * <p> Created by RenTao on 2017/3/17.
 */
public abstract class RequestEvent<T> {
    public void onPreRequest() {}

    /**
     * 当请求完成时（不管成功还是失败），都会回调该接口。
     * 会在onSuccess和onError之前
     */
    public void onPostRequest() {}

    /**
     * 请求服务器成功，并code=0时走该回调
     */
    public void onSuccess(T data) {}

    /**
     * 当出现以下三种情况时，会走该回调
     * 1.网络异常，请求服务器失败
     * 2.请求服务器成功，但解析出现异常
     * 3.请求解析都成功，但code！=0时
     */
    public void onError(int code, String message) {}

    /**
     * 当请求完成时（不管成功还是失败），都会回调该接口。
     * 会在onSuccess和onError之后
     */
    public void onComplete() {}
}
