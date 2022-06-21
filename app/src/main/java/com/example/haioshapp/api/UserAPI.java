package com.example.haioshapp.api;

import com.example.haioshapp.MyAPP;
import com.example.haioshapp.R;
import com.example.haioshapp.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    public Retrofit retrofit;
    public WebServiceAPI webServiceAPI;

    public UserAPI(){
        retrofit = new Retrofit.Builder()
                .baseUrl(MyAPP.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }
    public void get(){
        Call<List<User>> call = webServiceAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                users.get(0);
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }
}
