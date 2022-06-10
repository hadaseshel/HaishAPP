package com.example.haioshapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // pass chat screen
        Button btn_login = findViewById(R.id.register_button);
        btn_login.setOnClickListener(v->{
            // need to add validazia
            Intent intent = new Intent(this,Chats.class);
            startActivity(intent);
        });

        // pass to the register page
        TextView pass_register = findViewById(R.id.regist_pass_login);
        pass_register.setOnClickListener(v->{
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
        });
    }
}
