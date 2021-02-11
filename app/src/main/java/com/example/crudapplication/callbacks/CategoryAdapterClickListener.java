package com.example.crudapplication.callbacks;

public interface CategoryAdapterClickListener {
    void addProduct(String categoryId, String categoryName);
    void viewProduct(String categoryId);
    void updateProduct(String categoryId);
    void deleteProduct(String categoryId);
    void uploadImage(String categoryId);
}
