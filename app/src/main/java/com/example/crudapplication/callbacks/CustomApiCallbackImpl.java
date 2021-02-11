package com.example.crudapplication.callbacks;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomApiCallbackImpl<T extends Serializable> implements Callback<T> {
    CustomApiCallback<T> apiCallback;

    public CustomApiCallbackImpl(CustomApiCallback<T> apiCallback){
        this.apiCallback = apiCallback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        apiCallback.onStarted();
        if (response.isSuccessful()){
            apiCallback.onSuccess(response.body());
        }
        else {
            apiCallback.onError();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        apiCallback.onStarted();
        apiCallback.onNetworkError();
    }
}
