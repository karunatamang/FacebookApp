package com.example.facebookapp.api;

import com.example.facebookapp.model.Post;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiPost {
    @Multipart
    @POST("upload")
    Call<Post> uploadImage(@Part MultipartBody.Part img);

    @POST("addpost")
    Call<Void> addPost(@Body Post post);
}
