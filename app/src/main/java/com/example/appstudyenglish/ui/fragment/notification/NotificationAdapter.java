package com.example.appstudyenglish.ui.fragment.notification;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appstudyenglish.databinding.ItemNotificationBinding;
import com.example.appstudyenglish.model.ThongBao;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private INotification iNotification;

    public NotificationAdapter(INotification iKhoaHoc) {
        this.iNotification = iKhoaHoc;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        ThongBao thongBao = iNotification.getNotification(position);
        holder.binding.txtContent.setText(thongBao.getTieuDe());
        holder.binding.txtName.setText(thongBao.getNguoiGui());
        holder.binding.txtTitle.setText(thongBao.getNoiDung());
        holder.binding.txtDate.setText(thongBao.getTime());
        if(thongBao.getAvatar() == 0){
            holder.binding.cardViewNotifi.setVisibility(ViewGroup.GONE);
        }else {
            holder.binding.imgNotifi.setImageResource(thongBao.getAvatar());
        }
    }

    @Override
    public int getItemCount() {
        return iNotification.getCount();
    }

    public interface INotification{
        int getCount();
        ThongBao getNotification(int position);
    }
    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        ItemNotificationBinding binding;
        public NotificationViewHolder(@NonNull ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
