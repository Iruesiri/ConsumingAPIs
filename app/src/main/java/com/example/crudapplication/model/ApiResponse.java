package com.example.crudapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("statusCode")
    @Expose
    public int statusCode;
    @SerializedName("requestSuccessful")
    @Expose
    public boolean requestSuccessful;
    @SerializedName("executionTime")
    @Expose
    public double executionTime;
    @SerializedName("apiErrors")
    @Expose
    public ApiErrors apiErrors;
    @SerializedName("apiWarnings")
    @Expose
    public ApiWarnings apiWarnings;
    //public String requestedCommand = null;
   // public ResponseEntity responseEntity;
}
