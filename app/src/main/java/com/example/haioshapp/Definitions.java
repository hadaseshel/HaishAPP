package com.example.haioshapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Definitions extends AppCompatActivity {
    private String server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definitions);
        server = getIntent().getExtras().getString("user_server");

        TextView tvCurrenServer = findViewById(R.id.def_current_server);
        tvCurrenServer.setText(server);
    }
}