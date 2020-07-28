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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


public class SuccessStoryActivity extends Activity  {
    private Context mContext;

    private TextView header;
    private WebView webView;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_story);
        mContext = this;
        webView = (WebView) findViewById(R.id.webview);
        webviewSetting();


    }
    private void webviewSetting() {
        WebSettings settings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(SuccessStoryActivity.this, null,
                        "PLease wait .....");
                dialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }
            // This method will be triggered when the Page loading is completed
            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                super.onPageFinished(view, url);
            }
            // This method will be triggered when error page appear
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(SuccessStoryActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                webView.loadUrl("Musafir Ali");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        settings.setDefaultTextEncodingName("utf-8");

//        webView.loadDataWithBaseURL(null, getResources().getString(R.string.corona_direction_body_1), "text/html", "UTF-8", null);
//        webView.loadUrl("file:///android_res/raw/test.html");
			webView.loadUrl("https://skyapi.website/allpost");


    }

    public void BACK(View v) {
        this.finish();

    }
}
