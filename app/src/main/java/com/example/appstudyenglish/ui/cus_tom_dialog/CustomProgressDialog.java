package com.example.appstudyenglish.ui.cus_tom_dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import com.example.appstudyenglish.R;

public class CustomProgressDialog extends Dialog {
    public CustomProgressDialog(Context context){
        super(context);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(params);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading,null);
        setContentView(view);
    }
}
