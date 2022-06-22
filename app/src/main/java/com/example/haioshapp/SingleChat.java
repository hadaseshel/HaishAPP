package com.example.haioshapp;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haioshapp.adapters.MessageListAdapter;
import com.example.haioshapp.api.UserAPI;
import com.example.haioshapp.entities.Message;
import com.example.haioshapp.rooms.AppDB;
import com.example.haioshapp.rooms.MessagesDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleChat extends AppCompatActivity {
    List<Message> messages; // the message list
    List<Message> messages_of_server; // the message list of server
    private Context context = this;
    private String userId;
    private String contactId;
    private UserAPI userAPI;
    private AppDB db; // the DB of the app
    private MessagesDao messagesDao; // by this object we will add contact
    MessageListAdapter adapter; // adapter for the spacial view of each contact
    private RecyclerView recyclerView; // the recycle view of the contacts list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);

        db = AppDB.getDB(this);
        messagesDao = db.messagesDao();
        messages = new ArrayList<>(); // create new list
        recyclerView = findViewById(R.id.singlechat_recyclerView); //get the recycle view
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //bind the recycle view a view
        contactId = getIntent().getExtras().getString("contact_id");
        userId = getIntent().getExtras().getString("user_id");
//        //messagesDao.deleteAll(); //delet all

        // get messages
        userAPI = new UserAPI();
        Call<List<Message>> call = userAPI.webServiceAPI.getMessages(contactId,userId);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                messages_of_server = response.body();
                this.erazeEndUpdadeMessageRoom();
            }

            private void erazeEndUpdadeMessageRoom() {
                messagesDao.deleteAll();
                String contactIdForMessage = userId+contactId;
                for(Message message: messages_of_server){
                    Message message_for_dao = new Message(message.getId(),contactIdForMessage,
                            message.getContent(),message.getCreated(),message.isSent());
                    messagesDao.insert(message_for_dao);
                }
                // get the contacts list
                messages = messagesDao.getMessagesOfContact(contactIdForMessage);
                // create adapter
                adapter = new MessageListAdapter(context);
                // set the list on the adapter
                adapter.setMessages(messages);
                // set the adapter on the recycle view
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });

    }
    // refresh the contact list by the room
    @Override
    protected void onResume() {
        super.onResume();
        // get the contacts list
        messages = messagesDao.getMessagesOfContact(contactId);
        // create adapter
        adapter = new MessageListAdapter(this);
        // set the list on the adapter
        adapter.setMessages(messages);
        // set the adapter on the recycle view
        recyclerView.setAdapter(adapter);
    }
}