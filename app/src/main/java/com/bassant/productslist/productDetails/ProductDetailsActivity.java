package com.bassant.productslist.productDetails;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bassant.productslist.R;
import com.bassant.productslist.api.ApiClient;
import com.bassant.productslist.api.ApiService;
import com.bassant.productslist.models.Product;
import com.bumptech.glide.Glide;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {

    private int productId;
    private ApiService api;
    public ImageView imageProductDetails;
    public TextView titleProductDetails;
    public TextView priceProductDetails;
    public TextView descriptionProductDetails;
    public TextView categoryProductDetails;
    public TextView countRatingProductDetails;
    public LinearLayout ratingLinear;
    public RatingBar ratingProductDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_product);
        init();
        getProductDetails();
    }

    private void init() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.details);

        imageProductDetails = findViewById(R.id.item_product_image_view);
        titleProductDetails = findViewById(R.id.title_product_textView);
        priceProductDetails = findViewById(R.id.price_product_textView);
        descriptionProductDetails = findViewById(R.id.description_product_textView);
        categoryProductDetails = findViewById(R.id.category_product_textView);
        ratingProductDetails = findViewById(R.id.ratingBar_review);
        countRatingProductDetails = findViewById(R.id.count_review_textView);
        ratingLinear = findViewById(R.id.rating_review_layout);
        ratingLinear.setVisibility(View.VISIBLE);
        categoryProductDetails.setVisibility(View.VISIBLE);
        descriptionProductDetails.setVisibility(View.VISIBLE);
        productId = getIntent().getIntExtra("ID", 0);
        api = ApiClient.getInstance(this);
    }

    private void getProductDetails() {

        api.getProductDetails(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {


                if (response.body() != null) {
                    titleProductDetails.setText(response.body().getTitle());
                    descriptionProductDetails.setText(response.body().getDescription());
                    priceProductDetails.setText(response.body().getPrice());
                    categoryProductDetails.setText(response.body().getCategory());
                    categoryProductDetails.setText(response.body().getCategory());
                    ratingProductDetails.setRating(response.body().getRating().getRate());
                    countRatingProductDetails.setText(response.body().getRating().getCount());
                    Glide.with(getApplicationContext()).load(response.body().getImage()).into(imageProductDetails);

                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Please check your connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}