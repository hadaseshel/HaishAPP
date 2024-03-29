package com.example.haioshapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haioshapp.api.UserAPI;
import com.example.haioshapp.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private List<User> users;
    private String server = "10.0.2.2:5034";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(getIntent().getExtras()!= null){
            server = getIntent().getExtras().getString("user_server");
        }

        // get the users from the server
        UserAPI userAPI = new UserAPI(server);
        Call<List<User>> call = userAPI.webServiceAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
               users = response.body();
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });

        // pass chat screen
        Button btn_login = findViewById(R.id.lg_button);
        btn_login.setOnClickListener(v->{
            // need to add validazia
            Intent intent = new Intent(this,Chats.class);
            EditText user_id = findViewById(R.id.lg_username_editText);
            String user_id_string = user_id.getText().toString();
            intent.putExtra("user_id",user_id_string);
            intent.putExtra("user_server",server);
            EditText user_pass = findViewById(R.id.lg_editTextPassword);
            String user_pass_string = user_pass.getText().toString();
            int move_to_intent_flag = 0;
            if(users == null){
                TextView tv = (TextView)findViewById(R.id.login_error);
                tv.setText("There are no registered users on the server, or the server is incorrect");
                return;
            }
            for(User user:users){
                String current_user_id = user.getId();
                String current_user_pass = user.getPassword();
                // pass the chat screen only if the user is exist in server
                if(user_id_string.equals(current_user_id) && current_user_pass.equals(user_pass_string)){
                    move_to_intent_flag = 1;
                    TextView tv = (TextView)findViewById(R.id.login_error);
                    tv.setText("");
                    startActivity(intent);
                }
            }
            if(move_to_intent_flag==0){
               TextView tv = (TextView)findViewById(R.id.login_error);
               tv.setText("The username or password are incorrect");
            }
        });

        // pass to the register page
        TextView pass_register = findViewById(R.id.lg_pass_register);
        pass_register.setOnClickListener(v->{
            Intent intent = new Intent(this,Register.class);
            intent.putExtra("user_server",server);
            startActivity(intent);
        });

        // pass to the setting screen
        TextView pass_setting = findViewById(R.id.lg_pass_def);
        pass_setting.setOnClickListener(v->{
            Intent intent = new Intent(this,Definitions.class);
            intent.putExtra("user_server",server);
            startActivity(intent);
        });
    }
}