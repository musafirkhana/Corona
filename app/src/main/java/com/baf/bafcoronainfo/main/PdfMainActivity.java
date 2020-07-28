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

import org.w3c.dom.Text;


public class PdfMainActivity extends Activity {
    Typeface tf;
    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pdf_main);
        mContext = this;

    }

    public void PDF1(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "pdf_5.pdf");
        intent.putExtra("head","BAF CORONA INFO");
        startActivity(intent);
    }

    public void PDF2(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "pdf_5.pdf");
        intent.putExtra("head","BAF CORONA INFO");
        startActivity(intent);
    }

    public void PDF3(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "pdf_5.pdf");
        intent.putExtra("head","BAF CORONA INFO");
        startActivity(intent);
    }

    public void PDF4(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "pdf_5.pdf");
        intent.putExtra("head","BAF CORONA INFO");
        startActivity(intent);
    }




    public void BACK(View v) {
        this.finish();

    }

}
