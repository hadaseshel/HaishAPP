package com.example.haioshapp.rooms;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.haioshapp.entities.Contact;

import java.util.List;

@Dao
public interface ContactsDao {
    @Insert
    void insert(Contact contact);
    @Update
    void update(Contact contact);
    @Delete
    void delete(Contact contact);

    @Query("SELECT * FROM contact")
    List<Contact> index_contacts();

    @Query("SELECT * FROM contact WHERE id= :id")
    Contact get_contact(String id);
}
