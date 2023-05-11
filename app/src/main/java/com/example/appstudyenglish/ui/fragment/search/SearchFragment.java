package com.example.appstudyenglish.ui.fragment.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.FragmentSearchBinding;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.ui.khoa_hoc_info.KhoaHocInfoActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends Fragment implements KhoaHocSearchAdapter.IKhoaHocSearch {

    private FragmentSearchBinding binding;
    private View view;
    public static final String TAG = SearchFragment.class.getName();
    private List<KhoaHoc> khoaHocList;
    private List<KhoaHoc> khoaHocListOld;
    private KhoaHocSearchAdapter khoaHocSearchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search, container, false);
        view = binding.getRoot();
        addData();
        initRecyclerView();
        onSearch();
        return view;
    }

    private void onSearch() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                khoaHocList.clear();
                if(newText.isEmpty()){
                    addData();
                    binding.rcvKhoaHoc.getAdapter().notifyDataSetChanged();
                }else {
                    for (KhoaHoc khoaHoc : khoaHocListOld) {
                         if(khoaHoc.getName().toLowerCase().contains(newText.toLowerCase())){
                             khoaHocList.add(khoaHoc);
                         }
                    }
                    binding.rcvKhoaHoc.getAdapter().notifyDataSetChanged();
                }
                return false;
            }
        });
    }

    private void initRecyclerView() {
        khoaHocSearchAdapter = new KhoaHocSearchAdapter(this);
        binding.rcvKhoaHoc.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvKhoaHoc.setAdapter(khoaHocSearchAdapter);
    }

    private void addData() {
        khoaHocList = new ArrayList<>();
        khoaHocListOld = new ArrayList<>();
        khoaHocList.add(new KhoaHoc(1,R.drawable.khoa_1,"Level A0","Hoàng Minh","2 tuần",2301,""));
        khoaHocList.add(new KhoaHoc(2,R.drawable.khoa_2,"Level A1","Huy Nguyễn","3 tuần",3343,""));
        khoaHocList.add(new KhoaHoc(3,R.drawable.khoa_3,"Level A2","Trần Hậu","8 tuần",7663,""));
        khoaHocList.add(new KhoaHoc(4,R.drawable.khoa_4,"Level A3","Hải Hồ","12 tuần",9663,""));
        khoaHocListOld.addAll(khoaHocList);
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
    public void onClickKhoaHoc(int position) {
        Intent intent = new Intent(getContext(), KhoaHocInfoActivity.class);
        intent.putExtra("khoahoc",khoaHocList.get(position));
        startActivity(intent);
    }
}