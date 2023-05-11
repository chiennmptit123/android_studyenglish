package com.example.appstudyenglish.ui.test.speaking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivitySpeakingBinding;
import com.example.appstudyenglish.ui.test.reading.ReadingActivity;
import com.example.appstudyenglish.ui.test.reading.TestReadingActivity;

public class SpeakingActivity extends AppCompatActivity {

    private ActivitySpeakingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_speaking);
        binding.imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SpeakingActivity.this, TestSpeakingActivity.class));
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}