package com.example.appstudyenglish.ui.fragment.notification;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.FragmentNotificationBinding;
import com.example.appstudyenglish.model.ThongBao;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment implements NotificationAdapter.INotification {

    private FragmentNotificationBinding binding;
    private View view;
    public static final String TAG = NotificationFragment.class.getName();
    private List<ThongBao> thongBaoList;
    private NotificationAdapter notificationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_notification, container, false);
        view = binding.getRoot();
        onClick();
        addData();
        initRecyclerview();
        return view;
    }

    private void initRecyclerview() {
        notificationAdapter = new NotificationAdapter(this);
        binding.rcvNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvNotification.setAdapter(notificationAdapter);
    }

    private void addData() {
        thongBaoList = new ArrayList<>();
        thongBaoList.add(new ThongBao(1,R.drawable.img_test,"Re: Thông tin về khóa học","Hoàng Minh","Em hoàn toàn có thể học mà không lo về chất lượng  dịch vụ của thầy nhá. ","12 phút trước"));
        thongBaoList.add(new ThongBao(2,0,"Thanh toán thành công khóa học: Toeic Cơ Bản 350+","Quang Nguyễn","Cảm ơn bạn đã tin tưởng về mua khóa học của chúng tôi.","1 tiếng trước"));
        thongBaoList.add(new ThongBao(3,0,"Thông báo hệ thống","Hệ thống","Chúc mừng bạn đã tạo tài khoản thành công, hãy liên kết tài khoản để nhận được nhiều lợi ích hơn. ","2 ngày trước"));
    }

    private void onClick() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public int getCount() {
        return thongBaoList.size();
    }

    @Override
    public ThongBao getNotification(int position) {
        return thongBaoList.get(position);
    }
}