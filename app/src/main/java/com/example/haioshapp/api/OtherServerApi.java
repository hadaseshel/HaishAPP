package com.example.haioshapp.api;

import com.example.haioshapp.entities.Contact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OtherServerApi {
    public Retrofit retrofit;
    public WebServiceAPI webServiceAPI;

    public OtherServerApi(String urlServer) {
        String url_proper = urlServer.replace("localhost","10.0.2.2");
        this.retrofit =  new Retrofit.Builder()
                .baseUrl("http://"+url_proper+"/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void invitations(String user, Contact contact){
        Call<Void> call = webServiceAPI.createContact(user,contact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
