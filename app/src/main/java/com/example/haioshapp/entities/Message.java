package com.example.haioshapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @NonNull
    @PrimaryKey(autoGenerate=false)
    private String id;
    private String content;
    private String datecreate;
    private boolean sent;

    public Message(String id, String content, String datecreate, boolean sent) {
        this.id = id;
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
                ", content='" + content + '\'' +
                ", datecreate='" + datecreate + '\'' +
                ", sent=" + sent +
                '}';
    }
}
