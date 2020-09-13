package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.bafcoronainfo.R;


public class WebViewActivity extends Activity {
    private Context mContext;

    private TextView topbar_web;
    private WebView webView;
    ProgressDialog dialog;

    private String sectionString;
    private String titleString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_webview);
        mContext = this;
        topbar_web = (TextView) findViewById(R.id.topbar_web);
        sectionString = getIntent().getStringExtra("section");
        titleString = getIntent().getStringExtra("title");
        topbar_web.setText(titleString);
        webView = (WebView) findViewById(R.id.webview);
        webviewSetting();


    }

    private void webviewSetting() {
        WebSettings settings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(WebViewActivity.this, null,
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
                Toast.makeText(WebViewActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                webView.loadUrl("Musafir Ali");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        settings.setDefaultTextEncodingName("utf-8");

        loadSectionUrl();
    }

    private void loadSectionUrl() {
        if (sectionString.equalsIgnoreCase("1")) {
            webView.loadUrl("file:///android_asset/direction_1.html");
//            topbar.setText(getResources().getString(R.string.corona_direction_head_1));
        } else if (sectionString.equalsIgnoreCase("2")) {
            webView.loadUrl("file:///android_asset/direction_2.html");
//            topbar.setText(getResources().getString(R.string.corona_direction_head_2));
        } else if (sectionString.equalsIgnoreCase("3")) {
            webView.loadUrl("file:///android_asset/direction_3.html");
//            topbar.setText(getResources().getString(R.string.corona_direction_head_3));
        } else if (sectionString.equalsIgnoreCase("4")) {
            webView.loadUrl("file:///android_asset/direction_4.html");
//            topbar.setText(getResources().getString(R.string.corona_direction_head_4));
        } else if (sectionString.equalsIgnoreCase("5")) {
            webView.loadUrl("file:///android_asset/direction_5.html");
//            topbar.setText(getResources().getString(R.string.corona_direction_head_4));
        }else if (sectionString.equalsIgnoreCase("7")) {
            webView.loadUrl("file:///android_asset/direction_7.html");
//            topbar.setText(getResources().getString(R.string.corona_direction_head_4));
        }else if (sectionString.equalsIgnoreCase("8")) {
            webView.loadUrl("file:///android_asset/direction_8.html");
//            topbar.setText(getResources().getString(R.string.corona_direction_head_4));
        }
        if (sectionString.equalsIgnoreCase("10")) {
            webView.loadUrl("file:///android_asset/terms_condition.html");
//            topbar.setText(getResources().getString(R.string.corona_direction_head_4));
        } else if(sectionString.equalsIgnoreCase("11")) {
            webView.loadUrl("file:///android_asset/privacy.html");
//            topbar.setText(getResources().getString(R.string.corona_direction_head_5));
        }
    }

    public void BACK(View v) {
        this.finish();

    }
}
