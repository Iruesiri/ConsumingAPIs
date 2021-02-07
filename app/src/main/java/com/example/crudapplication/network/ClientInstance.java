package com.example.crudapplication.network;

import com.example.crudapplication.callbacks.ApiService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientInstance {
    private static Retrofit retrofit;
    private static Retrofit retrofit2;
    private static final String BASE_URL = "http://35.188.185.194:8080/kreer_app/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

            clientBuilder.addInterceptor(interceptor);
            OkHttpClient okHttpClient = clientBuilder.build();
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static ApiService getLoginService() {
        return getRetrofitInstance().create(ApiService.class);
    }

    public static Retrofit getInstance(String token) {
        if (retrofit2 == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
           // OkHttpClient.Builder client = new OkHttpClient.Builder();
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request newRequest;
                    newRequest= request.newBuilder()
                            .header("Authorization", token)
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json")
                            .method(request.method(), request.body())
                            .build();
                    return chain.proceed(newRequest);
                }
            };
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(interceptor)
                    .build();
            retrofit2 = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit2;
    }
    public static ApiService getService(String token){
        return getInstance(token).create(ApiService.class);
    }
}
