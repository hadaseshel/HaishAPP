package com.example.haioshapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Contact {
    @NonNull
    @PrimaryKey(autoGenerate=false)
    private String id;
    private String contactId;
    private String userId;
    private String name;
    private String server;
    private String userServer;
    private String last;
    private String lastdate;
    private String image;
    @Ignore
    private List<Message> chat;

    public Contact(){
        this.id = null;
        this.contactId = null;
        this.userId = null;
        this.name =null;
        this.server = null;
        this.last = null;
        this.userServer=null;
        this.lastdate = null;
        this.image = null;
        this.chat = null;
    }
    @Ignore
    public Contact(String id, String name, String server){
        this.id = id;
        this.contactId = null;
        this.userId = null;
        this.name = name;
        this.server = server;
        this.userServer=null;
        this.last = null;
        this.lastdate = null;
        this.image = null;
        this.chat = null;
    }
    @Ignore
    public Contact(String id, String name, String server,String last, String lastdate,String image,List<Message> chat) {
        this.id = id;
        this.contactId = null;
        this.userId = null;
        this.userServer=null;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
        this.image = image;
        this.chat = chat;
    }
    @Ignore
    public Contact(String id,String contactId, String userId, String name, String server) {
        this.id = id;
        this.contactId = contactId;
        this.userServer=null;
        this.userId = userId;
        this.name = name;
        this.server = server;
        this.last = null;
        this.lastdate = null;
    }

    @Ignore
    public Contact(@NonNull String id, String contactId, String userId, String name, String server, String last, String lastdate) {
        this.id = id;
        this.contactId = contactId;
        this.userId = userId;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
        this.userServer=null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", contactId='" + contactId + '\'' +
                ", userId='" + userId + '\'' +
                ", nickname='" + name + '\'' +
                ", server='" + server + '\'' +
                ", last='" + last + '\'' +
                ", lastdate='" + lastdate + '\'' +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Message> getChat() {
        return chat;
    }

    public void setChat(List<Message> chat) {
        this.chat = chat;
    }

    public String getUserServer() {
        return userServer;
    }

    public void setUserServer(String userServer) {
        this.userServer = userServer;
    }
}
