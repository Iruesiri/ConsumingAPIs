package com.example.crudapplication.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryApiResponse extends ApiResponse implements Serializable {
    public ResponseEntity<ArrayList<CategoryBody>> responseEntity;
}
