package com.example.travelhelper.mvp.repository.remote;

import com.example.travelhelper.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    static Retrofit instance;
    static HttpLoggingInterceptor interceptor;

    public static Retrofit getInstance() {
        if(instance == null){
            interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            instance = new Retrofit.Builder()
                    .baseUrl(Constants.baseUrl)
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return instance;
    }
}