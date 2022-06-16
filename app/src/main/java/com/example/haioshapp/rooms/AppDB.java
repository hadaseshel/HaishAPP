package com.example.haioshapp.rooms;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.entities.Message;

@Database(entities = {Contact.class, Message.class},version = 1)
public abstract class AppDB extends RoomDatabase{
    public abstract ContactsDao contactsDao();
    public abstract MessagesDao messagesDao();
}
