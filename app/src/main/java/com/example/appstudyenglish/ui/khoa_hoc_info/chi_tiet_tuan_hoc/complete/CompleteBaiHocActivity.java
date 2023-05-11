package com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc.complete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityCompleteBaiHocBinding;
import com.example.appstudyenglish.ui.main.MainActivity;

public class CompleteBaiHocActivity extends AppCompatActivity {
    private ActivityCompleteBaiHocBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_complete_bai_hoc);
        binding.btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompleteBaiHocActivity.this, MainActivity.class));
            }
        });
    }
}