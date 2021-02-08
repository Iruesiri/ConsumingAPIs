package com.example.crudapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.crudapplication.R;
import com.example.crudapplication.adapter.ProductAdapter;
import com.example.crudapplication.callbacks.ApiService;
import com.example.crudapplication.callbacks.SharedPreferenceClass;
import com.example.crudapplication.databinding.ActivityViewProductBinding;
import com.example.crudapplication.model.ApiResponse;
import com.example.crudapplication.model.LoginDetails;
import com.example.crudapplication.model.ProductApiResponse;
import com.example.crudapplication.network.ClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProductActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    ActivityViewProductBinding binding;
    private RecyclerView recyclerView;
    SharedPreferenceClass preference;
    ProductAdapter adapter;
    LoginDetails details;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_product);
        recyclerView = binding.recycler;

        preference = new SharedPreferenceClass(this);
        preference.checkLogin();
        details = preference.getUserDetail();
        apiService = ClientInstance.getService(details.getToken());

        Call<ProductApiResponse> call = apiService.getAllProductDetails();
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