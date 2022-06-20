package com.example.haioshapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haioshapp.R;
import com.example.haioshapp.SingleChat;
import com.example.haioshapp.entities.Contact;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>{
    class ContactViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvNameContact;
        private final TextView tvLastMessage;
        private final TextView tvDateLastMessage;
        private ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameContact = itemView.findViewById(R.id.tvNameContact);
            tvLastMessage = itemView.findViewById(R.id.tvLastMessage);
            tvDateLastMessage = itemView.findViewById(R.id.tvDateLastMessage);
        }
    }
    private final LayoutInflater inflater;
    private List<Contact> contacts;

    public ContactListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.contact_item,parent,false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if(contacts!=null){
            final Contact current = contacts.get(position);
            holder.tvDateLastMessage.setText(current.getLastdate());
            holder.tvNameContact.setText(current.getNickname());
            holder.tvLastMessage.setText(current.getLast());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SingleChat.class);
                    intent.putExtra("user_id",current.getUserId());
                    intent.putExtra("contact_id",current.getContactId());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
       if(contacts!=null){
           return contacts.size();
       }else{
           return 0;
       }
    }
    public void setContacts(List<Contact> list){
        contacts = list;
        notifyDataSetChanged();
    }

    public List<Contact> getContacts(){
        return contacts;
    }
}
