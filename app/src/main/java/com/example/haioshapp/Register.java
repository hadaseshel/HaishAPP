package com.example.haioshapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.haioshapp.api.UserAPI;
import com.example.haioshapp.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private final int GALLERY_REQ_CODE = 1000;
    private ImageView imageView;
    private List<User> users;
    private String server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        server = getIntent().getExtras().getString("user_server");

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
        Button btn_login = findViewById(R.id.register_button);
        btn_login.setOnClickListener(v->{
            // need to add validazia
            Intent intent = new Intent(this,Chats.class);
            EditText user_id = findViewById(R.id.register_userid);
            String user_id_string = user_id.getText().toString();
            EditText user_pass = findViewById(R.id.register_pass);
            String user_pass_string = user_pass.getText().toString();
            EditText user_pass_conf = findViewById(R.id.register_pass_confirm);
            String user_pass_conf_string = user_pass_conf.getText().toString();
            EditText user_name = findViewById(R.id.register_name);
            String user_name_string = user_name.getText().toString();

            intent.putExtra("user_id",user_id_string);
            intent.putExtra("user_server",server);
            // check password identical to confirm
            if(!user_pass_string.equals(user_pass_conf_string)){
                TextView tv = (TextView)findViewById(R.id.register_error);
                tv.setText("The password and password confirmation are not the same");
                return;
            }
             //check the strong of the password
            String characters = ".*[a-z].*";
            String numbers = ".*[0-9].*";
            if(!(user_pass_string.matches(characters)&&user_pass_string.matches(numbers))){
                TextView tv = (TextView)findViewById(R.id.register_error);
                tv.setText("The password must contain letters and numbers");
                return;
            }
            for(User user:users){
                String current_user_id = user.getId();
                // pass the chat screen only if the user is exist in server
                if(user_id_string.equals(current_user_id)){
                    TextView tv = (TextView)findViewById(R.id.register_error);
                    tv.setText("The username you entered is saved in the system, please select another username");
                    return;
                }
            }
            // need to regiset the user
            TextView tv = (TextView)findViewById(R.id.register_error);
            tv.setText("");
            User user = new User(user_id_string,user_name_string,user_pass_string,"http://localhost:5034/");
            userAPI.createUser(user);
            startActivity(intent);
        });

        // pass to the register page
        TextView pass_register = findViewById(R.id.regist_pass_login);
        pass_register.setOnClickListener(v->{
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);
        });

        // uplod from gallery
        Button btn_image = findViewById(R.id.button_img);
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK){
            if(requestCode == GALLERY_REQ_CODE){
                imageView.setImageURI(data.getData());
            }
        }
    }
}
