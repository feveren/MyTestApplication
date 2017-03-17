package com.rent.mytestapplication.retrofit;

import com.rent.mytestapplication.retrofit.bean.JsonResult;
import com.rent.mytestapplication.retrofit.bean.ListResult;
import com.rent.mytestapplication.retrofit.bean.MapResult;
import com.rent.mytestapplication.retrofit.bean.Result;
import com.rent.mytestapplication.upload.bean.UploadBean;
import com.rent.mytestapplication.retrofit.observable.CallObservable;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @GET     method
 * @Path    替换url中的占位符，如{user}
 * @Query   url后面拼的参数
 * Created by RenTao on 17/1/9.
 */
public interface TestService {
    @GET("users/{user}/repos")
    Call<List<RetrofitActivity.Repo>> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Observable<List<RetrofitActivity.Repo>> listReposObz(@Path("user") String user);

    @GET("api/zm/ad-scene/?tt=1")
    Call<MapResult> getAD(@Query("lastQueryTime") long time);

    @FormUrlEncoded
    @POST("users/{user}/repos?tt=1")
    Call<List<RetrofitActivity.Repo>> post(@Path("user") String user,
                                           @Field(value = "test") String test,
                                           @Field(value = "test2") String test2);

    @GET("api/zm/ad-scene/")
    Call<ListResult<RetrofitActivity.Ad>> getADList(@Query("lastQueryTime") long time);

    @GET("api/zm/card/detail")
    Call<Result<RetrofitActivity.Detail>> getCardDetail(@Query("targetUserId") String userId);

    @GET("api/zm/card/detail")
    Call<MapResult> getCardDetailMap(@Query("targetUserId") String userId);

    @GET("api/zm/card/detail")
    Call<JsonResult> getCardDetailJson(@Query("targetUserId") String userId);

    @GET("https://t.bang.gaiay.cn/api/zm/ad-scene/")
    CallObservable<ListResult<RetrofitActivity.Ad>> getRxADList(@Query("lastQueryTime") long time);

    @GET("api/zm/card/detail")
    CallObservable<Result<RetrofitActivity.Detail>> getRxCardDetail(@Query("targetUserId") String userId);

    @GET("https://t.zm.gaiay.cn/api/zm/card/detail")
    Observable<MapResult> getRxCardDetailMap(@Query("targetUserId") String userId);

    @GET("api/zm/card/detail")
    Observable<JsonResult> getRxCardDetailJson(@Query("targetUserId") String userId);

    // success
    @POST("https://gcs.zm518.cn/api/upload/image")
    Call<ResponseBody> uploadFile(@Body RequestBody body);

    // success
    @Multipart
    @POST("https://gcs.zm518.cn/api/upload/image")
    Call<ResponseBody> uploadFile(
            @Part("clientId") RequestBody clientId, @Part("domain") RequestBody domain,
            @Part MultipartBody.Part part
    );

    // success
    @Multipart
    @POST("https://gcs.zm518.cn/api/upload/image")
    CallObservable<JsonResult> uploadFile(
            @Part("clientId") RequestBody clientId, @Part("domain") RequestBody domain,
            @Part List<MultipartBody.Part> part
    );
}
