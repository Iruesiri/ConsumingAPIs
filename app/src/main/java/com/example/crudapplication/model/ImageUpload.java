package com.example.crudapplication.model;

import okhttp3.MultipartBody;

public class ImageUpload {
    public String categoryId;
    public MultipartBody.Part imageUrl;

    public ImageUpload(String categoryId, MultipartBody.Part imageUrl){
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }

}
