package com.example.haioshapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @NonNull
    public String id;
    public String password;
    public String nickname;
    public String image;
    public String server;
    public List<Contact> chat;

    public User(@NonNull String id) {
        this.id = id;
    }

    public User(@NonNull String id, String image, String nickname, String password,String server,List<Contact> chat) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.image = image;
        this.server = server;
        this.chat = chat;
    }
    public User(@NonNull String id, String nickname, String password, String server) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.image = "";
        this.server = server;
        this.chat = new ArrayList<Contact>();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
    public List<Contact> getChat() {
        return chat;
    }

    public void setChat(List<Contact> chat) {
        this.chat = chat;
    }
}
