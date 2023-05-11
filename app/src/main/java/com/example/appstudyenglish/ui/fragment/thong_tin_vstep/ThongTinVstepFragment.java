package com.example.appstudyenglish.ui.fragment.thong_tin_vstep;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.FragmentThongTinVstepBinding;
import com.example.appstudyenglish.model.KhoaHoc;

public class ThongTinVstepFragment extends Fragment {

    private View view;
    private FragmentThongTinVstepBinding binding;
    public static final String TAG =  ThongTinVstepFragment.class.getName();
    private KhoaHoc khoaHoc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_thong_tin_vstep, container, false);
        view = binding.getRoot();
        onClick();
        getAndSetData();
        return view;
    }

    private void getAndSetData() {
        khoaHoc = (KhoaHoc) getArguments().getSerializable("stepinfo");
        binding.content.setText(khoaHoc.getName());
        binding.imgAnhMoTa.setImageResource(khoaHoc.getAvatar());
    }

    private void onClick() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

}