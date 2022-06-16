package com.example.haioshapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SingleChat extends AppCompatActivity {
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);

        list = new ArrayList<String>();
        list.add("hi");
        list.add("how are you?");
        list.add("fine");

        ListView listView = findViewById(R.id.singlechat_recyclerView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
    }
}