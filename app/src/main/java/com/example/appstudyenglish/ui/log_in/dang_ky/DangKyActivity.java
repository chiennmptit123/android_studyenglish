package com.example.appstudyenglish.ui.log_in.dang_ky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityDangKyBinding;
import com.example.appstudyenglish.model.User;
import com.example.appstudyenglish.sqlite.SQLiteHelper;
import com.example.appstudyenglish.ui.cus_tom_dialog.CustomProgressDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DangKyActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ActivityDangKyBinding binding;
    private String name;
    private String email;
    private String password;
    private CustomProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_dang_ky);
        onClick();
        dialog = new CustomProgressDialog(this);
    }

    private void onClick() {
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInput();
                dialog.show();
            }
        });
        binding.edEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvThongBao.setVisibility(View.GONE);
            }
        });
        binding.edMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvThongBao.setVisibility(View.GONE);
            }
        });
        binding.edName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvThongBao.setVisibility(View.GONE);
            }
        });
    }

    private void checkInput(){
        email = binding.edEmail.getText().toString().trim();
        password = binding.edMatKhau.getText().toString().trim();
        name = binding.edName.getText().toString().trim();
        if(email.isEmpty() || password.isEmpty() || name.isEmpty()){
            binding.tvThongBao.setVisibility(View.VISIBLE);
            binding.tvThongBao.setText("Nhập thiếu thông tin");
            dialog.dismiss();
        }else if(password.length() < 6){
            binding.tvThongBao.setVisibility(View.VISIBLE);
            binding.tvThongBao.setText("Mật khẩu quá ngắn");
            dialog.dismiss();
        }else {
            onSigup();
        }
    }

    private void onSigup() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference();
                        String IdAcount = task.getResult().getUser().getUid();
                        User user = new User(IdAcount,name,email,0);
                        databaseReference.child(IdAcount).setValue(user);
                        sqLiteHelper.QueryData("INSERT INTO User VALUES(null,'" + IdAcount + "','" + email + "','"+name+"','0')");
                        sqLiteHelper.QueryData("INSERT INTO BaiTest VALUES(null,'" + IdAcount + "','0','0')");
                        nextActivity();
                    } else {
                        showToast("Sai định dạng email hoặc tài khoản đã tồn tại !");
                        dialog.dismiss();
                    }
                });
    }

    private void showToast(String msg) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    private void nextActivity() {
        finish();
        showToast("Đăng ký thành công !");
        dialog.dismiss();
    }
}