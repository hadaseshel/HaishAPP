package com.example.haioshapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haioshapp.adapters.ContactListAdapter;
import com.example.haioshapp.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class Chats extends AppCompatActivity {
    List<Contact> contacts;
//    private AppDB db;
//    private ContactsDao contactsDao;
//    private MessagesDao messagesDao;
//    private ArrayAdapter<Contact> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
//
//        db = Room.databaseBuilder(getApplicationContext(),AppDB.class,"AppDB")
//                .allowMainThreadQueries().build();
//        contactsDao = db.contactsDao();
//        messagesDao = db.messagesDao();

        //statick list
       // contactsDao.insert(new Contact("hadas","hdasi","server1"));
        //contactsDao.insert(new Contact("hail","haliosh","server1"));
       // contactsDao.insert(new Contact("ortal","ortalosh","server1"));

       // contacts = contactsDao.index_contacts();
        contacts = new ArrayList<Contact>();
        contacts.add(new Contact("hadas","hadasi","server1","hey","15.12.22"));
        contacts.add(new Contact("hail","hail","server1","baaa","16.12.22"));
        contacts.add(new Contact("ortal","ortal","server1","loo","17.12.22"));
        contacts.add(new Contact("SHIRA","shira","server1","loo","18.12.22"));
        String user_id;
        if(getIntent().getExtras()!=null){
            user_id = getIntent().getExtras().getString("user_id");
        }

//        ListView listView = findViewById(R.id.chat_listview);
//        arrayAdapter = new ArrayAdapter<Contact>(this,
//                android.R.layout.simple_list_item_1,contacts);
//        listView.setAdapter(arrayAdapter);

        RecyclerView recyclerView = findViewById(R.id.chat_recyclerView);
        final ContactListAdapter adapter = new ContactListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // set contacts list
        adapter.setContacts(contacts);

        // pass the add chat screen
        ImageButton btn_add_chat = findViewById(R.id.add_chat_icon);
        btn_add_chat.setOnClickListener(v->{
            // need to add validazia
            Intent intent = new Intent(this,NewChat.class);
            //intent.putExtra("contactsDao",contactsDao);
            startActivity(intent);
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        contacts.clear();
//        contacts.addAll(contactsDao.index_contacts());
//        arrayAdapter.notifyDataSetChanged();
//    }
}