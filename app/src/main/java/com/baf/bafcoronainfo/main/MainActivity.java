package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.adapter.MainSliderAdapter;
import com.baf.bafcoronainfo.callbackinterface.ServerResponse;
import com.baf.bafcoronainfo.networkcalls.ServerCallsProvider;
import com.baf.bafcoronainfo.parser.BaseStatelistParser;
import com.baf.bafcoronainfo.parser.StatelistParser;
import com.baf.bafcoronainfo.util.AllUrls;
import com.baf.bafcoronainfo.util.AppConstant;
import com.baf.bafcoronainfo.util.BusyDialog;
import com.baf.bafcoronainfo.util.Helpers;
import com.baf.bafcoronainfo.util.Logger;
import com.baf.bafcoronainfo.util.PersistentUser;
import com.baf.bafcoronainfo.util.PicassoImageLoadingService;
import com.baf.bafcoronainfo.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;

import static com.android.volley.VolleyLog.TAG;

public class MainActivity extends Activity {

    private Context mContext;
    private BusyDialog mBusyDialog;
    HashMap<String, Integer> HashMapForLocalRes;
    private Slider slider;

    private ToastUtil toastUtil;
    private String text;
    private String respones_results;

    String[] alphabet = new String[]{"dai", "dplans", "dfs", "drect", "dproj", "dac", "dao", "dat",
            "dad", "dedn", "dmet", "dats", "dcwit", "doao", "dengg", "dce", "darmt", "dsup", "dpers",
            "dwks", "dfin", "pm", "dms", "dwc"};
    List<String> list = Arrays.asList(alphabet);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        toastUtil = new ToastUtil(this);
        Slider.init(new PicassoImageLoadingService(this));
        initUI();


    }
    private void initUI() {
        slider = findViewById(R.id.slider);

        //Call this method to add images from local drawable folder .
        //AddImageUrlFormLocalRes();

        slider.postDelayed(new Runnable() {
            @Override
            public void run() {
                slider.setAdapter(new MainSliderAdapter());
                slider.setSelectedSlide(0);
            }
        }, 1500);
        slider.setOnSlideClickListener(new OnSlideClickListener() {
            @Override
            public void onSlideClick(int position) {
                //Do what you want
                toastUtil.appSuccessMsg(mContext,"dsadsadasd");
            }
        });

    }

    public void Prevention(View v) {
        /*Intent intent = new Intent(this, PdfMainActivity.class);
        startActivity(intent);*/

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "pdf.pdf");
        intent.putExtra("head","BAF CORONA INFO");
        startActivity(intent);
    }
    public void CoronaState(View v) {
        Log.i("Password",PersistentUser.getUserpassword(mContext));

        if(list.contains(PersistentUser.getUserpassword(mContext))){

            Intent intent = new Intent(getApplicationContext(), CoronaStateActivity.class);
            startActivity(intent);
        }else {
            showCustomDialog();
        }


    }

    public void Help(View v) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void Success(View v) {
        Intent intent = new Intent(this, SuccessStoryActivity.class);
        startActivity(intent);
    }



    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.password_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        TextView btn_cancel = (TextView) dialogView.findViewById(R.id.btn_cancel);
        TextView btn_apply = (TextView) dialogView.findViewById(R.id.btn_apply);
        final EditText filter_head=(EditText)dialogView.findViewById(R.id.et_password);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list.contains(filter_head.getText().toString())){
                    PersistentUser.setUserpassword(mContext,filter_head.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), CoronaStateActivity.class);
                    startActivity(intent);
                    alertDialog.dismiss();

                }else if(filter_head.getText().toString().equalsIgnoreCase("")){
                    toastUtil.appSuccessMsg(mContext,"Please Enter Password");
                } else {
                    toastUtil.appSuccessMsg(mContext,"Sory Password Does not match Try Again");
                }



            }
        });
        alertDialog.show();
    }

}
