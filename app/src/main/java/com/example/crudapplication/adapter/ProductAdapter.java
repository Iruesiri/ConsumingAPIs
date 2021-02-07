package com.example.crudapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudapplication.R;
import com.example.crudapplication.model.CategoryResponse;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.viewHolder>{
    private CategoryResponse response;
    private Context context;

    public ProductAdapter(Context context, CategoryResponse response){
        this.context = context;
        this.response = response;
    }
    @NonNull
    @Override
    public ProductAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_items, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.viewHolder holder, int position) {
        holder.categoryName.setText(response.responseEntity.productDetails.get(position).categoryName);
        holder.productName.setText(response.responseEntity.productDetails.get(position).productName);
        holder.commonName.setText(response.responseEntity.productDetails.get(position).commonName);
        holder.productVat.setText(response.responseEntity.productDetails.get(position).productVatAmount);
        holder.productAmount.setText(response.responseEntity.productDetails.get(position).productAmount);
        holder.warranty.setText(response.responseEntity.productDetails.get(position).warranty);
        holder.shortDescription.setText(response.responseEntity.productDetails.get(position).productShortDescription);
        holder.longDescription.setText(response.responseEntity.productDetails.get(position).productFullDescription);
    }

    @Override
    public int getItemCount() {
        return response.responseEntity.body.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        public final View view;
        TextView categoryName, productName, productVat, productAmount, commonName, warranty, shortDescription, longDescription;

        viewHolder(View itemView) {
            super(itemView);
            view = itemView;

            categoryName = view.findViewById(R.id.categoryName);
            productName = view.findViewById(R.id.productName);
            productVat = view.findViewById(R.id.vat);
            productAmount = view.findViewById(R.id.productAmount);
            commonName = view.findViewById(R.id.commonname);
            warranty = view.findViewById(R.id.warranty);
            shortDescription = view.findViewById(R.id.shortDescription);
            longDescription = view.findViewById(R.id.longDescription);

        }
    }
}
