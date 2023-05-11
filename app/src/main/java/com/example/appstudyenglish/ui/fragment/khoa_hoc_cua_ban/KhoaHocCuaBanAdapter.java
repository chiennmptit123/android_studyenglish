package com.example.appstudyenglish.ui.fragment.khoa_hoc_cua_ban;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudyenglish.databinding.ItemKhoaHocCuaBanBinding;
import com.example.appstudyenglish.model.KhoaHoc;

public class KhoaHocCuaBanAdapter extends RecyclerView.Adapter<KhoaHocCuaBanAdapter.NotificationViewHolder> {

    private IKhoaHocCuaBan iKhoaHocCuaBan;

    public KhoaHocCuaBanAdapter(IKhoaHocCuaBan iKhoaHocCuaBan) {
        this.iKhoaHocCuaBan = iKhoaHocCuaBan;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKhoaHocCuaBanBinding binding = ItemKhoaHocCuaBanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, @SuppressLint("RecyclerView") int position) {
        KhoaHoc khoaHoc = iKhoaHocCuaBan.getKhoaHoc(position);
        holder.binding.imageAvtKhoaHoc.setImageResource(khoaHoc.getAvatar());
        holder.binding.txtTenKhoaHoc.setText(khoaHoc.getName());
        holder.binding.btnVaoHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iKhoaHocCuaBan.onClickVaoHoc(position);
            }
        });
        holder.binding.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iKhoaHocCuaBan.onClickChat(position);
            }
        });
        holder.binding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iKhoaHocCuaBan.onClickDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iKhoaHocCuaBan.getCount();
    }

    public interface IKhoaHocCuaBan{
        int getCount();
        KhoaHoc getKhoaHoc(int position);
        void onClickVaoHoc(int position);
        void onClickChat(int position);
        void onClickDelete(int position);
    }
    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        ItemKhoaHocCuaBanBinding binding;
        public NotificationViewHolder(@NonNull ItemKhoaHocCuaBanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
