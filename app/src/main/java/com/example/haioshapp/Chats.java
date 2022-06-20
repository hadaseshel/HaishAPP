package com.example.haioshapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haioshapp.adapters.ContactListAdapter;
import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.rooms.AppDB;
import com.example.haioshapp.rooms.ContactsDao;

import java.util.ArrayList;
import java.util.List;

public class Chats extends AppCompatActivity {
    List<Contact> contacts; // the contacts list
    private String userID;
    private AppDB db; // the DB of the app
    private ContactsDao contactsDao; // by this object we will add contact
    ContactListAdapter adapter; // adapter for the spacial view of each contact
    private RecyclerView recyclerView; // the recycle view of the contacts list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        db = AppDB.getDB(this);
        contactsDao = db.contactsDao();
        //contactsDao.deleteAll();

        // insert to the room contacts
//        contactsDao.insert(new Contact("aaa","aaa","server1","hey","15.12.22"));
//        contactsDao.insert(new Contact("bbb","bbb","server1","baaa","16.12.22"));
//        contactsDao.insert(new Contact("ccc","ccc","server1","loo","17.12.22"));
        // delete the contacts list from DB
       //contactsDao.deleteAll();

        userID = getIntent().getExtras().getString("user_id");

        contacts = new ArrayList<>(); // create new list
        recyclerView = findViewById(R.id.chat_recyclerView); //get the recycle view
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //bind the recycle view a view

        // pass to the add chat screen
        ImageButton btn_add_chat = findViewById(R.id.add_chat_icon);
        btn_add_chat.setOnClickListener(v->{
            Intent intent = new Intent(this,NewChat.class);
            intent.putExtra("user_id",userID);
            startActivity(intent);
        });
    }

    // refresh the contact list by the room
    @Override
    protected void onResume() {
        super.onResume();
        // get the contacts list
        contacts = contactsDao.getContactOfUser(userID);
        // create adapter
        adapter = new ContactListAdapter(this);
        // set the list on the adapter
        adapter.setContacts(contacts);
        // set the adapter on the recycle view
        recyclerView.setAdapter(adapter);
    }
}