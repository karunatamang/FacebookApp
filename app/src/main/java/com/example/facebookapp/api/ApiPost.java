package com.example.facebookapp.api;

import com.example.facebookapp.model.Post;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiPost {
    @Multipart
    @POST("upload")
    Call<String> uploadImage(@Part MultipartBody.Part img);

    @POST("addpost")
    Call<Void> addPost(@Body Post post);

    @GET("showpost")
    Call<List<Post>> showPost();
}
