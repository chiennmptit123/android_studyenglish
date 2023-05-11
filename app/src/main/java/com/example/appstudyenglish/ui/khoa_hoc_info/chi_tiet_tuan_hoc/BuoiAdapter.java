package com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ItemBuoiHocBinding;
import com.example.appstudyenglish.model.Buoi;

public class BuoiAdapter extends RecyclerView.Adapter<BuoiAdapter.NotificationViewHolder> {

    private IBuoi iBuoi;

    public BuoiAdapter(IBuoi iBuoi) {
        this.iBuoi = iBuoi;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBuoiHocBinding binding = ItemBuoiHocBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Buoi buoi = iBuoi.getBuoi(position);
        holder.binding.txtBuoi.setText(buoi.getTenBuoi());
        holder.binding.txtPhan1.setText(buoi.getBaiHocTrongNgayArrayList().get(0).getBaiHoc());
        holder.binding.txtPhan2.setText(buoi.getBaiHocTrongNgayArrayList().get(1).getBaiHoc());
        holder.binding.txtPhan3.setText(buoi.getBaiHocTrongNgayArrayList().get(2).getBaiHoc());
        holder.binding.txtPhanTram1.setText(buoi.getBaiHocTrongNgayArrayList().get(0).getTienDo()+"/20");
        holder.binding.txtPhanTram2.setText(buoi.getBaiHocTrongNgayArrayList().get(1).getTienDo()+"/20");
        holder.binding.txtPhanTram3.setText(buoi.getBaiHocTrongNgayArrayList().get(2).getTienDo()+"/20");
        if(buoi.getCheckLoai() == 1){
            holder.binding.imgCapacity.setImageResource(R.drawable.ic_headphones);
        }else if(buoi.getCheckLoai() == 2){
            holder.binding.imgCapacity.setImageResource(R.drawable.ic_book);
        } else if(buoi.getCheckLoai() == 3){
            holder.binding.imgCapacity.setImageResource(R.drawable.ic_pen);
        } else if(buoi.getCheckLoai() == 4){
            holder.binding.imgCapacity.setImageResource(R.drawable.ic_speak);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buoi.getCheckLoai() == 1){
                    iBuoi.onClickBuoi(position);
                }else if(buoi.getCheckLoai() == 2){
                    iBuoi.onClickReading(position);
                } else if(buoi.getCheckLoai() == 3){
                    iBuoi.onClickWriting(position);
                } else if(buoi.getCheckLoai() == 4){
                    iBuoi.onClickSpeaking(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return iBuoi.getCount();
    }

    public interface IBuoi{
        int getCount();
        Buoi getBuoi(int position);
        void onClickBuoi(int position);
        Context getContext();
        void onClickReading(int position);
        void onClickWriting(int position);
        void onClickSpeaking(int position);
    }
    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        ItemBuoiHocBinding binding;
        public NotificationViewHolder(@NonNull ItemBuoiHocBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
