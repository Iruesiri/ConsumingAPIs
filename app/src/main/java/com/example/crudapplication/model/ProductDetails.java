package com.example.crudapplication.model;

public class ProductDetails {
    public String categoryId;
    public String categoryName;
    public String productName;
    public String productVatAmount;
    public String warranty;
    public String commonName;
    public String productAmount;
    public String productDiscount;
    public String productShortDescription;
    public String productFullDescription;

    public ProductDetails(String categoryId, String categoryName, String productName, String productVatAmount, String warranty,
                          String commonName, String productAmount, String productDiscount, String productShortDescription, String productFullDescription){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.commonName = commonName;
        this.productAmount = productAmount;
        this.productDiscount = productDiscount;
        this.productFullDescription = productFullDescription;
        this.productName = productName;
        this.productVatAmount = productVatAmount;
        this.warranty = warranty;
        this.productShortDescription = productShortDescription;

    }
}
