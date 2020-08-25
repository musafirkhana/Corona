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
    private RelativeLayout re_terms;
    private  RelativeLayout re_privacy;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about);
        context = this;
        initUI();

    }

    private void initUI() {
        re_share=(RelativeLayout)findViewById(R.id.re_share);
        re_terms=(RelativeLayout)findViewById(R.id.re_terms);
        re_privacy=(RelativeLayout)findViewById(R.id.re_privacy);
        re_share.setOnClickListener(this);
        re_terms.setOnClickListener(this);
        re_privacy.setOnClickListener(this);
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

            case R.id.re_terms:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("section", "10");
                intent.putExtra("title", getResources().getString(R.string.terms));
                startActivity(intent);
                break;
            case R.id.re_privacy:
                Intent privacyIntent = new Intent(this, WebViewActivity.class);
                privacyIntent.putExtra("section", "11");
                privacyIntent.putExtra("title", getResources().getString(R.string.privacy));
                startActivity(privacyIntent);
                break;
        }

    }

    public void BACK(View v) {
        this.finish();

    }


}
