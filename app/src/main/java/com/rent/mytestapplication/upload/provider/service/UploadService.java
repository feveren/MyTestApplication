package com.rent.mytestapplication.upload.provider.service;

import com.rent.mytestapplication.retrofit.bean.JsonResult;
import com.rent.mytestapplication.retrofit.observable.CallObservable;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 *
 * Created by RenTao on 2017/3/15.
 */
public interface UploadService {
    @POST("https://gcs.zm518.cn/api/upload/image")
    CallObservable<JsonResult> uploadImage(@Body RequestBody body);

    @POST("audio")
    CallObservable<JsonResult> uploadAudio(@Body RequestBody body);

    @POST("video")
    CallObservable<JsonResult> uploadVideo(@Body RequestBody body);

    @POST("misc")
    CallObservable<JsonResult> uploadMisc(@Body RequestBody body);
}
