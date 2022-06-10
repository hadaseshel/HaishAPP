package com.example.haioshapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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
    }
}