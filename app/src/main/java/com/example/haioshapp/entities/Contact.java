package com.example.haioshapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @NonNull
    @PrimaryKey(autoGenerate=false)
    private String id;
    private String nickname;
    private String server;
    private String last;
    private String lastdate;

    public Contact(){
        this.id = null;
        this.nickname =null;
        this.server = null;
        this.last = null;
        this.lastdate = null;
    }
    @Ignore
    public Contact(String id, String nickname, String server, String last, String lastdate) {
        this.id = id;
        this.nickname = nickname;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
    }
    @Ignore
    public Contact(String id, String nickname, String server) {
        this.id = id;
        this.nickname = nickname;
        this.server = server;
        this.last = null;
        this.lastdate = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String name) {
        this.nickname = name;
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

    @Override
    public String toString() {
        return "Contacts{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", server='" + server + '\'' +
                ", last='" + last + '\'' +
                ", lastdate='" + lastdate + '\'' +
                '}';
    }
}
