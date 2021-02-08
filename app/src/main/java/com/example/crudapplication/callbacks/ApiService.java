package com.example.crudapplication.callbacks;

import com.example.crudapplication.model.ApiResponse;
import com.example.crudapplication.model.CategoryApiResponse;
import com.example.crudapplication.model.CategoryBody;
import com.example.crudapplication.model.ImageUpload;
import com.example.crudapplication.model.LoginDetails;
import com.example.crudapplication.model.ProductApiResponse;
import com.example.crudapplication.model.ProductBody;

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
    Call<CategoryApiResponse> getCategoryDetails();

    //create category
    @POST("category")
    Call<ResponseBody> createCategory(@Body CategoryBody categoryBody);

    //Create product
    @POST("product")
    Call<ResponseBody> createProduct(@Body ProductBody productBody);

    //get all products
    @GET("product/0/5")
    Call<ProductApiResponse> getAllProductDetails();

    //get products by id/category
    //@GET("product")

    //upload category Image
    @POST("category/upload")
    Call<ResponseBody> uploadImage(@Body ImageUpload imageUpload);

}
