package com.example.haioshapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.haioshapp.entities.Message;

import java.util.List;

public class MessageViewModle extends ViewModel {
    //private MessageRepositry mReposity;
    private LiveData<List<Message>> messages;

    public MessageViewModle() {
       // this.mReposity = new MessageRepositry();
       // this.messages = mReposity.getAll();
    }
    public LiveData<List<Message>> get(){return messages;}

    //public void add(Message message){mReposity.add(message);}

    //public void delet(Message message){mReposity.delete(message);}

   // public void reload(){mReposity.reload();}
}
