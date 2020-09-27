package com.example.projectindividual.api;

import com.example.projectindividual.model.Cart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartApi {
    @FormUrlEncoded
    @POST("cart")
    Call<Void> addcart(@Header("Authorization") String token, @Field("Productid") String Productid);

    @GET("cart")
    Call<List<Cart>> getCart(@Header("Authorization") String token);

    @DELETE("cart/{id}")
    Call<Void> removeCart(@Path("id") String id);
}
