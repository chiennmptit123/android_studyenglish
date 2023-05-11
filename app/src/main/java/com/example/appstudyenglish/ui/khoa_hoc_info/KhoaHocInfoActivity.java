package com.example.appstudyenglish.ui.khoa_hoc_info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityKhoaHocInfoBinding;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.sqlite.SQLiteHelper;
import com.example.appstudyenglish.ui.chi_tiet_khoa_hoc.ChiTietKhoaHocActivity;
import com.example.appstudyenglish.ui.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class KhoaHocInfoActivity extends AppCompatActivity {

    private ActivityKhoaHocInfoBinding binding;
    private KhoaHoc khoaHoc;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_khoa_hoc_info);
        sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        getAndSetData();
        onClickListener();
    }

    private void getAndSetData() {
        khoaHoc = (KhoaHoc) getIntent().getSerializableExtra("khoahoc");
        binding.anhBiaKhoaHoc.setImageResource(khoaHoc.getAvatar());
        binding.txtTenKhoaHoc.setText(khoaHoc.getName());
        binding.txtTenGiaoVien.setText(khoaHoc.getTeacher());
        binding.txtThoiGian.setText(khoaHoc.getDate());
    }

    private void onClickListener() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnMuaKhoaHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String userID = firebaseUser.getUid();
                Cursor data = sqLiteHelper.GetData("SELECT * FROM KhoaHocDaMua WHERE UserId = '"+userID+"' AND MaKhoaHoc = '"+khoaHoc.getMaKhoaHoc()+"'");
                if(data.getCount() == 0){
                    Intent intent = new Intent(KhoaHocInfoActivity.this, MainActivity.class);
                    intent.putExtra("check",2);
                    Toast.makeText(KhoaHocInfoActivity.this, "Đã mua khóa học thành công", Toast.LENGTH_SHORT).show();
                    sqLiteHelper.QueryData("INSERT INTO KhoaHocDaMua VALUES(null,'" + userID + "','" + khoaHoc.getMaKhoaHoc() + "','"+khoaHoc.getName()+"','" + khoaHoc.getAvatar() + "','" + khoaHoc.getTeacher() + "','" + khoaHoc.getDate() + "')");
                    startActivity(intent);
                }else {
                    Toast.makeText(KhoaHocInfoActivity.this, "Bạn đã mua khóa học này !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnBookmart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhoaHocInfoActivity.this, MainActivity.class);
                intent.putExtra("check",2);
                startActivity(intent);
            }
        });
    }
}