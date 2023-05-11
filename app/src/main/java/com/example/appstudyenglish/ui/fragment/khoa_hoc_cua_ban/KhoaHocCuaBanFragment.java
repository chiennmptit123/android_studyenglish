package com.example.appstudyenglish.ui.fragment.khoa_hoc_cua_ban;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.FragmentKhoaHocCuaBanBinding;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.sqlite.SQLiteHelper;
import com.example.appstudyenglish.ui.chat.ChatActivity;
import com.example.appstudyenglish.ui.chi_tiet_khoa_hoc.ChiTietKhoaHocActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class KhoaHocCuaBanFragment extends Fragment implements KhoaHocCuaBanAdapter.IKhoaHocCuaBan {

    private View view;
    private FragmentKhoaHocCuaBanBinding binding;
    private List<KhoaHoc> khoaHocList;
    private SQLiteHelper sqLiteHelper;
    private KhoaHocCuaBanAdapter khoaHocCuaBanAdapter;
    String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_khoa_hoc_cua_ban, container, false);
        view = binding.getRoot();
        sqLiteHelper = new SQLiteHelper(getContext(), "Data.sqlite", null, 5);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userID = firebaseUser.getUid();
        khoaHocList = new ArrayList<>();
        getDataFromDataBase();
        if(khoaHocList.size() == 0){
            binding.tvThongBao.setVisibility(View.VISIBLE);
        }
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        khoaHocCuaBanAdapter = new KhoaHocCuaBanAdapter(this);
        binding.rcvKhoaHocCuaToi.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvKhoaHocCuaToi.setAdapter(khoaHocCuaBanAdapter);
    }


    private void getDataFromDataBase() {
        khoaHocList.clear();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM KhoaHocDaMua WHERE UserId = '"+userID+"' ORDER BY Id DESC");
        while(data.moveToNext()){
            int maKhoaHoc = data.getInt(2);
            int avatar = data.getInt(4);
            String tenKhoaHoc = data.getString(3);
            String tenGiaoVien = data.getString(5);
            String thoiGian = data.getString(6);
            khoaHocList.add(new KhoaHoc(maKhoaHoc,avatar,tenKhoaHoc,tenGiaoVien,thoiGian,0,""));
        }
    }

    @Override
    public int getCount() {
        return khoaHocList.size();
    }

    @Override
    public KhoaHoc getKhoaHoc(int position) {
        return khoaHocList.get(position);
    }

    @Override
    public void onClickVaoHoc(int position) {
        Intent intent = new Intent(getContext(), ChiTietKhoaHocActivity.class);
        intent.putExtra("khoahoc",khoaHocList.get(position));
        startActivity(intent);
    }

    @Override
    public void onClickChat(int position) {
        Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.putExtra("khoahoc",khoaHocList.get(position));
        startActivity(intent);
    }

    @Override
    public void onClickDelete(int position) {
        Dialog dialog = new Dialog(getContext());//Khởi tạo dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//ẩn thanh tiêu đề của dialog
        dialog.setCancelable(false);//hiển thị thông báo khi người dùng tương tác với dialog
        dialog.setContentView(R.layout.dialog_delete);//set layout cho dialog
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel); //ánh xạ button cancel
        Button btnOk = (Button) dialog.findViewById(R.id.btnOk); //ánh xạ button ok
        btnCancel.setOnClickListener(new View.OnClickListener() {//bắt sự kiện button cancel
            @Override
            public void onClick(View view) {
                dialog.dismiss();//Nếu người dùng click button cancel thì sẽ đóng dialog
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {//bắt sự kiện button ok
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                sqLiteHelper.QueryData("DELETE FROM KhoaHocDaMua WHERE UserId = '"+userID+"' AND MaKhoaHoc = '"+ khoaHocList.get(position).getMaKhoaHoc()+"' "); //khởi tạo sqlite để xóa dữ liệu của bảng Khoahoc theo Id
                Toast.makeText(getContext(),"Delete Success !!!",Toast.LENGTH_LONG).show(); //Hiển thị toast thông báo xóa thành công
                dialog.dismiss(); //Đóng dialog
                getDataFromDataBase();
                initRecyclerView();
            }
        });
        dialog.show(); //giúp hiển thị dialog
    }
}