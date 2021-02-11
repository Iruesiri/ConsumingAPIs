package com.example.crudapplication.callbacks;

import java.io.Serializable;

public interface CustomApiCallback <T extends Serializable>{
    void onStarted();
    void onSuccess(T t);
    void onError();
    void onNetworkError();
}
