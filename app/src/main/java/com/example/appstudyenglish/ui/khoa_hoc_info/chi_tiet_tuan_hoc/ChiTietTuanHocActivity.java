package com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityChiTietTuanHocBinding;
import com.example.appstudyenglish.model.Buoi;
import com.example.appstudyenglish.model.Tuan;
import com.example.appstudyenglish.ui.chi_tiet_buoi_hoc.ChiTietBuoiHocActivity;
import com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc.reading.BaiHocReadingActivity;
import com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc.speaking.BaiHocSpeakingActivity;
import com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc.writting.BaiHocWritingActivity;

public class ChiTietTuanHocActivity extends AppCompatActivity implements BuoiAdapter.IBuoi {

    private ActivityChiTietTuanHocBinding binding;
    private Tuan tuan = new Tuan();
    private BuoiAdapter buoiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chi_tiet_tuan_hoc);
        tuan = (Tuan) getIntent().getSerializableExtra("tuan");
        setDataToView();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        buoiAdapter = new BuoiAdapter(this);
        binding.rcvBuoiHoc.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvBuoiHoc.setAdapter(buoiAdapter);
    }

    private void setDataToView() {
        if (tuan != null){
            binding.txtTuan.setText(tuan.getTitle());
        }
        binding.tvPhanTram.setText("69%");
        binding.progressBar.setProgress(69);
        binding.progressBar.setMax(100);
    }

    @Override
    public int getCount() {
        return tuan.getBuoiArrayList().size();
    }

    @Override
    public Buoi getBuoi(int position) {
        return tuan.getBuoiArrayList().get(position);

    }

    @Override
    public void onClickBuoi(int position) {
        if (tuan != null){
            Intent intent = new Intent(ChiTietTuanHocActivity.this, ChiTietBuoiHocActivity.class);
            intent.putExtra("buoi",tuan.getBuoiArrayList().get(position));
            startActivity(intent);
        }
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onClickReading(int position) {
        Intent intent = new Intent(ChiTietTuanHocActivity.this, BaiHocReadingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickWriting(int position) {
        Intent intent = new Intent(ChiTietTuanHocActivity.this, BaiHocWritingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickSpeaking(int position) {
        Intent intent = new Intent(ChiTietTuanHocActivity.this, BaiHocSpeakingActivity.class);
        startActivity(intent);
    }
}