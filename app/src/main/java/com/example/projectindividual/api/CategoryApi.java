package com.example.projectindividual.api;

import com.example.projectindividual.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryApi {
    @GET("category")
    Call<List<Category>> getCategory();
}
