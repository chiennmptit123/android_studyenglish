package com.example.appstudyenglish.ui.test.complete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityCompleteBinding;

public class CompleteActivity extends AppCompatActivity {
    private ActivityCompleteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_complete);
        binding.btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CompleteActivity.this,AdviceActivity.class));
            }
        });
    }
}