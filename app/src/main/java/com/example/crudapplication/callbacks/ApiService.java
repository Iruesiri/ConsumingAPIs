package com.example.crudapplication.callbacks;

import com.example.crudapplication.model.CategoryResponse;
import com.example.crudapplication.model.ImageUpload;
import com.example.crudapplication.model.LoginDetails;
import com.example.crudapplication.model.ProductDetails;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("user/login")
    Call<ResponseBody> login(@Body LoginDetails details);

    //get all category
    @GET("category")
    Call<CategoryResponse> getCategoryDetails();

    //create category
    @POST("category")
    Call<ResponseBody> createCategory(@Body com.example.crudapplication.model.Body body);

    //Create product
    @POST("product")
    Call<ResponseBody> createProduct(@Body ProductDetails productDetails);

    //get all products
    @GET("product/0/5")
    Call<CategoryResponse> getAllProductDetails();

    //get products by id/category
    //@GET("product")

    //upload category Image
    @POST("category/upload")
    Call<ResponseBody> uploadImage(@Body ImageUpload imageUpload);

}
