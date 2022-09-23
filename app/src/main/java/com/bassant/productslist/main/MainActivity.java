package com.bassant.productslist.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bassant.productslist.R;
import com.bassant.productslist.api.ApiClient;
import com.bassant.productslist.api.ApiService;
import com.bassant.productslist.models.Product;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    private RecyclerView recyclerView;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getAllPost();
    }

    private void init() {
        apiService = ApiClient.getInstance(this);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.home);
        loadingPB = findViewById(R.id.idPBLoading);
        recyclerView = findViewById(R.id.productRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void getAllPost() {
        loadingPB.setVisibility(View.VISIBLE);
        apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {

                loadingPB.setVisibility(View.GONE);
                ProductAdapter productAdapter = new ProductAdapter(response.body(), getApplicationContext());
                recyclerView.setAdapter(productAdapter);
            }
            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Please check your connection ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}