package com.example.haioshapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haioshapp.adapters.ContactListAdapter;
import com.example.haioshapp.api.UserAPI;
import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.rooms.AppDB;
import com.example.haioshapp.rooms.ContactsDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chats extends AppCompatActivity {
    Context context = this;
    List<Contact> contacts; // the contacts list
    List<Contact> contacts_from_server;
    private String userID;
    private String server;
    private AppDB db; // the DB of the app
    private ContactsDao contactsDao; // by this object we will add contact
    ContactListAdapter adapter; // adapter for the spacial view of each contact
    private RecyclerView recyclerView; // the recycle view of the contacts list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        userID = getIntent().getExtras().getString("user_id");
        server = getIntent().getExtras().getString("user_server");
        db = AppDB.getDB(this);
        contactsDao = db.contactsDao();

        // get users
        UserAPI userAPI = new UserAPI(server);
        Call<List<Contact>> call = userAPI.webServiceAPI.getContacts(userID);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contacts_from_server = response.body();
                this.erazeRoomAndUpdateRoom();
            }

            private void erazeRoomAndUpdateRoom() {
                // eraze the room
                contactsDao.deleteAll();
                for(Contact contact:contacts_from_server){
                    Contact new_contact = new Contact();
                    new_contact.setContactId(contact.getId());
                    new_contact.setServer(contact.getServer());
                    new_contact.setName(contact.getName());
                    new_contact.setImage(contact.getImage());
                    new_contact.setChat(contact.getChat());
                    new_contact.setLast(contact.getLast());
                    new_contact.setLastdate(contact.getLastdate());
                    new_contact.setUserId(userID);
                    new_contact.setId(userID +contact.getId());
                    new_contact.setUserServer(server);
                    contactsDao.insert(new_contact);
                }
                // get the contacts list
                contacts = contactsDao.getContactOfUser(userID);
                // create adapter
                adapter = new ContactListAdapter(context);
                // set the list on the adapter
                adapter.setContacts(contacts);
                // set the adapter on the recycle view
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
            }
        });

        contacts = new ArrayList<>(); // create new list
        recyclerView = findViewById(R.id.chat_recyclerView); //get the recycle view
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //bind the recycle view a view

        // pass to the add chat screen
        ImageButton btn_add_chat = findViewById(R.id.add_chat_icon);
        btn_add_chat.setOnClickListener(v->{
            Intent intent = new Intent(this,NewChat.class);
            intent.putExtra("user_id",userID);
            intent.putExtra("user_server",server);
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