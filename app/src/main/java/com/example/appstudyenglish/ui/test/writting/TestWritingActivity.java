package com.example.appstudyenglish.ui.test.writting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityTestWritingBinding;
import com.example.appstudyenglish.ui.test.complete.CompleteActivity;
import com.example.appstudyenglish.ui.test.listening.TestListeningActivity;
import com.example.appstudyenglish.ui.test.reading.ReadingActivity;

import java.util.Locale;

public class TestWritingActivity extends AppCompatActivity {
    private ActivityTestWritingBinding binding;
    private long mTimeInMillis = 1200000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test_writing);
        onClick();
        onsetTime();
    }

    private void onsetTime() {
        new CountDownTimer(mTimeInMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                mTimeInMillis = millisUntilFinished;
                int minutes = (int) (mTimeInMillis/1000)/60;
                int seconds = (int) (mTimeInMillis/1000)%60;
                binding.txtTimeWrite.setText(String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds));
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
        binding.btnGuiDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtWrite = binding.edtWrite.getText().toString().trim();
                if (txtWrite.isEmpty()){
                    Toast.makeText(TestWritingActivity.this,"Bạn chưa nhập dữ liệu bài viết !",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(TestWritingActivity.this, CompleteActivity.class));
                }
            }
        });
    }
}