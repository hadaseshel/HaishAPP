package com.example.haioshapp.api;

import com.example.haioshapp.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServiceAPI {
    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call<Void> createUser(@Body User user);
}
