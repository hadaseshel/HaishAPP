package com.example.haioshapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @NonNull
    @PrimaryKey(autoGenerate=false)
    private String id;
    private String contactKey;
    private String content;
    private String datecreate;
    private boolean sent;


    public Message(String id,String contactKey, String content, String datecreate, boolean sent) {
        this.id = id;
        this.contactKey = contactKey;
        this.content = content;
        this.datecreate = datecreate;
        this.sent = sent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactKey() {
        return contactKey;
    }

    public void setContactKey(String contactKey) {
        this.contactKey = contactKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", contactKey='" + contactKey + '\'' +
                ", content='" + content + '\'' +
                ", datecreate='" + datecreate + '\'' +
                ", sent=" + sent +
                '}';
    }
}
