package com.example.crudapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import okhttp3.Headers;

public class ResponseEntity<T> implements Serializable {
    @SerializedName("headers")
    @Expose
    public Headers headers;

    @SerializedName("body")
    @Expose
    public T body;

    @SerializedName("statusCode")
    @Expose
    public String statusCode;

    @SerializedName("statusCodeValue")
    @Expose
    public Integer statusCodeValue;
}
