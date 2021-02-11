package com.example.crudapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.crudapplication.R;
import com.example.crudapplication.adapter.CategoryAdapter;
import com.example.crudapplication.callbacks.ApiService;
import com.example.crudapplication.callbacks.CategoryAdapterClickListener;
import com.example.crudapplication.callbacks.CategorySharedPreference;
import com.example.crudapplication.callbacks.SharedPreferenceClass;
import com.example.crudapplication.databinding.ActivityLoginRedirectBinding;
import com.example.crudapplication.model.ApiResponse;
import com.example.crudapplication.model.CategoryApiResponse;
import com.example.crudapplication.model.LoginDetails;
import com.example.crudapplication.network.ClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginRedirectActivity extends AppCompatActivity {
    ActivityLoginRedirectBinding bindings;
    SharedPreferenceClass preference;
    LoginDetails details;
    ApiService apiService;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindings = DataBindingUtil.setContentView(this, R.layout.activity_login_redirect);

        preference = new SharedPreferenceClass(this);
        recyclerView = findViewById(R.id.recyclerId);
        preference.checkLogin();
        details = preference.getUserDetail();


        apiService = ClientInstance.getService(details.getToken());
        Call<CategoryApiResponse> call = apiService.getCategoryDetails();
        call.enqueue(new Callback<CategoryApiResponse>() {
            @Override
            public void onResponse(Call<CategoryApiResponse> call, Response<CategoryApiResponse> response) {
                bindings.displayUser.setText(String.format("Hello %s", details.getUsername()));
                generateCategoryList(response.body());
            }

            @Override
            public void onFailure(Call<CategoryApiResponse> call, Throwable t) {
                Toast.makeText(LoginRedirectActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        bindings.addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginRedirectActivity.this, CreateCategoryActivity.class);
                startActivity(intent);
            }
        });
        bindings.viewAllProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginRedirectActivity.this, ViewProductActivity.class);
                intent.putExtra("CategoryId", "");
                startActivity(intent);
            }
        });

    }
    private void generateCategoryList(CategoryApiResponse response){
        adapter = new CategoryAdapter(this,response);

        adapter.setClickListener(new CategoryAdapterClickListener() {
            @Override
            public void addProduct(String categoryId, String categoryName) {
                Intent intent = new Intent(LoginRedirectActivity.this, AddProductActivity.class);
                intent.putExtra("CategoryId", categoryId);
                intent.putExtra("CategoryName", categoryName);
                startActivity(intent);
            }

            @Override
            public void viewProduct(String categoryId) {
                Intent intent = new Intent(LoginRedirectActivity.this, ViewProductActivity.class);
                intent.putExtra("CategoryId", categoryId);
                startActivity(intent);
            }

            @Override
            public void updateProduct(String categoryId) {
                Toast.makeText(LoginRedirectActivity.this, "Update", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void deleteProduct(String categoryId) {
                Toast.makeText(LoginRedirectActivity.this, "Delete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void uploadImage(String categoryId) {
                Intent intent = new Intent(LoginRedirectActivity.this, ImageUploadActivity.class);
                intent.putExtra("CategoryId", categoryId);
                startActivity(intent);
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(LoginRedirectActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}