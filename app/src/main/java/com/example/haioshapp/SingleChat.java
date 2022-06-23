package com.example.haioshapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haioshapp.adapters.MessageListAdapter;
import com.example.haioshapp.api.OtherServerApi;
import com.example.haioshapp.api.UserAPI;
import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.entities.Message;
import com.example.haioshapp.entities.Transfer;
import com.example.haioshapp.rooms.AppDB;
import com.example.haioshapp.rooms.ContactsDao;
import com.example.haioshapp.rooms.MessagesDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleChat extends AppCompatActivity {
    List<Message> messages; // the message list
    List<Message> messages_of_server; // the message list of server
    private Context context = this;
    private String userId;
    private String contactId;
    private String contactServer;
    private String server;
    private UserAPI userAPI;
    private AppDB db; // the DB of the app
    private MessagesDao messagesDao; // by this object we will add contact
    private ContactsDao contactsDao;
    MessageListAdapter adapter; // adapter for the spacial view of each contact
    private RecyclerView recyclerView; // the recycle view of the contacts list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);

        db = AppDB.getDB(this);
        messagesDao = db.messagesDao();
        contactsDao = db.contactsDao();
        messages = new ArrayList<>(); // create new list
        recyclerView = findViewById(R.id.singlechat_recyclerView); //get the recycle view
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //bind the recycle view a view
        contactId = getIntent().getExtras().getString("contact_id");
        userId = getIntent().getExtras().getString("user_id");
        server = getIntent().getExtras().getString("user_server");
        String contactNickName = getIntent().getExtras().getString("contact_name");
        contactServer =  getIntent().getExtras().getString("contact_server");

        TextView tvOfTitel = findViewById(R.id.single_chat_title);
        tvOfTitel.setText(contactNickName);

        // get messages
        userAPI = new UserAPI(server);
        Call<List<Message>> call = userAPI.webServiceAPI.getMessages(contactId,userId);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                messages_of_server = response.body();
                this.erazeEndUpdadeMessageRoom();
            }

            private void erazeEndUpdadeMessageRoom() {
                messagesDao.deleteAll();
                String contactIdForMessage = userId+contactId;
                for(Message message: messages_of_server){
                    Message message_for_dao = new Message(message.getId(),contactIdForMessage,
                            message.getContent(),message.getCreated(),message.isSent());
                    messagesDao.insert(message_for_dao);
                }
                // get the contacts list
                messages = messagesDao.getMessagesOfContact(contactIdForMessage);
                // create adapter
                adapter = new MessageListAdapter(context);
                // set the list on the adapter
                adapter.setMessages(messages);
                // set the adapter on the recycle view
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });

        // send the message
        ImageButton btn_send = findViewById(R.id.single_chat_send_btn);
        btn_send.setOnClickListener(v->{
            EditText ed_message = findViewById(R.id.single_chat_ed_send);
            String message_str = ed_message.getText().toString();
            Message last_message = messages.get(messages.size()-1);
            String id_last_message = last_message.getId();
            Integer id_last_message_int =  Integer.parseInt(id_last_message);
            Integer id_new_last_message = id_last_message_int+1;
            String id_new_last_message_str = String.valueOf(id_new_last_message);
            String time_date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());;
            Message message = new Message(id_new_last_message_str,userId+contactId, message_str, time_date, true);
            Message message_to_contact = new Message(id_new_last_message_str,userId+contactId, message_str, time_date, false);
            // send tp the contact server
            OtherServerApi otherServerApi = new OtherServerApi(contactServer);
            //Call<Void> new_call = otherServerApi.webServiceAPI.createMessage(userId,contactId,message_to_contact);
            Call<Void> new_call = otherServerApi.webServiceAPI.createMessageTransfer(new Transfer(userId,contactId,message_str));
            new_call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    userAPI.createdMessage(contactId,userId,message);
                    Contact current_contat = contactsDao.get(userId+contactId);
                    current_contat.setLast(message_str);
                    current_contat.setLastdate(time_date);
                    contactsDao.update(current_contat);
                    ed_message.setText("");
                    messagesDao.insert(message);
                    onResume();
                }

                @SuppressLint("ResourceAsColor")
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                     ed_message.setText("There is a failure on the contact's server, messages can not be sent");
                     ed_message.setTextColor(R.color.deep_orange_variant);
                }
            });
        });
    }
    // refresh the contact list by the room
    @Override
    protected void onResume() {
        super.onResume();
        // get the contacts list
        messages = messagesDao.getMessagesOfContact(userId+contactId);
        // create adapter
        adapter = new MessageListAdapter(this);
        // set the list on the adapter
        adapter.setMessages(messages);
        // set the adapter on the recycle view
        recyclerView.setAdapter(adapter);
    }
}