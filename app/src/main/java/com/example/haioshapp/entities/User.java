package com.example.haioshapp.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity
public class User {
    @NonNull
    public String id;

    public User(@NonNull String id) {
        this.id = id;
    }
}
