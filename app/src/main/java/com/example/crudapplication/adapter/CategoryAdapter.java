package com.example.crudapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudapplication.R;
import com.example.crudapplication.callbacks.CategoryAdapterClickListener;
import com.example.crudapplication.callbacks.CategorySharedPreference;
import com.example.crudapplication.model.ApiResponse;
import com.example.crudapplication.model.CategoryApiResponse;
import com.example.crudapplication.view.AddProductActivity;
import com.example.crudapplication.view.ImageUploadActivity;
import com.example.crudapplication.view.ViewProductActivity;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    private static final String TAG = "TAG";
    private CategoryApiResponse response;
    private Context context;
    CategorySharedPreference manager;
    CategoryAdapterClickListener clickListener;

    public CategoryAdapter(Context context, CategoryApiResponse response){
        this.context = context;
        this.response = response;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_items, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String categoryId = response.responseEntity.body.get(position).categoryId;
        String categoryName = response.responseEntity.body.get(position).categoryName;
        holder.categoryName.setText(categoryName);
        holder.description.setText(response.responseEntity.body.get(position).categoryDescription);

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load("http://app.kreer.ng/image/"+response.responseEntity.body.get(position).categoryImageUrl)
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.categoryImage);
        Log.d(TAG, "onBindViewHolder: " + "http://app.kreer.ng/image/"+response.responseEntity.body.get(position).categoryImageUrl);


        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.addProduct(categoryId, categoryName);
            }
        });
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.viewProduct(categoryId);
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.updateProduct(categoryId);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.deleteProduct(categoryId);
            }
        });
        holder.btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.uploadImage(categoryId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return response.responseEntity.body.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public final View view;
        TextView categoryName;
        TextView description;
        ImageView categoryImage;
        Button btnAdd, btnView, btnUpdate, btnDelete;
        Button btnImage;

        viewHolder(View itemView) {
            super(itemView);
            view = itemView;

            categoryName = view.findViewById(R.id.categoryName);
            description = view.findViewById(R.id.description);
            categoryImage = view.findViewById(R.id.categoryImage);
            btnAdd = view.findViewById(R.id.btnAdd);
            btnView = view.findViewById(R.id.btnView);
            btnUpdate = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);
            btnImage = view.findViewById(R.id.selectButton);
        }
    }
    public void setClickListener(CategoryAdapterClickListener clickListener){
        this.clickListener = clickListener;
    }
}
