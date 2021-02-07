package com.example.crudapplication.model;

public class CategoryResponse {
    public int statusCode;
    public boolean requestSuccessful;
    public double executionTime;
    public ApiErrors apiErrors;
    public ApiWarnings apiWarnings;
    //public String requestedCommand = null;
    public ResponseEntity responseEntity;
}
