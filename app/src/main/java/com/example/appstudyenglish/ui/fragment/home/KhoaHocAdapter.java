package com.example.appstudyenglish.ui.fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudyenglish.databinding.ItemKhoaHocBinding;
import com.example.appstudyenglish.model.KhoaHoc;

public class KhoaHocAdapter extends RecyclerView.Adapter<KhoaHocAdapter.KhoaHocViewHolder> {

    private IKhoaHoc iKhoaHoc;
    private int check;

    public KhoaHocAdapter(IKhoaHoc iKhoaHoc, int check) {
        this.iKhoaHoc = iKhoaHoc;
        this.check = check;
    }

    @NonNull
    @Override
    public KhoaHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKhoaHocBinding binding = ItemKhoaHocBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new KhoaHocViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoaHocViewHolder holder, int position) {
        KhoaHoc khoaHoc = iKhoaHoc.getKhoaHoc(position,check);
        holder.binding.imgAvatar.setImageResource(khoaHoc.getAvatar());
        holder.binding.txtNameKH.setText(khoaHoc.getName());
        holder.binding.txtDateKH.setText(khoaHoc.getDate());
        holder.binding.txtViewer.setText(String.valueOf(khoaHoc.getViewer()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iKhoaHoc.onCLickKhoaHoc(position,check);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iKhoaHoc.getCount(check);
    }

    public interface IKhoaHoc{
        int getCount(int check);
        KhoaHoc getKhoaHoc(int position, int check);
        void onCLickKhoaHoc(int position, int check);
    }
    public class KhoaHocViewHolder extends RecyclerView.ViewHolder{
        ItemKhoaHocBinding binding;
        public KhoaHocViewHolder(@NonNull ItemKhoaHocBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
