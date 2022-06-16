package com.example.haioshapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    // this link may be useful
    //https://www.youtube.com/watch?v=sOJRJtM_iu0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // pass chat screen
        Button btn_login = findViewById(R.id.lg_button);
        btn_login.setOnClickListener(v->{
            // need to add validazia
            Intent intent = new Intent(this,Chats.class);
            EditText user_id = findViewById(R.id.lg_username_editText);
            intent.putExtra("user_id",user_id.toString());
            startActivity(intent);
        });

        // pass to the register page
        TextView pass_register = findViewById(R.id.lg_pass_register);
        pass_register.setOnClickListener(v->{
            Intent intent = new Intent(this,Register.class);
            startActivity(intent);
        });
    }
}