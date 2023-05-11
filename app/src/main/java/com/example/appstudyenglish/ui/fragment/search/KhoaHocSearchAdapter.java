package com.example.appstudyenglish.ui.fragment.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudyenglish.databinding.ItemKhoaHocCuaBanBinding;
import com.example.appstudyenglish.databinding.ItemKhoaHocSearchBinding;
import com.example.appstudyenglish.model.KhoaHoc;

public class KhoaHocSearchAdapter extends RecyclerView.Adapter<KhoaHocSearchAdapter.NotificationViewHolder> {

    private IKhoaHocSearch iKhoaHocSearch;

    public KhoaHocSearchAdapter(IKhoaHocSearch iKhoaHocSearch) {
        this.iKhoaHocSearch = iKhoaHocSearch;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKhoaHocSearchBinding binding = ItemKhoaHocSearchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        KhoaHoc khoaHoc = iKhoaHocSearch.getKhoaHoc(position);
        holder.binding.imageAvtKhoaHoc.setImageResource(khoaHoc.getAvatar());
        holder.binding.txtTenKhoaHoc.setText(khoaHoc.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iKhoaHocSearch.onClickKhoaHoc(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iKhoaHocSearch.getCount();
    }

    public interface IKhoaHocSearch{
        int getCount();
        KhoaHoc getKhoaHoc(int position);
        void onClickKhoaHoc(int position);
    }
    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        ItemKhoaHocSearchBinding binding;
        public NotificationViewHolder(@NonNull ItemKhoaHocSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
