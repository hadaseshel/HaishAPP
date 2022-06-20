package com.example.haioshapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haioshapp.R;
import com.example.haioshapp.entities.Message;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder>{
    class MessageViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvMessage;
        private final TextView tvDate;
        private MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
    private final LayoutInflater inflater;
    private List<Message> messages;

    public MessageListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MessageListAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if(viewType==1){
            itemView = inflater.inflate(R.layout.message_item_sent,parent,false);
        }else{
            itemView = inflater.inflate(R.layout.message_item_received,parent,false);
        }
        return new MessageListAdapter.MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListAdapter.MessageViewHolder holder, int position) {
        if(messages!=null){
            final Message current = messages.get(position);
            holder.tvMessage.setText(current.getContent());
            holder.tvDate.setText(current.getDatecreate());
        }
    }

    @Override
    public int getItemViewType(int position) {
        final Message current = messages.get(position);
        if(current.isSent()){
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        if(messages!=null){
            return messages.size();
        }else{
            return 0;
        }
    }
    public void setMessages(List<Message> list){
        messages = list;
        notifyDataSetChanged();
    }

    public List<Message> getMessages(){
        return messages;
    }
}

