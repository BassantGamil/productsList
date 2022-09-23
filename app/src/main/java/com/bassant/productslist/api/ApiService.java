package com.bassant.productslist.api;

import com.bassant.productslist.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("products")
    Call<List<Product>>
    getProducts();

    @GET("products/{id}")
    Call<Product>
    getProductDetails(@Path("id") int productID);

}