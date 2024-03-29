package com.example.haioshapp.api;

import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.entities.Message;
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

    public UserAPI(String urlServer){
        String url_proper = urlServer.replace("localhost","10.0.2.2");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://"+url_proper+"/api/")
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
    public void createUser(User user){
        Call<Void> call = webServiceAPI.createUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
              // in response  
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // in response
            }
        });
    }

    public void createContact(String user, Contact contact){
        Call<Void> call = webServiceAPI.createContact(user,contact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // in response
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // in fail
            }
        });
    }

    public void createdMessage(String contactId,String user, Message message){
        Call<Void> call = webServiceAPI.createMessage(contactId,user,message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // in response
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // in fail
            }
        });
    }
}
