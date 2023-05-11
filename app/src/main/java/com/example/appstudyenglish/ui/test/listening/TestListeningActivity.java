package com.example.appstudyenglish.ui.test.listening;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityTestListeningBinding;
import com.example.appstudyenglish.model.CauHoi;
import com.example.appstudyenglish.model.CauTraLoi;
import com.example.appstudyenglish.sqlite.SQLiteHelper;
import com.example.appstudyenglish.ui.test.reading.ReadingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestListeningActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityTestListeningBinding binding;
    private MediaPlayer mediaPlayer;
    Handler handler;
    private List<CauHoi> mListQuestion;
    private int currentQuestion = 0;
    private CauHoi mQuestion;
    private static int point = 0;
    private boolean checkPoint;
    private long mTimeInMillis = 300000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_test_listening);
        mediaPlayer = MediaPlayer.create(TestListeningActivity.this,R.raw.filelistening);
        binding.seekBar.setMax(mediaPlayer.getDuration());
        handler = new Handler();
        onClickListener();
        mListQuestion = getListQuestion();
        if (mListQuestion.isEmpty()){
            return;
        }
        setDataQuestionListen(mListQuestion.get(currentQuestion));
        onSetTimeDown();
    }

    private void onSetTimeDown() {
        new CountDownTimer(mTimeInMillis, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimeInMillis = millisUntilFinished;
                int minutes = (int) (mTimeInMillis/1000)/60;
                int seconds = (int) (mTimeInMillis/1000)%60;
                binding.txtTimeListen.setText(String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds));
            }

            public void onFinish() {

            }

        }.start();
    }


    private void setDataQuestionListen(CauHoi questionListening) {
        if (questionListening==null){
            return;
        }
        mQuestion = questionListening;

        binding.txtNumberListen.setText(String.valueOf(questionListening.getStt()));
        binding.txtQuestionListen.setText(questionListening.getTitle());
        binding.radioAnswer1.setText(questionListening.getCauTraLoiList().get(0).getTitleAnswer());
        binding.radioAnswer2.setText(questionListening.getCauTraLoiList().get(1).getTitleAnswer());
        binding.radioAnswer3.setText(questionListening.getCauTraLoiList().get(2).getTitleAnswer());
        binding.radioAnswer4.setText(questionListening.getCauTraLoiList().get(3).getTitleAnswer());

        binding.radioAnswer1.setOnClickListener(this);
        binding.radioAnswer2.setOnClickListener(this);
        binding.radioAnswer3.setOnClickListener(this);
        binding.radioAnswer4.setOnClickListener(this);

        if(questionListening.getDapAnChon() == 1){
            binding.radioAnswer1.setChecked(true);
        }else if(questionListening.getDapAnChon() == 2){
            binding.radioAnswer2.setChecked(true);
        }else if(questionListening.getDapAnChon() == 3){
            binding.radioAnswer3.setChecked(true);
        } else if(questionListening.getDapAnChon() == 4){
            binding.radioAnswer4.setChecked(true);
        }
//        Toast.makeText(this, ""+questionListening.getDapAnChon(), Toast.LENGTH_SHORT).show();
    }


    private void onClickListener() {
        binding.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer!=null){
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                        binding.imgPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    }else {
                        binding.imgPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                        mediaPlayer.start();
                        UpdateSeekbar updateSeekbar = new UpdateSeekbar();
                        handler.post(updateSeekbar);
                    }
                }
            }
        });
        binding.imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backQuestion();
            }
        });
    }

    @Override
    public void onClick(View view) {
       if (binding.radioAnswer1.isChecked()){
           checkAnswer(mQuestion.getCauTraLoiList().get(0));
       }else if (binding.radioAnswer2.isChecked()){
           checkAnswer(mQuestion.getCauTraLoiList().get(1));
       }else if (binding.radioAnswer3.isChecked()){
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

    private void backQuestion() {
        setRadioChecked();
        if (currentQuestion == 0){
            mediaPlayer.pause();
            startActivity(new Intent(TestListeningActivity.this,ListeningActivity.class));
        }else {
            currentQuestion--;
            setDataQuestionListen(mListQuestion.get(currentQuestion));
            if(mListQuestion.get(currentQuestion).getDapAnChon() == 0){
                setRadioButton();
            }
        }
    }

    private void nextQuestion() {
        setRadioChecked();
        if (currentQuestion == mListQuestion.size()-1){
            mediaPlayer.pause();
            if (checkPoint){
                point += 5;
                checkPoint = false;
            }
            Toast.makeText(TestListeningActivity.this,"Point : " + point,Toast.LENGTH_SHORT).show();
            savePointAndNextActivity();
        }else {
            if (checkPoint){
                point += 5;
            }
            Toast.makeText(TestListeningActivity.this,"Point : " + point,Toast.LENGTH_SHORT).show();
            currentQuestion++;
            setDataQuestionListen(mListQuestion.get(currentQuestion));
            if(mListQuestion.get(currentQuestion).getDapAnChon() == 0){
                setRadioButton();
            }
        }
    }

    private void savePointAndNextActivity() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext(), "Data.sqlite", null, 5);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = firebaseUser.getUid();
        startActivity(new Intent(TestListeningActivity.this,ReadingActivity.class));
        sqLiteHelper.QueryData("UPDATE BaiTest SET DiemNghe='"+point+"' WHERE UserId='"+userID+"'");
    }

    private void setRadioButton(){
        binding.radioAnswer1.setChecked(false);
        binding.radioAnswer2.setChecked(false);
        binding.radioAnswer3.setChecked(false);
        binding.radioAnswer4.setChecked(false);
    }

    private void setRadioChecked(){
        if(binding.radioAnswer1.isChecked()){
            mListQuestion.get(currentQuestion).setDapAnChon(1);
        }else if(binding.radioAnswer2.isChecked()){
            mListQuestion.get(currentQuestion).setDapAnChon(2);
        } else if(binding.radioAnswer3.isChecked()){
            mListQuestion.get(currentQuestion).setDapAnChon(3);
        } else if(binding.radioAnswer4.isChecked()){
            mListQuestion.get(currentQuestion).setDapAnChon(4);
        }
    }

    public class UpdateSeekbar implements Runnable{

        @Override
        public void run() {
            binding.seekBar.setProgress(mediaPlayer.getCurrentPosition()*3);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            binding.timeMP3.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
            handler.postDelayed(this,100);
        }
    }

    private List<CauHoi> getListQuestion(){
        List<CauHoi> questionList = new ArrayList<>();
        //cau1
        List<CauTraLoi> answerListenList1 = new ArrayList<>();
        answerListenList1.add(new CauTraLoi("two",false));
        answerListenList1.add(new CauTraLoi("three",false));
        answerListenList1.add(new CauTraLoi("four",true));
        answerListenList1.add(new CauTraLoi("five",false));
        //cau2
        List<CauTraLoi> answerListenList2 = new ArrayList<>();
        answerListenList2.add(new CauTraLoi("originated",true));
        answerListenList2.add(new CauTraLoi("destroyed",false));
        answerListenList2.add(new CauTraLoi("inferred",false));
        answerListenList2.add(new CauTraLoi("discussed",false));
        //cau3
        List<CauTraLoi> answerListenList3 = new ArrayList<>();
        answerListenList3.add(new CauTraLoi("fish hook",false));
        answerListenList3.add(new CauTraLoi("bow and arrow",false));
        answerListenList3.add(new CauTraLoi("hatchet",false));
        answerListenList3.add(new CauTraLoi("pottery",true));

        //cau4
        List<CauTraLoi> answerListenList4= new ArrayList<>();
        answerListenList4.add(new CauTraLoi("farming",false));
        answerListenList4.add(new CauTraLoi("clothing",false));
        answerListenList4.add(new CauTraLoi("living indoors",true));
        answerListenList4.add(new CauTraLoi("using fire",false));
        //cau5
        List<CauTraLoi> answerListenList5 = new ArrayList<>();
        answerListenList5.add(new CauTraLoi("using fire",true));
        answerListenList5.add(new CauTraLoi("complex",false));
        answerListenList5.add(new CauTraLoi("vulgar",false));
        answerListenList5.add(new CauTraLoi("primitive",false));
        //cau6
        List<CauTraLoi> answerListenList6 = new ArrayList<>();
        answerListenList6.add(new CauTraLoi("durable",false));
        answerListenList6.add(new CauTraLoi("vegetation",false));
        answerListenList6.add(new CauTraLoi("weapons",false));
        answerListenList6.add(new CauTraLoi("stone caves",true));

        //cau7
        List<CauTraLoi> answerListenList7 = new ArrayList<>();
        answerListenList7.add(new CauTraLoi("sedentary",false));
        answerListenList7.add(new CauTraLoi("wandering",false));
        answerListenList7.add(new CauTraLoi("primitive",true));
        answerListenList7.add(new CauTraLoi("inquisitive",false));
        //cau8
        List<CauTraLoi> answerListenList8 = new ArrayList<>();
        answerListenList8.add(new CauTraLoi("families",true));
        answerListenList8.add(new CauTraLoi("periods",false));
        answerListenList8.add(new CauTraLoi("herds",false));
        answerListenList8.add(new CauTraLoi("tools",false));
        //cau9
        List<CauTraLoi> answerListenList9 = new ArrayList<>();
        answerListenList9.add(new CauTraLoi("people were inventive",false));
        answerListenList9.add(new CauTraLoi("people were warriors",false));
        answerListenList9.add(new CauTraLoi("people were crude",false));
        answerListenList9.add(new CauTraLoi("people stayed indoors ",true));

        //cau10
        List<CauTraLoi> answerListenList10 = new ArrayList<>();
        answerListenList10.add(new CauTraLoi("The Neolithic Age",false));
        answerListenList10.add(new CauTraLoi("The Stone Age",false));
        answerListenList10.add(new CauTraLoi("The Ice Age",false));
        answerListenList10.add(new CauTraLoi("The Paleolithic Age",true));

        questionList.add(new CauHoi(1,"Into how many periods was the Stone Age divided ?",answerListenList1,0));
        questionList.add(new CauHoi(2,"The word “derived” is closest meaning to____.",answerListenList2,0));
        questionList.add(new CauHoi(3,"Which of the following was developed earliest ?",answerListenList3,0));
        questionList.add(new CauHoi(4,"Which of the following developments is NOT related to the conditions of the Ice Age ?",answerListenList4,0));
        questionList.add(new CauHoi(5,"The word “crude” is closest meaning to____.",answerListenList5,0));
        questionList.add(new CauHoi(6,"The author states that the Stone Age was so named because___.",answerListenList6,0));
        questionList.add(new CauHoi(7,"The word “nomadic” is closest meaning to_____.",answerListenList7,0));
        questionList.add(new CauHoi(8,"The word “eras” is closest meaning to____.",answerListenList8,0));
        questionList.add(new CauHoi(9,"Which of the following best describes the Mesolithic Age ?",answerListenList9,0));
        questionList.add(new CauHoi(10,"With what subject is the passage mainly concerned ?",answerListenList10,0));
        return questionList;
    }

}