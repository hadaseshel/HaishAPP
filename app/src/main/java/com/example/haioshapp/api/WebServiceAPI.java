package com.example.haioshapp.api;

import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.entities.Invitations;
import com.example.haioshapp.entities.Message;
import com.example.haioshapp.entities.Transfer;
import com.example.haioshapp.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServiceAPI {
    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call<Void> createUser(@Body User user);

    @GET("contacts")
    Call<List<Contact>> getContacts(@Query("user") String user);

    @POST("contacts")
    Call<Void> createContact(@Query("user") String user,@Body Contact contact);

    @POST("invitations")
    Call<Void> createContactInvet(@Body Invitations invitations);

    @POST("transfer")
    Call<Void> createMessageTransfer(@Body Transfer transfer);

    @GET("contacts/{id}/messages")
    Call<List<Message>> getMessages(@Path(value="id") String id,@Query("user") String user);

    @POST("contacts/{id}/messages")
    Call<Void> createMessage(@Path(value="id") String id,@Query("user") String user,@Body Message message);
}


