package com.example.haioshapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Chats extends AppCompatActivity {

    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        list = new ArrayList<String>();
        list.add("hadas");
        list.add("hail");
        list.add("shira");

        ListView listView = findViewById(R.id.chat_listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        // pass the add chat screen
        ImageButton btn_add_chat = findViewById(R.id.add_chat_icon);
        btn_add_chat.setOnClickListener(v->{
            // need to add validazia
            Intent intent = new Intent(this,NewChat.class);
            startActivity(intent);
        });
    }
}