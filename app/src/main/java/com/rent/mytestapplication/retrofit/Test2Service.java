package com.rent.mytestapplication.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <p> Created by RenTao on 2017/3/29.
 */
public interface Test2Service {

    @GET("https://api.github.com/users/{user}/repos")
    Call<List<RetrofitActivity.Repo>> list(@Path("user") String userId, @Query(value = "name") String name);

    @FormUrlEncoded
    @POST("https://api.github.com/users/{user}/repos")
    Call<List<RetrofitActivity.Repo>> post(@Path("user") String userId, @Query(value = "name") String name,
                                           @Field("email") String email);
}
