package com.example.appstudyenglish.ui.test.reading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityTestReadingBinding;
import com.example.appstudyenglish.model.CauHoi;
import com.example.appstudyenglish.model.CauTraLoi;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.sqlite.SQLiteHelper;
import com.example.appstudyenglish.ui.test.listening.TestListeningActivity;
import com.example.appstudyenglish.ui.test.speaking.SpeakingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestReadingActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityTestReadingBinding binding;
    private List<CauHoi> cauHoiList;
    private int dem = 0;
    private static int point = 0;
    private boolean checkPoint;
    private long mTimeInMillis = 300000;
    private CauHoi mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test_reading);
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
        binding.txtThuTuCauHoi.setText(dem+1+  "/" +cauHoiList.size());
        binding.txtQuestion.setText(cauHoi.getTitle());
        binding.rdDapAn1.setText(cauHoi.getCauTraLoiList().get(0).getTitleAnswer());
        binding.rdDapAn2.setText(cauHoi.getCauTraLoiList().get(1).getTitleAnswer());
        binding.rdDapAn3.setText(cauHoi.getCauTraLoiList().get(2).getTitleAnswer());
        binding.rdDapAn4.setText(cauHoi.getCauTraLoiList().get(3).getTitleAnswer());
        binding.rdDapAn1.setOnClickListener(this);
        binding.rdDapAn2.setOnClickListener(this);
        binding.rdDapAn3.setOnClickListener(this);
        binding.rdDapAn4.setOnClickListener(this);
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
            startActivity(new Intent(TestReadingActivity.this,ReadingActivity.class));
        }else {
            dem--;
            setData(cauHoiList.get(dem));
            if(cauHoiList.get(dem).getDapAnChon() == 0){
                setRadioButton();
                Toast.makeText(this, ""+dem, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onClickNextQuestion() {
        setRadioChecked();
        if(dem == 9){
            if (checkPoint){
                point += 5;
            }
            savePointAndNextActivity();
        }else {
            if (checkPoint){
                point += 5;
            }
            dem++;
            setData(cauHoiList.get(dem));
            if(cauHoiList.get(dem).getDapAnChon() == 0){
                setRadioButton();
            }
        }
    }

    private void savePointAndNextActivity() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = firebaseUser.getUid();
        startActivity(new Intent(TestReadingActivity.this, SpeakingActivity.class));
        sqLiteHelper.QueryData("UPDATE BaiTest SET DiemDoc='"+point+"' WHERE UserId='"+userID+"'");
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
        answerListenList1.add(new CauTraLoi("The production procession",false));
        answerListenList1.add(new CauTraLoi("The equipment needed",false));
        answerListenList1.add(new CauTraLoi("The high cost",true));
        answerListenList1.add(new CauTraLoi("The role of the",false));
        //cau2
        List<CauTraLoi> answerListenList2 = new ArrayList<>();
        answerListenList2.add(new CauTraLoi("first frame",true));
        answerListenList2.add(new CauTraLoi("middle frames",false));
        answerListenList2.add(new CauTraLoi("last frame",false));
        answerListenList2.add(new CauTraLoi("entire sequences of frames",false));
        //cau3
        List<CauTraLoi> answerListenList3 = new ArrayList<>();
        answerListenList3.add(new CauTraLoi("formulas",false));
        answerListenList3.add(new CauTraLoi("objects",false));
        answerListenList3.add(new CauTraLoi("numbers",false));
        answerListenList3.add(new CauTraLoi("database",true));

        //cau4
        List<CauTraLoi> answerListenList4= new ArrayList<>();
        answerListenList4.add(new CauTraLoi("drawing several versions",false));
        answerListenList4.add(new CauTraLoi("enlarging one frame",false));
        answerListenList4.add(new CauTraLoi("analyzing the sequence",true));
        answerListenList4.add(new CauTraLoi("using computer calculations",false));
        //cau5
        List<CauTraLoi> answerListenList5 = new ArrayList<>();
        answerListenList5.add(new CauTraLoi("separates",true));
        answerListenList5.add(new CauTraLoi("registers",false));
        answerListenList5.add(new CauTraLoi("describes",false));
        answerListenList5.add(new CauTraLoi("numbers",false));
        //cau6
        List<CauTraLoi> answerListenList6 = new ArrayList<>();
        answerListenList6.add(new CauTraLoi("12",false));
        answerListenList6.add(new CauTraLoi("2",false));
        answerListenList6.add(new CauTraLoi("4",false));
        answerListenList6.add(new CauTraLoi("6",true));

        //cau7
        List<CauTraLoi> answerListenList7 = new ArrayList<>();
        answerListenList7.add(new CauTraLoi("before",false));
        answerListenList7.add(new CauTraLoi("since",false));
        answerListenList7.add(new CauTraLoi("after",true));
        answerListenList7.add(new CauTraLoi("while",false));
        //cau8
        List<CauTraLoi> answerListenList8 = new ArrayList<>();
        answerListenList8.add(new CauTraLoi("new",true));
        answerListenList8.add(new CauTraLoi("extra",false));
        answerListenList8.add(new CauTraLoi("accelerating",false));
        answerListenList8.add(new CauTraLoi("surprising",false));
        //cau9
        List<CauTraLoi> answerListenList9 = new ArrayList<>();
        answerListenList9.add(new CauTraLoi("possibility",false));
        answerListenList9.add(new CauTraLoi("position",false));
        answerListenList9.add(new CauTraLoi("time",false));
        answerListenList9.add(new CauTraLoi("job",true));

        //cau10
        List<CauTraLoi> answerListenList10 = new ArrayList<>();
        answerListenList10.add(new CauTraLoi("Canadians",false));
        answerListenList10.add(new CauTraLoi("years",false));
        answerListenList10.add(new CauTraLoi("decades",false));
        answerListenList10.add(new CauTraLoi("marriages",true));

        cauHoiList = new ArrayList<>();
        cauHoiList.add(new CauHoi(1,"What aspect of computer animation does the passage mainly discuss?",answerListenList1,0));
        cauHoiList.add(new CauHoi(2,"According to the passage, in computer-assisted animation the role of the computer is to draw the _____.",answerListenList2,0));
        cauHoiList.add(new CauHoi(3,"The word “they” in the second paragraph refers to .",answerListenList3,0));
        cauHoiList.add(new CauHoi(4,"According to the passage, the frame buffers mentioned in the third paragraph are used to ____.",answerListenList4,0));
        cauHoiList.add(new CauHoi(5,"According to the passage, the positions and colors of the figures in high-tech animation are determined by ____.",answerListenList5,0));
        cauHoiList.add(new CauHoi(6,"The word “captures” in the fourth paragraph is closest in meaning to _____.",answerListenList6,0));
        cauHoiList.add(new CauHoi(7,"The word “Once” in the fourth paragraph is closest in meaning to_____.",answerListenList7,0));
        cauHoiList.add(new CauHoi(8,"According to the passage, how do computer-animation companies often test motion?",answerListenList8,0));
        cauHoiList.add(new CauHoi(9,"The word “task” in the fourth paragraph is closest in meaning to____.",answerListenList9,0));
        cauHoiList.add(new CauHoi(10,"The word “five” in bold refers to .",answerListenList10,0));

        return cauHoiList;
    }

    @Override
    public void onClick(View view) {
        if (binding.rdDapAn1.isChecked()){
            checkAnswer(mQuestion.getCauTraLoiList().get(0));
        }else if (binding.rdDapAn2.isChecked()){
            checkAnswer(mQuestion.getCauTraLoiList().get(1));

        }else if (binding.rdDapAn3.isChecked()){
            checkAnswer(mQuestion.getCauTraLoiList().get(2));

        }else {
            checkAnswer(mQuestion.getCauTraLoiList().get(3));
        }
    }
    private void checkAnswer(CauTraLoi answer){
        if (answer.isAnswer()){
            checkPoint = true;
        }else {
            checkPoint = false;
        }
    }
}