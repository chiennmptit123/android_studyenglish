package com.example.appstudyenglish.ui.quan_tri_vien.main_quan_tri_vien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityMainQuanTriBinding;

public class MainQuanTriActivity extends AppCompatActivity {

    private ActivityMainQuanTriBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_quan_tri);
    }
}