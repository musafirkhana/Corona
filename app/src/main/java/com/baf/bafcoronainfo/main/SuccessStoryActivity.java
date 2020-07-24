package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.util.JustifiedTextView;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;


public class SuccessStoryActivity extends Activity  {
    private Context mContext;

    private JustifiedTextView success_story;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_story);
        mContext = this;
        success_story=(JustifiedTextView)findViewById(R.id.success_story);
        success_story.setText(R.string.success_story);


    }


    public void BACK(View v) {
        this.finish();

    }
}
