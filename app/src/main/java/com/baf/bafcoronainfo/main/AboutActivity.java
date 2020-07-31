package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;


public class AboutActivity extends Activity {
    private String TAG = AboutActivity.class.getSimpleName();
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about);
        context = this;

    }



}
