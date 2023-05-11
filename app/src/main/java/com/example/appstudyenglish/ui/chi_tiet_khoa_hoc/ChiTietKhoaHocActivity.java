package com.example.appstudyenglish.ui.chi_tiet_khoa_hoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityChiTietKhoaHocBinding;
import com.example.appstudyenglish.model.BaiHocTrongNgay;
import com.example.appstudyenglish.model.Buoi;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.model.Tuan;
import com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc.ChiTietTuanHocActivity;
import com.example.appstudyenglish.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ChiTietKhoaHocActivity extends AppCompatActivity implements TuanAdapter.ITuan {

    private ActivityChiTietKhoaHocBinding binding;
    private KhoaHoc khoaHoc;
    private List<Tuan> tuanList;
    private TuanAdapter tuanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chi_tiet_khoa_hoc);
        khoaHoc = (KhoaHoc) getIntent().getSerializableExtra("khoahoc");
        getAndSetData();
        addDataTuan();
        initRecyclerview();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnBookmart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietKhoaHocActivity.this, MainActivity.class);
                intent.putExtra("check",2);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerview() {
        tuanAdapter = new TuanAdapter(this);
        binding.rcvCacTuanHoc.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvCacTuanHoc.setAdapter(tuanAdapter);
    }

    private void getAndSetData() {
        binding.anhBiaKhoaHoc.setImageResource(khoaHoc.getAvatar());
        binding.txtTenKhoaHoc.setText(khoaHoc.getName());
        binding.txtTenGiaoVien.setText(khoaHoc.getTeacher());
        binding.txtThoiGian.setText(khoaHoc.getDate());
    }

    private void addDataTuan() {
        //tuan 1
        ArrayList<BaiHocTrongNgay> buoi1_tuan1 = new ArrayList<>();
        buoi1_tuan1.add(new BaiHocTrongNgay("1.1 Từ Vựng",20));
        buoi1_tuan1.add(new BaiHocTrongNgay("1.2 Bài tập - Ghi nhớ từ vựng",20));
        buoi1_tuan1.add(new BaiHocTrongNgay("1.3 Memory Game",20));

        ArrayList<BaiHocTrongNgay> buoi2_tuan1 = new ArrayList<>();
        buoi2_tuan1.add(new BaiHocTrongNgay("2.1 Từ vựng",20));
        buoi2_tuan1.add(new BaiHocTrongNgay("2.2 Bài tập - Reading part 1",20));
        buoi2_tuan1.add(new BaiHocTrongNgay("2.3 Crossword Game",20));

        ArrayList<BaiHocTrongNgay> buoi3_tuan1 = new ArrayList<>();
        buoi3_tuan1.add(new BaiHocTrongNgay("3.1 Từ vựng",0));
        buoi3_tuan1.add(new BaiHocTrongNgay("3.2 Image Pairing Game",0));
        buoi3_tuan1.add(new BaiHocTrongNgay("3.3 Bài tập - Topic: Viết thư",0));

        ArrayList<BaiHocTrongNgay> buoi4_tuan1 = new ArrayList<>();
        buoi4_tuan1.add(new BaiHocTrongNgay("4.1 Từ vựng",0));
        buoi4_tuan1.add(new BaiHocTrongNgay("4.2 Bài tập - Speaking part 1",0));
        buoi4_tuan1.add(new BaiHocTrongNgay("4.3 Giới thiệu bản thân",0));

        ArrayList<Buoi> buoiList_tuan1 = new ArrayList<>();
        buoiList_tuan1.add(new Buoi("Buổi 1 : Listening",buoi1_tuan1,1));
        buoiList_tuan1.add(new Buoi("Buổi 2 : Reading",buoi2_tuan1,2));
        buoiList_tuan1.add(new Buoi("Buổi 3 : Writing",buoi3_tuan1,3));
        buoiList_tuan1.add(new Buoi("Buổi 4 : Speaking",buoi4_tuan1,4));

        //tuan 2
        ArrayList<BaiHocTrongNgay> buoi1_tuan2 = new ArrayList<>();
        buoi1_tuan2.add(new BaiHocTrongNgay("T2. Từ Vựng",20));
        buoi1_tuan2.add(new BaiHocTrongNgay("T2. Bài tập - Ghi nhớ từ vựng",20));
        buoi1_tuan2.add(new BaiHocTrongNgay("T2. Memory Game",20));

        ArrayList<BaiHocTrongNgay> buoi2_tuan2 = new ArrayList<>();
        buoi2_tuan2.add(new BaiHocTrongNgay("T2. Từ vựng",20));
        buoi2_tuan2.add(new BaiHocTrongNgay("T2. Bài tập - Reading part 1",20));
        buoi2_tuan2.add(new BaiHocTrongNgay("T2. Crossword Game",20));

        ArrayList<BaiHocTrongNgay> buoi3_tuan2 = new ArrayList<>();
        buoi3_tuan2.add(new BaiHocTrongNgay("T2. Từ vựng",0));
        buoi3_tuan2.add(new BaiHocTrongNgay("T2. Image Pairing Game",0));
        buoi3_tuan2.add(new BaiHocTrongNgay("T2. Bài tập - Topic: Viết thư",0));

        ArrayList<BaiHocTrongNgay> buoi4_tuan2 = new ArrayList<>();
        buoi4_tuan2.add(new BaiHocTrongNgay("T2. Từ vựng",0));
        buoi4_tuan2.add(new BaiHocTrongNgay("T2. Bài tập - Speaking part 1",0));
        buoi4_tuan2.add(new BaiHocTrongNgay("T2. Giới thiệu bản thân",0));

        ArrayList<Buoi> buoiList_tuan2 = new ArrayList<>();
        buoiList_tuan2.add(new Buoi("T2. Buổi 1 : Listening",buoi1_tuan2,1));
        buoiList_tuan2.add(new Buoi("T2. Buổi 2 : Reading",buoi2_tuan2,2));
        buoiList_tuan2.add(new Buoi("T2. Buổi 3 : Writing",buoi3_tuan2,3));
        buoiList_tuan2.add(new Buoi("T2. Buổi 4 : Speaking",buoi4_tuan2,4));

        //tuan 3
        ArrayList<BaiHocTrongNgay> buoi1_tuan3 = new ArrayList<>();
        buoi1_tuan3.add(new BaiHocTrongNgay("T3. 1.1 Từ Vựng",20));
        buoi1_tuan3.add(new BaiHocTrongNgay("T3. 1.2 Bài tập - Ghi nhớ từ vựng",20));
        buoi1_tuan3.add(new BaiHocTrongNgay("T3. 1.3 Memory Game",20));

        ArrayList<BaiHocTrongNgay> buoi2_tuan3 = new ArrayList<>();
        buoi2_tuan3.add(new BaiHocTrongNgay("T3. 2.1 Từ vựng",20));
        buoi2_tuan3.add(new BaiHocTrongNgay("T3. 2.2 Bài tập - Reading part 1",20));
        buoi2_tuan3.add(new BaiHocTrongNgay("T3. 2.3 Crossword Game",20));

        ArrayList<BaiHocTrongNgay> buoi3_tuan3 = new ArrayList<>();
        buoi3_tuan3.add(new BaiHocTrongNgay("T3. 3.1 Từ vựng",0));
        buoi3_tuan3.add(new BaiHocTrongNgay("T3. 3.2 Image Pairing Game",0));
        buoi3_tuan3.add(new BaiHocTrongNgay("T3. 3.3 Bài tập - Topic: Viết thư",0));

        ArrayList<BaiHocTrongNgay> buoi4_tuan3 = new ArrayList<>();
        buoi4_tuan3.add(new BaiHocTrongNgay("T3. 4.1 Từ vựng",0));
        buoi4_tuan3.add(new BaiHocTrongNgay("T3. 4.2 Bài tập - Speaking part 1",0));
        buoi4_tuan3.add(new BaiHocTrongNgay("T3. 4.3 Giới thiệu bản thân",0));

        ArrayList<Buoi> buoiList_tuan3 = new ArrayList<>();
        buoiList_tuan3.add(new Buoi("T3. Buổi 10 : Listening",buoi1_tuan3,1));
        buoiList_tuan3.add(new Buoi("T3. Buổi 20 : Reading",buoi2_tuan3,2));
        buoiList_tuan3.add(new Buoi("T3. Buổi 30 : Writing",buoi3_tuan3,3));
        buoiList_tuan3.add(new Buoi("T3. Buổi 40 : Speaking",buoi4_tuan3,4));

        tuanList = new ArrayList<>();
        tuanList.add(new Tuan("Tuần 1",1,buoiList_tuan1));
        tuanList.add(new Tuan("Tuần 2",1,buoiList_tuan2));
        tuanList.add(new Tuan("Tuần 3",1,buoiList_tuan3));
        tuanList.add(new Tuan("Kỳ thi kiểm tra",1,buoiList_tuan1));
        tuanList.add(new Tuan("Tuần 5",1,buoiList_tuan1));
        tuanList.add(new Tuan("Tuần 6",1,buoiList_tuan1));
    }

    @Override
    public int getCount() {
        return tuanList.size();
    }

    @Override
    public Tuan getTuan(int position) {
        return tuanList.get(position);
    }

    @Override
    public void onClickTuan(int position) {
        Intent intent = new Intent(ChiTietKhoaHocActivity.this, ChiTietTuanHocActivity.class);
        intent.putExtra("tuan", tuanList.get(position));
        startActivity(intent);
    }
}