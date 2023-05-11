package com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc.writting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityBaiHocWritingBinding;
import com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc.complete.CompleteBaiHocActivity;

import java.util.Locale;

public class BaiHocWritingActivity extends AppCompatActivity {
    private ActivityBaiHocWritingBinding binding;
    private long mTimeInMillis = 1200000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_bai_hoc_writing);
        onSetTimeDown();
        onClick();
    }
    private void onSetTimeDown() {
        new CountDownTimer(mTimeInMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                mTimeInMillis = millisUntilFinished;
                int minutes = (int) (mTimeInMillis/1000)/60;
                int seconds = (int) (mTimeInMillis/1000)%60;
                binding.txtTimeRead.setText(String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds));
            }
            public void onFinish() {

            }
        }.start();
    }
    private void onClick() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtWrite = binding.edtWrite.getText().toString().trim();
                if (txtWrite.isEmpty()){
                    Toast.makeText(BaiHocWritingActivity.this,"Bạn chưa nhập dữ liệu bài viết !",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(BaiHocWritingActivity.this, CompleteBaiHocActivity.class));
                }
            }
        });
    }
}