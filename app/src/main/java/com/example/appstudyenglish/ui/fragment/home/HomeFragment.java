package com.example.appstudyenglish.ui.fragment.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.FragmentHomeBinding;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.model.User;
import com.example.appstudyenglish.ui.khoa_hoc_info.KhoaHocInfoActivity;
import com.example.appstudyenglish.ui.fragment.notification.NotificationFragment;
import com.example.appstudyenglish.ui.fragment.search.SearchFragment;
import com.example.appstudyenglish.ui.fragment.thong_tin_vstep.ThongTinVstepFragment;
import com.example.appstudyenglish.ui.test.TestActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  implements KhoaHocAdapter.IKhoaHoc {

    private View view;
    private FragmentHomeBinding binding;
    private List<KhoaHoc> listKhoaHoc;
    private List<KhoaHoc> listKhoaHoc2;
    private KhoaHocAdapter khoaHocAdapter;
    private KhoaHocAdapter khoaHocAdapter2;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        view = binding.getRoot();
        onCLick();
        addData();
        initAdapter();
        upLoadUserName();
        return view;
    }

    private void upLoadUserName() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser==null){
            binding.txtuserName.setText("Hoang Duy Tung");
            return;
        }
        else {
            String userID = firebaseUser.getUid();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference(userID);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user = snapshot.getValue(User.class);
                    binding.txtuserName.setText(user.getName());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void initAdapter() {
        khoaHocAdapter = new KhoaHocAdapter(this,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.rcvThongTinStep.setLayoutManager(linearLayoutManager);
        binding.rcvThongTinStep.setAdapter(khoaHocAdapter);

        khoaHocAdapter2 = new KhoaHocAdapter(this,2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.rcvCacKhoaHoc.setLayoutManager(linearLayoutManager2);
        binding.rcvCacKhoaHoc.setAdapter(khoaHocAdapter2);
    }


    private void addData() {
        listKhoaHoc = new ArrayList<>();
        listKhoaHoc.add(new KhoaHoc(10,R.drawable.khoa_5,"Chứng chỉ Vstep là gì ?","","19/05/2001",2001,""));
        listKhoaHoc.add(new KhoaHoc(11,R.drawable.khoa_6,"Lợi ích của Vstep ?","","06/09/2021",2001,""));
        listKhoaHoc.add(new KhoaHoc(12,R.drawable.khoa_7,"Khi nào cần học Tiếng anh ?","","19/05/2001",2001,""));

        listKhoaHoc2 = new ArrayList<>();
        listKhoaHoc2.add(new KhoaHoc(1,R.drawable.khoa_1,"Level A0","Hoàng Minh","2 tuần",2301,""));
        listKhoaHoc2.add(new KhoaHoc(2,R.drawable.khoa_2,"Level A1","Huy Nguyễn","3 tuần",3343,""));
        listKhoaHoc2.add(new KhoaHoc(3,R.drawable.khoa_3,"Level A2","Trần Hậu","8 tuần",7663,""));
        listKhoaHoc2.add(new KhoaHoc(4,R.drawable.khoa_4,"Level B1","Hải Hồ","12 tuần",9663,""));
    }


    private void onCLick() {
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragment searchFragment = new SearchFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentMain, searchFragment);
                fragmentTransaction.addToBackStack(SearchFragment.TAG);
                fragmentTransaction.commit();
            }
        });
        binding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationFragment notificationFragment = new NotificationFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentMain, notificationFragment);
                fragmentTransaction.addToBackStack(NotificationFragment.TAG);
                fragmentTransaction.commit();
            }
        });
        binding.layoutLamBaiTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TestActivity.class));
            }
        });
    }

    @Override
    public int getCount(int check) {
        if(check == 1){
            return listKhoaHoc.size();
        }else {
            return listKhoaHoc2.size();
        }
    }

    @Override
    public KhoaHoc getKhoaHoc(int position,int check) {
        if(check == 1){
            return listKhoaHoc.get(position);
        }else {
            return listKhoaHoc2.get(position);
        }
    }

    @Override
    public void onCLickKhoaHoc(int position,int check) {
        if(check == 1){
            ThongTinVstepFragment thongTinVstepFragment = new ThongTinVstepFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("stepinfo",listKhoaHoc.get(position));
            thongTinVstepFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentMain, thongTinVstepFragment);
            fragmentTransaction.addToBackStack(ThongTinVstepFragment.TAG);
            fragmentTransaction.commit();
        }else {
            Intent intent = new Intent(getContext(), KhoaHocInfoActivity.class);
            intent.putExtra("khoahoc",listKhoaHoc2.get(position));
            startActivity(intent);
        }
    }
}