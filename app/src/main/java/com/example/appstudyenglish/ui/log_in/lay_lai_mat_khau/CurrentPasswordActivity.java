package com.example.appstudyenglish.ui.log_in.lay_lai_mat_khau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityCurrentPasswordBinding;
import com.example.appstudyenglish.ui.cus_tom_dialog.CustomProgressDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class CurrentPasswordActivity extends AppCompatActivity {

    private ActivityCurrentPasswordBinding binding;
    private FirebaseAuth firebaseAuth;
    private String email;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_current_password);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new CustomProgressDialog(this);
        binding.btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = binding.edEmail.getText().toString().trim();
                progressDialog.show();
                if(email.isEmpty()){
                    Toast.makeText(CurrentPasswordActivity.this, "Vui lòng nhập email !", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else {
                    resetPassword();
                }
            }
        });
    }

    private void resetPassword() {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    resetPasswordSuccess(Gravity.CENTER);
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(CurrentPasswordActivity.this, "Nhập đúng định dạng email !", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void resetPasswordSuccess(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_view_apply_success);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        Button btnTiepTucMuaSam = dialog.findViewById(R.id.btnComfirm);
        TextView txt = dialog.findViewById(R.id.txtThongBao);
        txt.setText("Đã gửi thông báo tới địa chỉ email " + email +". Vui lòng kiểm tra trong email của bạn !");
        btnTiepTucMuaSam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialog.show();
    }
}