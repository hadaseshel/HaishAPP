package com.example.haioshapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haioshapp.adapters.MessageListAdapter;
import com.example.haioshapp.entities.Message;
import com.example.haioshapp.rooms.AppDB;
import com.example.haioshapp.rooms.MessagesDao;

import java.util.ArrayList;
import java.util.List;

public class SingleChat extends AppCompatActivity {
    List<Message> messages; // the message list
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

//        messagesDao.insert(new Message("gvdv","heyy","12.12.22",true));
//        messagesDao.insert(new Message("gvdv1","bye","13.12.22",true));
//        messagesDao.insert(new Message("gvdv1","loo","14.12.22",true));
//        list = new ArrayList<String>();
//        list.add("hi");
//        list.add("how are you?");
//        list.add("fine");
//
//        ListView listView = findViewById(R.id.singlechat_recyclerView);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,list);
//        listView.setAdapter(arrayAdapter);
    }
    // refresh the contact list by the room
    @Override
    protected void onResume() {
        super.onResume();
        // get the contacts lisr
        messages = messagesDao.index();
        // create adapter
        adapter = new MessageListAdapter(this);
        // set the list on the adapter
        adapter.setMessages(messages);
        // set the adapter on the recycle view
        recyclerView.setAdapter(adapter);
    }
}