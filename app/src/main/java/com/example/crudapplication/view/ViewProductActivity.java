package com.example.crudapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.crudapplication.R;
import com.example.crudapplication.adapter.ProductAdapter;
import com.example.crudapplication.callbacks.ApiService;
import com.example.crudapplication.callbacks.CategorySharedPreference;
import com.example.crudapplication.callbacks.SharedPreferenceClass;
import com.example.crudapplication.databinding.ActivityViewProductBinding;
import com.example.crudapplication.model.CategoryBody;
import com.example.crudapplication.model.LoginDetails;
import com.example.crudapplication.model.ProductApiResponse;
import com.example.crudapplication.network.ClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProductActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    ActivityViewProductBinding binding;
    CategorySharedPreference manager;
    private RecyclerView recyclerView;
    SharedPreferenceClass preference;
    ProductAdapter adapter;
    ApiService apiService;
    String categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_product);
        recyclerView = binding.recycler;

        Intent intent = getIntent();
        categoryId = intent.getStringExtra("CategoryId");

        preference = new SharedPreferenceClass(this);
        preference.checkLogin();
        LoginDetails details = preference.getUserDetail();
        apiService = ClientInstance.getService(details.getToken());

        Call<ProductApiResponse> call = apiService.getProductbyCategory(categoryId, 0, 5);
        Log.d(TAG, "Category Id: " + categoryId);

        Call<ProductApiResponse> call2 = apiService.getAllProductDetails();
        if(categoryId.matches("")){
            getProduct(call2);
        }
        else {
            getProduct(call);
        }
    }

    private void getProduct(Call<ProductApiResponse> call) {
        call.enqueue(new Callback<ProductApiResponse>() {

            @Override
            public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {
                Toast.makeText(ViewProductActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: " + response.body());
                generateProductList(response.body());
            }

            @Override
            public void onFailure(Call<ProductApiResponse> call, Throwable t) {
                Toast.makeText(ViewProductActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateProductList(ProductApiResponse response) {
        adapter = new ProductAdapter(this,response);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ViewProductActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}