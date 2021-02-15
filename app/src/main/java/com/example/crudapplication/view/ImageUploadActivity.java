package com.example.crudapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudapplication.R;
import com.example.crudapplication.callbacks.ApiService;
import com.example.crudapplication.callbacks.SharedPreferenceClass;
import com.example.crudapplication.model.ImageUpload;
import com.example.crudapplication.model.LoginDetails;
import com.example.crudapplication.network.ClientInstance;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class ImageUploadActivity extends AppCompatActivity {
    Button button, upload; TextView display;
    SharedPreferenceClass sharedPreference; ApiService apiService;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
        button = findViewById(R.id.selectImage);
        display = findViewById(R.id.displayUrl);
        upload = findViewById(R.id.uploadImage);
        sharedPreference = new SharedPreferenceClass(this);

        LoginDetails details = sharedPreference.getUserDetail();
        apiService = ClientInstance.getService(details.getToken());

        intent = getIntent();
        String categoryId = intent.getStringExtra("CategoryId");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), display.getText().toString());
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), categoryId);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", "image.jpg", requestFile);
                //ImageUpload imageUpload = new ImageUpload(categoryId, body);
                Call<ResponseBody> response = apiService.uploadImage(body, filename);
                response.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.raw().code() == 200){
                            Toast.makeText(ImageUploadActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(ImageUploadActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ImageUploadActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                String pathHolder = data.getData().getPath();
                display.setText(pathHolder);
            }
        }
    }
}




//  switch (requestCode) {
//          case 7:
//          if (resultCode == RESULT_OK) {
//          String PathHolder = data.getData().getPath();
//          Toast.makeText(ImageUploadActivity.this, PathHolder, Toast.LENGTH_LONG).show();
//          }
//          break;
//          }