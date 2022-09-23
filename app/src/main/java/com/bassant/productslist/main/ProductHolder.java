package com.bassant.productslist.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bassant.productslist.R;


public class ProductHolder extends RecyclerView.ViewHolder {
    public ImageView imageProduct;
    public TextView titleProduct;
    public TextView priceProduct;
    public TextView descriptionProduct;
    public TextView categoryProduct;
    public TextView countRatingProduct;
    public CardView containerProduct;
    public RatingBar ratingProduct;

    public ProductHolder(@NonNull View itemView) {
        super(itemView);
        imageProduct = itemView.findViewById(R.id.item_product_image_view);
        titleProduct = itemView.findViewById(R.id.title_product_textView);
        priceProduct = itemView.findViewById(R.id.price_product_textView);
        descriptionProduct = itemView.findViewById(R.id.description_product_textView);
        categoryProduct = itemView.findViewById(R.id.category_product_textView);
        ratingProduct = itemView.findViewById(R.id.ratingBar_review);
        countRatingProduct = itemView.findViewById(R.id.count_review_textView);
        containerProduct = itemView.findViewById(R.id.container);
    }
}
