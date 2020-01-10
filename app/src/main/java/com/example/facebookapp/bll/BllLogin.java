package com.example.facebookapp.bll;

import com.example.facebookapp.api.ApiUser;
import com.example.facebookapp.model.User;
import com.example.facebookapp.strictmode.StrictModeClass;
import com.example.facebookapp.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;


public class BllLogin {
    private ApiUser apiUser = Url.getInstance().create(ApiUser.class);
    private boolean isloggedIn = false;

    public boolean userLogin(User user) {
        Call<User> userCall = apiUser.userLogin(user);
        StrictModeClass.StrictMode();

        try {
            Response<User> loginResponse = userCall.execute();
            if (loginResponse.isSuccessful()) {
                isloggedIn = true;
                Url.token += loginResponse.body().getToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isloggedIn;

    }
}