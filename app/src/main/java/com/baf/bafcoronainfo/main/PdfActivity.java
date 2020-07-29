package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baf.bafcoronainfo.R;



public class PdfActivity extends Activity  {
    private Context mContext;
    private static final String TAG = PdfActivity.class.getSimpleName();


    Integer pageNumber = 0;
    private TextView tv_directories;
    private TextView topbar;
    private String section_name;
    private String headName;

    String pdfFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pdfview);
        mContext = this;

        tv_directories = (TextView) findViewById(R.id.tv_directories);
        topbar=(TextView)findViewById(R.id.topbar);

        section_name = getIntent().getStringExtra("section");
        if(section_name.equalsIgnoreCase("1")){
            tv_directories.setText(getResources().getString(R.string.corona_direction_body_1));
        }else if(section_name.equalsIgnoreCase("2")){
            tv_directories.setText(getResources().getString(R.string.corona_direction_body_2));
        }else if(section_name.equalsIgnoreCase("3")){
            tv_directories.setText(getResources().getString(R.string.corona_direction_body_3));
        }else if(section_name.equalsIgnoreCase("4")){
            tv_directories.setText(getResources().getString(R.string.corona_direction_body_4));
        }else {
            tv_directories.setText(getResources().getString(R.string.corona_direction_body_5));
        }


    }


    public void BACK(View v) {
        this.finish();

    }
}
