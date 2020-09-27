package com.example.projectindividual.api;

import com.example.projectindividual.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApi {
    @GET("product")
    Call<List<Product>> getProduct();

    @GET("product/getByCategory/{id}")
    Call<List<Product>> getProductByID(@Path("id") String CategoryID);

    @GET("product/search/{ItemName}")
    Call<List<Product>> searchProduct(@Path("ItemName") String ItemName);
}
