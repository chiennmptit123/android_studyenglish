package com.example.appstudyenglish.ui.test.listening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityListeningBinding;

public class ListeningActivity extends AppCompatActivity {
    private ActivityListeningBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_listening);
        binding.imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListeningActivity.this,TestListeningActivity.class));
            }
        });
    }
}