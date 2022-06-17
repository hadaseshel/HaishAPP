package com.example.haioshapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    private final int GALLERY_REQ_CODE = 1000;
    private ImageView imageView;

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
