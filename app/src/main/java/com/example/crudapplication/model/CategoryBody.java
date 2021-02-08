package com.example.crudapplication.model;

import android.net.Uri;

public class CategoryBody {
    public String categoryId;
    public String categoryName;
    public String categoryImageUrl;
    public String categoryDescription;

    //Product Body
    public String productName;
    public String productVatAmount;
    public String warranty;
    public String commonName;
    public String productAmount;
    public String productDiscount;
    public String productShortDescription;
    public String productFullDescription;

    public CategoryBody(String categoryName, String categoryDescription){
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
//    public Body(String categoryId, String categoryName, String productName, String productVatAmount, String warranty,
//                          String commonName, String productAmount, String productDiscount, String productShortDescription, String productFullDescription){
//        this.categoryId = categoryId;
//        this.categoryName = categoryName;
//        this.commonName = commonName;
//        this.productAmount = productAmount;
//        this.productDiscount = productDiscount;
//        this.productFullDescription = productFullDescription;
//        this.productName = productName;
//        this.productVatAmount = productVatAmount;
//        this.warranty = warranty;
//        this.productShortDescription = productShortDescription;
//
//    }
}


