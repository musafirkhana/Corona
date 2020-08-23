package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


import com.baf.bafcoronainfo.R;
import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;


public class WhattoDoActivity extends Activity implements View.OnClickListener {
    Typeface tf;
    private Context mContext;
    private MaterialCardView mat_direction_1;
    private MaterialCardView mat_direction_2;
    private MaterialCardView mat_direction_3;
    private MaterialCardView mat_direction_4;
    private MaterialCardView mat_direction_5;

    private String titleText;
    private TextView topbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pdf_main);
        titleText=getIntent().getStringExtra("title");
        mContext = this;
        initUI();

    }

    private void initUI() {
        topbar=(TextView)findViewById(R.id.topbar);
        topbar.setText(titleText);
        mat_direction_1=(MaterialCardView)findViewById(R.id.mat_direction_1);
        mat_direction_2=(MaterialCardView)findViewById(R.id.mat_direction_2);
        mat_direction_3=(MaterialCardView)findViewById(R.id.mat_direction_3);
        mat_direction_4=(MaterialCardView)findViewById(R.id.mat_direction_4);
        mat_direction_5=(MaterialCardView)findViewById(R.id.mat_direction_5);
        mat_direction_1.setOnClickListener(this);
        mat_direction_2.setOnClickListener(this);
        mat_direction_3.setOnClickListener(this);
        mat_direction_4.setOnClickListener(this);
        mat_direction_5.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mat_direction_1:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("section", "1");
                intent.putExtra("title", getResources().getString(R.string.corona_direction_head_1));
                startActivity(intent);
                break;
            case R.id.mat_direction_2:
                Intent intent2 = new Intent(this, WebViewActivity.class);
                intent2.putExtra("section", "2");
                intent2.putExtra("title", getResources().getString(R.string.corona_direction_head_2));
                startActivity(intent2);
                break;
            case R.id.mat_direction_3:
                Intent intent3 = new Intent(this, WebViewActivity.class);
                intent3.putExtra("section", "3");
                intent3.putExtra("title", getResources().getString(R.string.corona_direction_head_3));
                startActivity(intent3);
                break;
            case R.id.mat_direction_4:
                Intent intent4 = new Intent(this, WebViewActivity.class);
                intent4.putExtra("section", "4");
                intent4.putExtra("title", getResources().getString(R.string.corona_direction_head_4));
                startActivity(intent4);
                break;
            case R.id.mat_direction_5:
                Intent intent5 = new Intent(this, WebViewActivity.class);
                intent5.putExtra("section", "5");
                intent5.putExtra("title", getResources().getString(R.string.corona_direction_head_5));
                startActivity(intent5);
                break;
        }


    }





    public void BACK(View v) {
        this.finish();

    }


}
