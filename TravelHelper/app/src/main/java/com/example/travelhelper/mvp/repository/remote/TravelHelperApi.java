package com.example.travelhelper.mvp.repository.remote;

import com.example.travelhelper.mvp.repository.model.Users;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TravelHelperApi {
    @GET("api/user")
    Observable<String> searchUser(@Query("login") String login, @Query("password") String password);

    @POST("api/user")
    Observable<String> createUser(@Body Users user);
}