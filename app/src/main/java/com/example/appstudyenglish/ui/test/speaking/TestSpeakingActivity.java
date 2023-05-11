package com.example.appstudyenglish.ui.test.speaking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityTestSpeakingBinding;
import com.example.appstudyenglish.ui.test.listening.TestListeningActivity;
import com.example.appstudyenglish.ui.test.writting.WrittingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class TestSpeakingActivity extends AppCompatActivity {

    private ActivityTestSpeakingBinding binding;
    private long mTimeInMillis = 300000;
    private long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    private int Seconds, Minutes;
    private Handler handler;
    private boolean checkGhiAm = false;
    private static int MICROPHONE_PERMISSION_CODE = 200;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private boolean checkRecorded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_speaking);
        onSetTimeDown();
        handler = new Handler();
        mediaPlayer = new MediaPlayer();
        binding.btnGhiAm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecord();
            }
        });
        if (isMicrophonePresent()) {
            getMicrophonePermission();
        }
        binding.btnNgheLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playRecord();
            }
        });
        binding.imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkRecorded){
                    startActivity(new Intent(TestSpeakingActivity.this, WrittingActivity.class));
                    mediaPlayer.stop();
                }else {
                    Toast.makeText(TestSpeakingActivity.this, "Bạn chưa thực hiện bài nói !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
            }
        });
    }

    private void onRecord() {
        if (checkGhiAm == false) {
            setTimeUp();
            startRecording();
            binding.btnGhiAm.setBackgroundResource(R.drawable.custom_button_login_red);
            checkGhiAm = true;
            checkRecorded = true;
        } else if (checkGhiAm == true) {
            checkGhiAm = false;
            binding.btnGhiAm.setBackgroundResource(R.drawable.custom_button_login);
            setTimeStop();
            stopRecording();
            binding.linerGhiAm.setVisibility(View.GONE);
            binding.btnNgheLai.setVisibility(View.VISIBLE);
        }
    }

    private void startRecording() {
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(this, "Recording is started", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
        Toast.makeText(this, "Recording is stopped", Toast.LENGTH_SHORT).show();
    }

    private void playRecord() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getRecordingFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Play recorded", Toast.LENGTH_SHORT).show();
    }

    private void setTimeUp() {
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }

    private void setTimeStop() {
        TimeBuff += MillisecondTime;
        handler.removeCallbacks(runnable);
    }

    private void onSetTimeDown() {
        new CountDownTimer(mTimeInMillis, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimeInMillis = millisUntilFinished;
                int minutes = (int) (mTimeInMillis / 1000) / 60;
                int seconds = (int) (mTimeInMillis / 1000) % 60;
                binding.txtTimeRead.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
            }

            public void onFinish() {
                mediaPlayer.stop();
            }

        }.start();
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            binding.txtThoiGianGhiAm.setText(String.format(Locale.getDefault(), "%02d:%02d", Minutes, Seconds));

            handler.postDelayed(this, 0);
        }
    };

    private boolean isMicrophonePresent() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            return true;
        } else {
            return false;
        }
    }

    private void getMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }

    private String getRecordingFilePath() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = firebaseUser.getUid();
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "testRecordingFile" + userID + ".mp3");
        return file.getPath();
    }
}