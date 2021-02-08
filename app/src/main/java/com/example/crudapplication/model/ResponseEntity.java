package com.example.crudapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import okhttp3.Headers;

public abstract class ResponseEntity<T> implements Serializable {
    //public List<> headers;
    //public List<Body> body;

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
