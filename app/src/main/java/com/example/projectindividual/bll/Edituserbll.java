package com.example.projectindividual.bll;

import com.example.projectindividual.Url.Url;
import com.example.projectindividual.api.UserApi;
import com.example.projectindividual.model.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Edituserbll {
    boolean isSuccess = false;
    public boolean Useredit(User user) {
        UserApi usersAPI = Url.getInstance().create(UserApi.class);
        Call<User> usersCall = usersAPI.UpdateDetails(Url.token,user);
        try {
            Response<User> EditResponse = usersCall.execute();
            if (EditResponse.isSuccessful()) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
