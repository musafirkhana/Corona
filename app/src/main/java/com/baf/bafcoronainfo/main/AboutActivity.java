package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.util.AppConstant;
import com.baf.bafcoronainfo.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;


public class AboutActivity extends Activity implements View.OnClickListener {
    private String TAG = AboutActivity.class.getSimpleName();
    private Context context;
    private RelativeLayout re_share;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about);
        context = this;
        initUI();

    }

    private void initUI() {
        re_share=(RelativeLayout)findViewById(R.id.re_share);
        re_share.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.re_share:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "BAF CORONA INFO URL ");
                i.putExtra(Intent.EXTRA_TEXT, AppConstant.SHARE_URL);
                startActivity(Intent.createChooser(i, "Share URL"));
                break;
        }

    }

    public void BACK(View v) {
        this.finish();

    }


}
