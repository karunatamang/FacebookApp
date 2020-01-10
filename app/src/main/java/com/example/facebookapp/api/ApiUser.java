package com.example.facebookapp.api;

import com.example.facebookapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiUser {
    @POST("login")
    Call<User> userLogin(@Body User user);

    @GET("users")
    Call<List<User>> showUser();

    @POST("signup")
    Call<Void> addUser(@Body User user);
}
