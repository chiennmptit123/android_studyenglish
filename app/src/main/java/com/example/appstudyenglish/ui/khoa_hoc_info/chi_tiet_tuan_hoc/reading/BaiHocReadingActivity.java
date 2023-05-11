package com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc.reading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityBaiHocReadingBinding;
import com.example.appstudyenglish.model.CauHoi;
import com.example.appstudyenglish.model.CauTraLoi;
import com.example.appstudyenglish.ui.khoa_hoc_info.chi_tiet_tuan_hoc.complete.CompleteBaiHocActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BaiHocReadingActivity extends AppCompatActivity{
    private ActivityBaiHocReadingBinding binding;
    private List<CauHoi> cauHoiList;
    private int dem = 0;
    private long mTimeInMillis = 300000;
    private CauHoi mQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_bai_hoc_reading);
        cauHoiList = getData();
        if (cauHoiList.isEmpty()){
            return;
        }
        setData(cauHoiList.get(dem));
        binding.imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickNextQuestion();
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBackQuestion();
            }
        });
        onSetTimeDown();
    }
    private void onSetTimeDown() {
        new CountDownTimer(mTimeInMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                mTimeInMillis = millisUntilFinished;
                int minutes = (int) (mTimeInMillis/1000)/60;
                int seconds = (int) (mTimeInMillis/1000)%60;
                binding.txtTimeRead.setText(String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds));
            }
            public void onFinish() {

            }
        }.start();
    }
    private void setData(CauHoi cauHoi) {
        mQuestion = cauHoi;
        binding.txtQuestion.setText(cauHoi.getTitle());
        binding.rdDapAn1.setText(cauHoi.getCauTraLoiList().get(0).getTitleAnswer());
        binding.rdDapAn2.setText(cauHoi.getCauTraLoiList().get(1).getTitleAnswer());
        binding.rdDapAn3.setText(cauHoi.getCauTraLoiList().get(2).getTitleAnswer());
        binding.rdDapAn4.setText(cauHoi.getCauTraLoiList().get(3).getTitleAnswer());
        if(cauHoi.getDapAnChon() == 1){
            binding.rdDapAn1.setChecked(true);
        }else if(cauHoi.getDapAnChon() == 2){
            binding.rdDapAn2.setChecked(true);
        }else if(cauHoi.getDapAnChon() == 3){
            binding.rdDapAn3.setChecked(true);
        } else if(cauHoi.getDapAnChon() == 4){
            binding.rdDapAn4.setChecked(true);
        }
    }
    private void onClickBackQuestion() {
        setRadioChecked();
        if(dem == 0){
            finish();
        }else {
            dem--;
            setData(cauHoiList.get(dem));
            if(cauHoiList.get(dem).getDapAnChon() == 0){
                setRadioButton();
            }
        }
    }
    private void onClickNextQuestion() {
        setRadioChecked();
        if(dem == cauHoiList.size()-1){
           startActivity(new Intent(BaiHocReadingActivity.this, CompleteBaiHocActivity.class));
        }else {
            dem++;
            setData(cauHoiList.get(dem));
            if(cauHoiList.get(dem).getDapAnChon() == 0){
                setRadioButton();
            }
        }
    }
    private void setRadioChecked(){
        if(binding.rdDapAn1.isChecked()){
            cauHoiList.get(dem).setDapAnChon(1);
        }else if(binding.rdDapAn2.isChecked()){
            cauHoiList.get(dem).setDapAnChon(2);
        } else if(binding.rdDapAn3.isChecked()){
            cauHoiList.get(dem).setDapAnChon(3);
        } else if(binding.rdDapAn4.isChecked()){
            cauHoiList.get(dem).setDapAnChon(4);
        }
    }
    private void setRadioButton(){
        binding.rdDapAn1.setChecked(false);
        binding.rdDapAn2.setChecked(false);
        binding.rdDapAn3.setChecked(false);
        binding.rdDapAn4.setChecked(false);
    }
    private List<CauHoi> getData() {
        //cau1
        List<CauTraLoi> answerListenList1 = new ArrayList<>();
        answerListenList1.add(new CauTraLoi("difficult",false));
        answerListenList1.add(new CauTraLoi("lucrative",false));
        answerListenList1.add(new CauTraLoi("lengthy",true));
        answerListenList1.add(new CauTraLoi("free",false));
        //cau2
        List<CauTraLoi> answerListenList2 = new ArrayList<>();
        answerListenList2.add(new CauTraLoi("it takes place out of public view",true));
        answerListenList2.add(new CauTraLoi("mass media companies do not own production divisions",false));
        answerListenList2.add(new CauTraLoi("the output of mass media is intended to grab our attention",false));
        answerListenList2.add(new CauTraLoi("companies can function as both producers and distributors",false));
        //cau3
        List<CauTraLoi> answerListenList3 = new ArrayList<>();
        answerListenList3.add(new CauTraLoi("create",false));
        answerListenList3.add(new CauTraLoi("send out",false));
        answerListenList3.add(new CauTraLoi("take in",false));
        answerListenList3.add(new CauTraLoi("fertilize",true));

        //cau4
        List<CauTraLoi> answerListenList4= new ArrayList<>();
        answerListenList4.add(new CauTraLoi("the first step in mass media production",false));
        answerListenList4.add(new CauTraLoi("the most talked-about step in mass media production",false));
        answerListenList4.add(new CauTraLoi("at least as important as production",true));
        answerListenList4.add(new CauTraLoi("not as important as exhibition",false));
        //cau5
        List<CauTraLoi> answerListenList5 = new ArrayList<>();
        answerListenList5.add(new CauTraLoi("tell an interesting story",true));
        answerListenList5.add(new CauTraLoi("define a concept clearly",false));
        answerListenList5.add(new CauTraLoi("describe a scene vividly",false));
        answerListenList5.add(new CauTraLoi("argue with the reader",false));

        cauHoiList = new ArrayList<>();
        cauHoiList.add(new CauHoi(1,"Question 1.In this passage, “arduous” means _____.",answerListenList1,0));
        cauHoiList.add(new CauHoi(2,"Question 2.The passage states that people tend to focus on production because______.",answerListenList2,0));
        cauHoiList.add(new CauHoi(3,"Question 3.In this passage, to “disseminate” means to ____ .",answerListenList3,0));
        cauHoiList.add(new CauHoi(4,"Question 4.This passage states that distribution is _______",answerListenList4,0));
        cauHoiList.add(new CauHoi(5,"Question 5.The author’s purpose in writing this passage is to ______.",answerListenList5,0));

        return cauHoiList;
    }

}