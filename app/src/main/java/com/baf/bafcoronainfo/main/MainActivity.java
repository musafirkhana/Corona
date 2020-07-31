package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.adapter.SliderAdapter;
import com.baf.bafcoronainfo.model.SliderItem;
import com.baf.bafcoronainfo.util.BusyDialog;
import com.baf.bafcoronainfo.util.PersistentUser;
import com.baf.bafcoronainfo.util.ToastUtil;
import com.google.android.material.card.MaterialCardView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    private Context mContext;
    private BusyDialog mBusyDialog;
    HashMap<String, Integer> HashMapForLocalRes;
    private SliderAdapter adapter;
    SliderView sliderView;


    private ToastUtil toastUtil;
    private String text;
    private String respones_results;

    private MaterialCardView mat_coronabot, mat_success;
    private MaterialCardView card_about, mat_help;
    private MaterialCardView mat_prevention, mat_corona_state;

    String[] alphabet = new String[]{"dai", "dplans", "dfs", "drect", "dproj", "dac", "dao", "dat",
            "dad", "dedn", "dmet", "dats", "dcwit", "doao", "dengg", "dce", "darmt", "dsup", "dpers",
            "dwks", "dfin", "pm", "dms", "dwc",
            "coas", "pstocoas", "airsecy", "dyairsecy", "ci",
            "acaso", "acasp", "acasm", "acasa"};
    List<String> list = Arrays.asList(alphabet);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        toastUtil = new ToastUtil(this);

        initUI();


    }

    private void initUI() {

        sliderView = findViewById(R.id.imageSlider);
        adapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        renewItems();

        mat_coronabot = (MaterialCardView) findViewById(R.id.mat_coronabot);
        card_about = (MaterialCardView) findViewById(R.id.card_about);
        mat_prevention = (MaterialCardView) findViewById(R.id.mat_prevention);
        mat_corona_state = (MaterialCardView) findViewById(R.id.mat_corona_state);
        mat_help = (MaterialCardView) findViewById(R.id.mat_help);
        mat_success = (MaterialCardView) findViewById(R.id.mat_success);
        mat_coronabot.setOnClickListener(this);
        card_about.setOnClickListener(this);
        mat_prevention.setOnClickListener(this);
        mat_corona_state.setOnClickListener(this);
        mat_help.setOnClickListener(this);
        mat_success.setOnClickListener(this);

    }

    public void renewItems() {
        List<SliderItem> sliderItemList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("Slider Item " + i);
            sliderItem.setImageUrl("https://skyapi.website/storage/images/image" + i + ".jpg");
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mat_coronabot:
                Intent intent = new Intent(this, CoronaBotActivity.class);
                startActivity(intent);
                break;
            case R.id.card_about:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.mat_prevention:
                Intent prevention = new Intent(this, WhattoDoActivity.class);
                startActivity(prevention);
                break;
            case R.id.mat_corona_state:
                Log.i("Password", PersistentUser.getUserpassword(mContext));

                if (list.contains(PersistentUser.getUserpassword(mContext))) {
                    Intent stateIntent = new Intent(getApplicationContext(), CoronaStateActivity.class);
                    startActivity(stateIntent);
                } else {
                    showCustomDialog();
                }
                break;
            case R.id.mat_help:
                Intent helpIntent = new Intent(this, HelpActivity.class);
                startActivity(helpIntent);
                break;
            case R.id.mat_success:
                Intent successIntent = new Intent(this, SuccessStoryActivity.class);
                startActivity(successIntent);
                break;
        }

    }


    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.password_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        TextView btn_cancel = (TextView) dialogView.findViewById(R.id.btn_cancel);
        TextView btn_apply = (TextView) dialogView.findViewById(R.id.btn_apply);
        final EditText filter_head = (EditText) dialogView.findViewById(R.id.et_password);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.contains(filter_head.getText().toString())) {
                    PersistentUser.setUserpassword(mContext, filter_head.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), CoronaStateActivity.class);
                    startActivity(intent);
                    alertDialog.dismiss();

                } else if (filter_head.getText().toString().equalsIgnoreCase("")) {
                    toastUtil.appSuccessMsg(mContext, "Please Enter Password");
                } else {
                    toastUtil.appSuccessMsg(mContext, "Sory Password Does not match Try Again");
                }


            }
        });
        alertDialog.show();
    }


}
