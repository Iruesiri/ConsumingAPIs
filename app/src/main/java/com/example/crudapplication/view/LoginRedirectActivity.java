package com.example.crudapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.crudapplication.R;
import com.example.crudapplication.adapter.CategoryAdapter;
import com.example.crudapplication.callbacks.ApiService;
import com.example.crudapplication.callbacks.SharedPreferenceClass;
import com.example.crudapplication.databinding.ActivityLoginRedirectBinding;
import com.example.crudapplication.model.CategoryResponse;
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
        Call<CategoryResponse> call = apiService.getCategoryDetails();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                //ResponseBody response1 = (ResponseBody) response.body();
                bindings.displayUser.setText(String.format("Hello %s", details.getUsername()));
                generateCategoryList(response.body());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
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
                startActivity(intent);
            }
        });

    }
    private void generateCategoryList(CategoryResponse response){
        adapter = new CategoryAdapter(this,response);
        LinearLayoutManager layoutManager = new LinearLayoutManager(LoginRedirectActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
//    public void popupWindowOnClick(View view){
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.activity_create_category, null);
//
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//    }
}