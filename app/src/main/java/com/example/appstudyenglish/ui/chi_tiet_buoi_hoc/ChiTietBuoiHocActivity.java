package com.example.appstudyenglish.ui.chi_tiet_buoi_hoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityChiTietBuoiHocBinding;
import com.example.appstudyenglish.model.BaiHocTrongNgay;
import com.example.appstudyenglish.model.Buoi;
import com.example.appstudyenglish.ui.test.listening.TestListeningActivity;

public class ChiTietBuoiHocActivity extends AppCompatActivity implements BaiHocAdapter.IBaiHoc {
    private ActivityChiTietBuoiHocBinding binding;
    private Buoi buoi;
    private BaiHocAdapter baiHocAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chi_tiet_buoi_hoc);
        buoi = (Buoi) getIntent().getSerializableExtra("buoi");
        initRecyclerview();
        binding.txtBaiHoc.setText(buoi.getTenBuoi());
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initRecyclerview() {
        baiHocAdapter = new BaiHocAdapter(this,this);
        binding.rcvBaiHoc.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvBaiHoc.setAdapter(baiHocAdapter);
    }

    @Override
    public int getCount() {
        return buoi.getBaiHocTrongNgayArrayList().size();
    }

    @Override
    public BaiHocTrongNgay getBaiHoc(int position) {
        return buoi.getBaiHocTrongNgayArrayList().get(position);
    }
    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}