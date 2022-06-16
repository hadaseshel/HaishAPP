package com.example.haioshapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haioshapp.entities.Contact;

public class NewChat extends AppCompatActivity {
    //private ContactsDao contactsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);
       /*
        if(getIntent().getExtras()!=null) {
            contactsDao = (ContactsDao) getIntent().getSerializableExtra("contactsDao");
        }*/

        Button btn_new_chat = findViewById(R.id.new_chat_button);
        btn_new_chat.setOnClickListener(v-> {
            String contact_name = findViewById(R.id.new_chat_contact_name).toString();
            String contact_nickname = findViewById(R.id.new_chat_nickname).toString();
            String contact_server = findViewById(R.id.new_chat_server).toString();
            Contact contact = new Contact(contact_name,contact_nickname,contact_server);
            //Intent intent = new Intent(this,Contact.class);
            //intent.putExtra("contact",contact);
            //startActivity(intent);
            finish();
            });
    }
}