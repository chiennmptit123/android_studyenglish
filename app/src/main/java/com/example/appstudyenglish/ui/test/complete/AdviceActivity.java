package com.example.appstudyenglish.ui.test.complete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityAdviceBinding;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.sqlite.SQLiteHelper;
import com.example.appstudyenglish.ui.khoa_hoc_info.KhoaHocInfoActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdviceActivity extends AppCompatActivity {

    private ActivityAdviceBinding binding;
    private SQLiteHelper sqLiteHelper;
    private int point = 0;
    private KhoaHoc khoaA0;
    private KhoaHoc khoaA1;
    private KhoaHoc khoaA2;
    private KhoaHoc khoaB1;
    private KhoaHoc khoaHocPhuHop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_advice);
        sqLiteHelper = new SQLiteHelper(this, "Data.sqlite", null, 5);
        addDataKhoaHoc();
        getPointLastTest();
        binding.btnVaoKhoaHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdviceActivity.this, KhoaHocInfoActivity.class);
                intent.putExtra("khoahoc",khoaHocPhuHop);
                startActivity(intent);
            }
        });
    }

    private void addDataKhoaHoc() {
        khoaA0 = new KhoaHoc(1,R.drawable.khoa_1,"Level A0","Hoàng Minh","2 tuần",2301,"- Nắm đuợc các thì cơ bản trong tiếng anh  \n- Tiếp cận với các đoạn hội thoại ngắn  \n- Làm các đoạn bài đọc đơn giản.");
        khoaA1 = new KhoaHoc(2,R.drawable.khoa_2,"Level A1","Huy Nguyễn","3 tuần",3343,"- Nắm đuợc các thì cơ bản trong tiếng anh   \n- Tiếp cận với các đoạn hội thoại ngắn  \n- Làm các đoạn bài đọc đơn giản.");
        khoaA2 = new KhoaHoc(3,R.drawable.khoa_3,"Level A2","Trần Hậu","8 tuần",7663,"- Nắm đuợc các thì cơ bản trong tiếng anh   \n- Tiếp cận với các đoạn hội thoại ngắn  \n- Làm các đoạn bài đọc đơn giản.");
        khoaB1 = new KhoaHoc(4,R.drawable.khoa_4,"Level B1","Hải Hồ","12 tuần",9663,"- Nắm đuợc các thì cơ bản trong tiếng anh   \n- Tiếp cận với các đoạn hội thoại ngắn  \n- Làm các đoạn bài đọc đơn giản.");
    }

    private void getPointLastTest() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = firebaseUser.getUid();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM BaiTest WHERE UserId='"+userID+"'");
        while(data.moveToNext()){
            int diemNghe = data.getInt(2);
            int diemDoc = data.getInt(3);
            point = diemDoc + diemNghe;
        }
        setData();
    }

    private void setData() {
        binding.txtThongBaoDiem.setText("Điểm đạt được : "+point+"% ("+point+" points)");
        if(point <= 30){
            khoaHocPhuHop = khoaA0;
        }else if(point > 30 && point <= 60){
            khoaHocPhuHop = khoaA1;
        }else if(point > 60 && point <= 80){
            khoaHocPhuHop = khoaA2;
        }else {
            khoaHocPhuHop = khoaB1;
        }
        setKhoaHoc();
    }

    private void setKhoaHoc(){
        binding.txtThongBaoKhoaHocPhuHop.setText("Khóa học phù hợp với bạn : "+khoaHocPhuHop.getName());
        binding.txtAnhKhoaHoc.setImageResource(khoaHocPhuHop.getAvatar());
        binding.txtTenKhoaHoc.setText(khoaHocPhuHop.getName());
        binding.txtThoiGianHoc.setText(khoaHocPhuHop.getDate());
        binding.txtMota.setText(khoaHocPhuHop.getTitle());
    }


}