package com.example.appstudyenglish.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ItemMessageBinding;
import com.example.appstudyenglish.databinding.ItemTuanBinding;
import com.example.appstudyenglish.model.Message;
import com.example.appstudyenglish.model.Tuan;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.NotificationViewHolder> {

    private IMessage iMessage;

    public MessageAdapter(IMessage iMessage) {
        this.iMessage = iMessage;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMessageBinding binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Message message = iMessage.getMessage(position);
        holder.binding.txtMessage.setText(message.getTitle());
        holder.binding.txtTime.setText(message.getTime());
    }

    @Override
    public int getItemCount() {
        return iMessage.getCount();
    }

    public interface IMessage{
        int getCount();
        Message getMessage(int position);
    }
    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        ItemMessageBinding binding;
        public NotificationViewHolder(@NonNull ItemMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
