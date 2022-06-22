package com.example.haioshapp.rooms;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.entities.Message;

@Database(entities = {Contact.class, Message.class},version = 1)
public abstract class AppDB extends RoomDatabase{
    public abstract ContactsDao contactsDao();
    public abstract MessagesDao messagesDao();

    private static volatile AppDB INSTANCE;

    public static AppDB getDB(final Context context) {
        if(INSTANCE==null){
            synchronized (AppDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, AppDB.class,"AppDB5")
                            .allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
