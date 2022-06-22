package com.example.haioshapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haioshapp.api.OtherServerApi;
import com.example.haioshapp.api.UserAPI;
import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.rooms.AppDB;
import com.example.haioshapp.rooms.ContactsDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewChat extends AppCompatActivity {
    private AppDB db; // the DB of the app
    private ContactsDao contactsDao; // by this object we will add contact
    private String userId;
    private String userServer;
    private UserAPI userAPI;
    private OtherServerApi otherAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);


        db = AppDB.getDB(this);
        contactsDao = db.contactsDao();
        userId = getIntent().getExtras().getString("user_id");
        userServer = getIntent().getExtras().getString("user_server");
        userAPI = new UserAPI(userServer);

        Button btn_new_chat = findViewById(R.id.new_chat_button);
        btn_new_chat.setOnClickListener(v-> {
            EditText contact_name = findViewById(R.id.new_chat_contact_name);
            EditText contact_nickname = findViewById(R.id.new_chat_nickname);
            EditText contact_server = findViewById(R.id.new_chat_server);
            Contact contact = new Contact(userId+contact_name.getText().toString(),contact_name.getText().toString()
                    ,userId,contact_nickname.getText().toString(),
                    contact_server.getText().toString());
            Contact contact_for_server = new Contact(contact_name.getText().toString(),contact_nickname.getText().toString(),
                    contact_server.getText().toString());

            // the text in case of error
            TextView tv = findViewById(R.id.new_chat_error);
            // if try to add his self
            if(contact_name.getText().toString().equals(userId) &&
                    contact_server.getText().toString().equals(userServer)){
                tv.setText("You can not add yourself as a contact");
                return;
            }

            List<Contact> contacts = contactsDao.getContactOfUser(userId);
            for(Contact contact_form_dao:contacts){
                if(contact_form_dao.getContactId().equals(contact_name.getText().toString())){
                    tv.setText("The contact already exists");
                    return;
                }
            }

            // post contact server
            Contact me_as_contacts = new Contact(userId, userId, userServer);
            otherAPI = new OtherServerApi(contact_server.getText().toString());
            Call<Void> call = otherAPI.webServiceAPI.createContact(contact_for_server.getId(),me_as_contacts);
            call.enqueue(new Callback<Void>() {
                // if the incitation part sucsess
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    // post my server
                    userAPI.createContact(userId,contact_for_server);
                    contactsDao.insert(contact); //insert the contact to the room
                    tv.setText("");
                    finish();
                }

                // if cant to the invitation part
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    tv.setText("The contact does not exist on the server you entered");
                }
            });
            });
    }
}