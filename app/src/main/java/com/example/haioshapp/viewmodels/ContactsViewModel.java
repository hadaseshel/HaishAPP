package com.example.haioshapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.haioshapp.entities.Contact;

import java.util.List;

public class ContactsViewModel extends ViewModel {
    //private ContacsRepositry mReposity;
    private LiveData<List<Contact>> contacts;

    public ContactsViewModel() {
        //this.mReposity = new ContacsRepositry();
        //this.contacts = mReposity.getAll();
    }
    public LiveData<List<Contact>> get(){return contacts;}

   // public void add(Contacts contacts){mReposity.add(contacts);}

  //  public void delet(Contacts contacts){mReposity.delete(contacts);}

    //public void reload(){mReposity.reload();}
}
