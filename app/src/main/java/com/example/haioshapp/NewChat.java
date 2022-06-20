package com.example.haioshapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.rooms.AppDB;
import com.example.haioshapp.rooms.ContactsDao;

public class NewChat extends AppCompatActivity {
    private AppDB db; // the DB of the app
    private ContactsDao contactsDao; // by this object we will add contact
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);

        db = AppDB.getDB(this);
        contactsDao = db.contactsDao();
        userId = getIntent().getExtras().getString("user_id");

        Button btn_new_chat = findViewById(R.id.new_chat_button);
        btn_new_chat.setOnClickListener(v-> {
            EditText contact_name = findViewById(R.id.new_chat_contact_name);
            EditText contact_nickname = findViewById(R.id.new_chat_nickname);
            EditText contact_server = findViewById(R.id.new_chat_server);
            Contact contact = new Contact(userId+contact_name.getText().toString(),contact_name.getText().toString()
                    ,userId,contact_nickname.getText().toString(),
                    contact_server.getText().toString());
            contactsDao.insert(contact); //insert the contact to the room
            finish();
            });
    }
}