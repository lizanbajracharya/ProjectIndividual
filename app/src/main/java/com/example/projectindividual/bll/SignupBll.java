package com.example.projectindividual.bll;

import com.example.projectindividual.Url.Url;
import com.example.projectindividual.api.UserApi;
import com.example.projectindividual.model.User;
import com.example.projectindividual.serverresponse.SignUpResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SignupBll {
    boolean isSuccess = false;
    String id;
    public boolean Useradd(User user) {
        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<SignUpResponse> usersCall = usersAPI.registerUser(user);
        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful()) {
                Url.token += loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
