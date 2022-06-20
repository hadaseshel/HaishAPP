package com.example.haioshapp.rooms;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.entities.Message;

import java.util.List;

@Dao
public interface MessagesDao {
    @Insert
    void insert(Message message);
    @Update
    void update(Message message);
    @Delete
    void delete(Message message);

    @Query("DELETE FROM message")
    void deleteAll();

    @Query("SELECT * FROM message")
    List<Message> index();

    @Query("SELECT * FROM message WHERE id= :id")
    Contact get(String id);

    @Query("SELECT * FROM message WHERE contactKey= :contactKey")
    List<Message> getMessagesOfContact(String contactKey);
}
