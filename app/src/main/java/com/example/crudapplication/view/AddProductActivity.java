package com.example.crudapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.crudapplication.R;
import com.example.crudapplication.callbacks.ApiService;
import com.example.crudapplication.callbacks.CategorySharedPreference;
import com.example.crudapplication.callbacks.SharedPreferenceClass;
import com.example.crudapplication.databinding.ActivityAddProductBinding;
import com.example.crudapplication.model.LoginDetails;
import com.example.crudapplication.model.ProductDetails;
import com.example.crudapplication.network.ClientInstance;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    ActivityAddProductBinding binding;
    CategorySharedPreference manager;
    ApiService apiService;
    SharedPreferenceClass sharedPreference;
    String categoryId, categoryName, productName, productVatAmount, warranty, commonName, productAmount, productDiscount, productShortDescription, productFullDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_product);
        sharedPreference = new SharedPreferenceClass(this);
        manager = new CategorySharedPreference(this);

        LoginDetails details = sharedPreference.getUserDetail();
        apiService = ClientInstance.getService(details.getToken());

        binding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> user = manager.getUserDetails();
                categoryId = user.get(CategorySharedPreference.CAT_ID);
                categoryName = user.get(CategorySharedPreference.CAT_NAME);

                productAmount = binding.amount.getText().toString();
                productName = binding.productName.getText().toString();
                productVatAmount = binding.vatAmount.getText().toString();
                warranty = binding.Warranty.getText().toString();
                commonName = binding.commonName.getText().toString();
                productDiscount = binding.discount.getText().toString();
                productShortDescription = binding.productShortDescription.getText().toString();
                productFullDescription = binding.productFullDescription.getText().toString();

                ProductDetails productDetails = new ProductDetails(categoryId, categoryName, productName, productVatAmount, warranty, commonName, productAmount, productDiscount, productShortDescription, productFullDescription);
                Call<ResponseBody> request = apiService.createProduct(productDetails);
                request.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 200) {
                            Toast.makeText(AddProductActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddProductActivity.this, LoginRedirectActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(AddProductActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AddProductActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}