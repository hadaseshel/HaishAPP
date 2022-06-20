package com.example.haioshapp.rooms;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.entities.User;

import java.util.List;

public class ContactsOfUser {
        @Embedded
        public User user;
        @Relation(
                parentColumn = "id",
                entityColumn = "userId"
        )
        public List<Contact> contacts;
        public ContactsOfUser(){}
    public ContactsOfUser(User user,List<Contact> contacts){
            this.contacts = contacts;
            this.user = user;
    }

}
