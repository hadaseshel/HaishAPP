package com.example.haioshapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
        Button btnConfirm = findViewById(R.id.def_confirm);
        btnConfirm.setOnClickListener(v->{
            EditText edServer = findViewById(R.id.def_new_url);
            String serverStr = edServer.getText().toString();
            Intent intent = new Intent(this,Login.class);
            intent.putExtra("user_server",serverStr);
            startActivity(intent);
        });
    }
}