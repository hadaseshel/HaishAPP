package com.example.haioshapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @NonNull
    @PrimaryKey(autoGenerate=false)
    private String id;
    private String contactKey;
    private String content;
    private String created;
    @Ignore
    private String type;
    private boolean sent;

    public Message(String id,String contactKey, String content, String created, boolean sent) {
        this.id = id;
        this.contactKey = contactKey;
        this.content = content;
        this.created = created;
        this.sent = sent;
        this.type = null;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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
                ", datecreate='" + created + '\'' +
                ", sent=" + sent +
                '}';
    }
}
