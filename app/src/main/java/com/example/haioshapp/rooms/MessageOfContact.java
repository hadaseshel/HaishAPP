package com.example.haioshapp.rooms;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.haioshapp.entities.Contact;
import com.example.haioshapp.entities.Message;

import java.util.List;

public class MessageOfContact {
    @Embedded
    public Contact contact;
    @Relation(
            parentColumn = "id",
            entityColumn = "contactKey"
    )
    public List<Message> messages;
    public MessageOfContact(){}
    public MessageOfContact(Contact contact,List<Message> messages){
        this.messages = messages;
        this.contact = contact;
    }
}
