package com.example.crudapplication.model;

import java.util.List;

public class ResponseEntity {
    //public List<> headers;
    public List<Body> body;
    public String statusCode;
    public int statusCodeValue;

    public List<ProductDetails> productDetails;
}
