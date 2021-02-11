package com.example.crudapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.crudapplication.callbacks.ApiService;
import com.example.crudapplication.callbacks.CustomApiCallback;
import com.example.crudapplication.callbacks.CustomApiCallbackImpl;
import com.example.crudapplication.callbacks.LoginResponse;
import com.example.crudapplication.callbacks.SharedPreferenceClass;
import com.example.crudapplication.databinding.ActivityMainBinding;
import com.example.crudapplication.model.LoginDetails;
import com.example.crudapplication.view.LoginRedirectActivity;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.crudapplication.network.ClientInstance.getLoginService;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    SharedPreferenceClass sharedPreference;
    ActivityMainBinding binding;
    private String usernameValue; private String passwordValue;
    ApiService apiService;
    LoginDetails details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_main);
        sharedPreference = new SharedPreferenceClass(this);
        apiService = getLoginService();

        init();
    }

    private void init() {
        binding.btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameValue = binding.user.getText().toString();
                passwordValue = binding.pass.getText().toString();

                if (!(usernameValue.isEmpty()) && !(passwordValue.isEmpty()))
                    {
                        details = new LoginDetails(usernameValue, passwordValue);
                        signInApiCall();
                    }
                else {
                    Toast.makeText(MainActivity.this, "Enter Required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signInApiCall() {
        Call<ResponseBody> service = apiService.login(details);
        service.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Headers headers = response.headers();
                String authorizationToken = headers.get("Authorization");
                String userId = headers.get("UserId");
                String name = headers.get("First_name");

                if (authorizationToken == null){
                    Toast.makeText(MainActivity.this, "Invalid login", Toast.LENGTH_SHORT).show();
                }
                else {
                    sharedPreference.createLoginSession(authorizationToken, name, userId);
                    Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginRedirectActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

//    public void signInApi(){
//        Call<LoginResponse> service = apiService.login(details);
//        service.enqueue(new CustomApiCallbackImpl<LoginResponse>(new CustomApiCallback<LoginResponse>() {
//
//            @Override
//            public void onStarted() {
//                Log.d(TAG, "onStarted: " + "Api call started");
//            }
//
//            @Override
//            public void onSuccess(LoginResponse loginResponse) {
//                Log.d(TAG, "onSuccess: " + "Api call Successful");
//            }
//
//            @Override
//            public void onError() {
//                Log.d(TAG, "onError: " + "Api call has an error");
//            }
//
//            @Override
//            public void onNetworkError() {
//                Log.d(TAG, "onNetworkFailure: " + "Api call has network error");
//            }
//        }));
//    }
}