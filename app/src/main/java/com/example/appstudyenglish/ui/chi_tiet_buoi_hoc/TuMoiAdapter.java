package com.example.appstudyenglish.ui.chi_tiet_buoi_hoc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ItemBaiHocBinding;
import com.example.appstudyenglish.databinding.ItemTuMoiBinding;
import com.example.appstudyenglish.model.BaiHocTrongNgay;
import com.example.appstudyenglish.model.TuMoi;

public class TuMoiAdapter extends RecyclerView.Adapter<TuMoiAdapter.NotificationViewHolder> {
    private ITuMoi iTuMoi;

    public TuMoiAdapter(ITuMoi iTuMoi) {
        this.iTuMoi = iTuMoi;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTuMoiBinding binding = ItemTuMoiBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TuMoi tuMoi = iTuMoi.getTuMoi(position);
        holder.binding.txtTenBaiHoc.setText(tuMoi.getContent());
        holder.binding.icVolum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iTuMoi.onClickVolum(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iTuMoi.getCount();
    }

    public interface ITuMoi{
        int getCount();
        TuMoi getTuMoi(int position);
        Context getContext();
        void onClickVolum(int position);
    }
    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        ItemTuMoiBinding binding;
        public NotificationViewHolder(@NonNull ItemTuMoiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
