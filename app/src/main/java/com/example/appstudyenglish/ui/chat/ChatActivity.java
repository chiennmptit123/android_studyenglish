package com.example.appstudyenglish.ui.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.ActivityChatBinding;
import com.example.appstudyenglish.model.KhoaHoc;
import com.example.appstudyenglish.model.Message;
import com.example.appstudyenglish.sqlite.SQLiteHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements MessageAdapter.IMessage {

    private ActivityChatBinding binding;
    private KhoaHoc khoaHoc;
    private List<Message> messageList;
    private MessageAdapter messageAdapter;
    FirebaseUser firebaseUser;
    SQLiteHelper sqLiteHelper;
    String idAcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chat);
        khoaHoc = (KhoaHoc) getIntent().getSerializableExtra("khoahoc");
        binding.imageAvtKhoaHoc.setImageResource(khoaHoc.getAvatar());
        binding.txtTenKhoaHoc.setText(khoaHoc.getName());
        messageList = new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(this,"Data.sqlite",null,5);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAcount = firebaseUser.getUid();
        GetData();
        initRecyclerView();
        binding.rcvMessage.scrollToPosition(messageList.size() - 1);
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSendMessage();
            }
        });
    }

    private void onClickSendMessage() {
        String message = binding.edMessage.getText().toString().trim();
        if(message.isEmpty()){
            Toast.makeText(this, "Hãy nhập tin nhắn !", Toast.LENGTH_SHORT).show();
        }else {
            sqLiteHelper.QueryData("INSERT INTO TinNhan VALUES(null,'"+ idAcount +"','"+khoaHoc.getMaKhoaHoc()+"','"+message+"','"+ngay()+"')");
            GetData();
            messageAdapter.notifyDataSetChanged();
            binding.rcvMessage.scrollToPosition(messageList.size() - 1);
            binding.edMessage.setText("");
        }
    }

    void GetData(){
        messageList.clear();
        Cursor dataTinNhan = sqLiteHelper.GetData("SELECT * FROM TinNhan WHERE UserId ='"+idAcount+"' AND MaKhoaHoc='"+khoaHoc.getMaKhoaHoc()+"'");
        while (dataTinNhan.moveToNext()){
            String tinNhan = dataTinNhan.getString(3);
            String date = dataTinNhan.getString(4);
            messageList.add(new Message(tinNhan,date));
        }
    }

    private void initRecyclerView() {
        messageAdapter = new MessageAdapter(this);
        binding.rcvMessage.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvMessage.setAdapter(messageAdapter);
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Message getMessage(int position) {
        return messageList.get(position);
    }

    public String ngay(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String ngay = dateFormat.format(cal.getTime());
        return ngay;
    }
}