package com.example.appstudyenglish.ui.log_in.dang_nhap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityLogInBinding;
import com.example.appstudyenglish.model.User;
import com.example.appstudyenglish.ui.cus_tom_dialog.CustomProgressDialog;
import com.example.appstudyenglish.ui.log_in.dang_ky.DangKyActivity;
import com.example.appstudyenglish.ui.log_in.lay_lai_mat_khau.CurrentPasswordActivity;
import com.example.appstudyenglish.ui.main.MainActivity;
import com.example.appstudyenglish.ui.quan_tri_vien.main_quan_tri_vien.MainQuanTriActivity;
import com.example.appstudyenglish.ui.splash.SplashActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInActivity extends AppCompatActivity {

    private ActivityLogInBinding binding;
    private String saveInformation = "tk_mk";
    private String email = "";
    private String password = "";
    private String inputEmail;
    private String inputPassword;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private String userID;
    private CustomProgressDialog dialog;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_log_in);
        onClick();
        dialog = new CustomProgressDialog(this);
    }

    private void onClick() {
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInput();
                dialog.show();
            }
        });
        binding.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
        binding.tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, CurrentPasswordActivity.class);
                startActivity(intent);
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
    }

    private void checkInput(){
        inputEmail = binding.edEmail.getText().toString().trim();
        inputPassword = binding.edMatKhau.getText().toString().trim();
        if(inputEmail.isEmpty() || inputPassword.isEmpty()){
            binding.tvThongBao.setVisibility(View.VISIBLE);
            binding.tvThongBao.setText("Nhập thiếu thông tin");
            dialog.dismiss();
        }else {
            onSigin();
        }
    }

    private void onSigin() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        userID = firebaseUser.getUid();
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference(userID);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                user = snapshot.getValue(User.class);
                                logInSuccess();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } else {
                        binding.tvThongBao.setVisibility(View.VISIBLE);
                        binding.tvThongBao.setText("Sai email hoặc password");
                        dialog.dismiss();
                    }
                });
    }

    private void logInSuccess() {
        setLocate();
        //check phân quyền
        if(user.getPermission() == 0){ // gán biến permission để check quyền , nếu = 0 thì là user
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            intent.putExtra("check",1);
            startActivity(intent);
        }else if(user.getPermission() == 1){ //nếu bằng 1 thì là admin
            Intent intent = new Intent(LogInActivity.this, MainQuanTriActivity.class);
            startActivity(intent);
        }
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    private void setLocate() {
        if(binding.checkbox.isChecked()){
            SharedPreferences sharedPreferences = this.getSharedPreferences(saveInformation, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Email",binding.edEmail.getText().toString().trim());
            editor.putString("Password",binding.edMatKhau.getText().toString().trim());
            editor.putBoolean("Save",binding.checkbox.isChecked());
            editor.apply();
        }else {
            SharedPreferences sharedPreferences = this.getSharedPreferences(saveInformation, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("Email");
            editor.remove("Password");
            editor.remove("Save");
            editor.apply();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = this.getSharedPreferences(saveInformation, Context.MODE_PRIVATE);
        String emailResume = sharedPreferences.getString("Email", "");
        String passwordResume = sharedPreferences.getString("Password", "");
        boolean save = sharedPreferences.getBoolean("Save", false);
        if(save){
            binding.edEmail.setText(emailResume);
            binding.edMatKhau.setText(passwordResume);
            binding.checkbox.setChecked(true);
        }
    }
}