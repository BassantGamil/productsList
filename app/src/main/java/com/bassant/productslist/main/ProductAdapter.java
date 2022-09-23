package com.bassant.productslist.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bassant.productslist.R;
import com.bassant.productslist.models.Product;
import com.bassant.productslist.productDetails.ProductDetailsActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {
    private final List<Product> productList;
    private final Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = productList.get(position);
        holder.titleProduct.setText(product.getTitle());
        holder.categoryProduct.setText(product.getCategory());
        holder.priceProduct.setText(product.getPrice());
        holder.ratingProduct.setRating(product.getRating().getRate());
        holder.countRatingProduct.setText(product.getRating().getCount());
        Glide.with(context).load(product.getImage()).into(holder.imageProduct);


        holder.containerProduct.setOnClickListener(v -> {
            Intent i = new Intent(context, ProductDetailsActivity.class);
            i.putExtra("ID", product.getId());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
