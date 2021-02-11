package com.example.crudapplication.callbacks;

import com.example.crudapplication.model.ApiResponse;
import com.example.crudapplication.model.CategoryApiResponse;
import com.example.crudapplication.model.CategoryBody;
import com.example.crudapplication.model.ImageUpload;
import com.example.crudapplication.model.LoginDetails;
import com.example.crudapplication.model.ProductApiResponse;
import com.example.crudapplication.model.ProductBody;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

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
    @GET("product/0/25")
    Call<ProductApiResponse> getAllProductDetails();

    //get products by id/category
    @GET("product/pc/{categoryId}/{page}/{size}")
    Call<ProductApiResponse> getProductbyCategory(@Path("categoryId") String categoryId, @Path("page") int page, @Path("size") int size);

    //upload category Image
    //Call<Response> uploadImage(@Part MultipartBody.Part image);
    @Multipart
    @POST("category/upload")
    //Call<ResponseBody> uploadImage(@Part ImageUpload imageUpload);
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part file, @Part("RefNo") RequestBody RefNo);

}
