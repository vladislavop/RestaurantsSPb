package com.nixbyte.project.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    private static OkHttpClient CLIENT;
    private static Gson gson;

    static {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        CLIENT = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        gson = new GsonBuilder()
                .setLenient()
                .create();

    }

    public static Retrofit getRetrofit(String base_url) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(base_url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(CLIENT);

        return builder.build();
    }
}
