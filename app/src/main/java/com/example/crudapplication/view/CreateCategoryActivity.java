package com.example.crudapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.crudapplication.R;
import com.example.crudapplication.callbacks.ApiService;
import com.example.crudapplication.callbacks.SharedPreferenceClass;
import com.example.crudapplication.databinding.ActivityCreateCategoryBinding;
import com.example.crudapplication.model.CategoryBody;
import com.example.crudapplication.model.LoginDetails;
import com.example.crudapplication.network.ClientInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCategoryActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    String categoryName, categoryDescription;
    SharedPreferenceClass sharedPreference;
    ActivityCreateCategoryBinding binding;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_category);
        sharedPreference = new SharedPreferenceClass(this);
        sharedPreference.checkLogin();

        LoginDetails details = sharedPreference.getUserDetail();
        apiService = ClientInstance.getService(details.getToken());

        binding.btnCreateCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryName = binding.addName.getText().toString();
                categoryDescription = binding.addDescription.getText().toString();
                CategoryBody categoryBody = new CategoryBody(categoryName, categoryDescription);

                if (!(categoryName.isEmpty()) && !(categoryDescription.isEmpty())){
                    Call<ResponseBody> request = apiService.createCategory(categoryBody);
                    request.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String feedback = response.body().toString();
                            Toast.makeText(CreateCategoryActivity.this, feedback, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateCategoryActivity.this, LoginRedirectActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(CreateCategoryActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(CreateCategoryActivity.this, "Input Values", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}