package com.example.appstudyenglish.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.sqlite.SQLiteHelper;
import com.example.appstudyenglish.ui.log_in.dang_nhap.LogInActivity;

public class SplashActivity extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        creatDatabase();
    }

    private void creatDatabase() {
        sqLiteHelper = new SQLiteHelper(this,"Data.sqlite",null,5);
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS User(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserId VARCHAR(100)," +
                "Email VARCHAR(100)," +
                "UserName NVARCHAR(100)," +
                "Permission INTEGER)");
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS KhoaHocDaMua(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserId VARCHAR(100)," +
                "MaKhoaHoc INTEGER," +
                "TenKhoaHoc VARCHAR(100)," +
                "AvatarKhoaHoc INTEGER," +
                "TenGiangVien NVARCHAR(100)," +
                "ThoiGian NVARCHAR(100))");
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS TinNhan(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserId VARCHAR(100)," +
                "MaKhoaHoc INTEGER," +
                "NoiDung NVARCHAR(200)," +
                "ThoiGian NVARCHAR(100))");
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS BaiTest(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserId VARCHAR(100)," +
                "DiemNghe INTEGER," +
                "DiemDoc INTEGER)");
    }

    private void nextActivity(){
        Intent intent = new Intent(SplashActivity.this, LogInActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        },1000);
    }
}