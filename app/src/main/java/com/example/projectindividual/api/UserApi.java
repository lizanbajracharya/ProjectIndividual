package com.example.projectindividual.api;

import com.example.projectindividual.model.User;
import com.example.projectindividual.serverresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface UserApi {
    @POST("user/signup")
    Call<SignUpResponse> registerUser(@Body User users);

    @FormUrlEncoded
    @POST("user/login")
    Call<SignUpResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @GET("user/me")
    Call<User> getUserDetails(@Header("Authorization") String token);

    @PATCH("user/me")
    Call<User> UpdateDetails(@Header("Authorization") String token, @Body User users);

    @POST("user/logout")
    Call<Void> logout(@Header("Authorization") String token);
}

