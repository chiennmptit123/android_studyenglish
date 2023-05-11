package com.example.appstudyenglish.ui.chi_tiet_khoa_hoc;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ItemKhoaHocCuaBanBinding;
import com.example.appstudyenglish.databinding.ItemTuanBinding;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.model.Tuan;

public class TuanAdapter extends RecyclerView.Adapter<TuanAdapter.NotificationViewHolder> {

    private ITuan iTuan;

    public TuanAdapter(ITuan iTuan) {
        this.iTuan = iTuan;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTuanBinding binding = ItemTuanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tuan tuan = iTuan.getTuan(position);
        holder.binding.txtTuan.setText(tuan.getTitle());
        if(tuan.getStatus() == 1){
            holder.binding.icLock.setImageResource(R.drawable.ic_done_gray);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iTuan.onClickTuan(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iTuan.getCount();
    }

    public interface ITuan{
        int getCount();
        Tuan getTuan(int position);
        void onClickTuan(int position);
    }
    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        ItemTuanBinding binding;
        public NotificationViewHolder(@NonNull ItemTuanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
