package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.adapter.EmailAdapter;
import com.baf.bafcoronainfo.model.Item;

import java.util.ArrayList;


public class MailWebViewActivity extends Activity {
    private Context mContext;

    private TextView topbar_web;
    private WebView webView;
    ProgressDialog dialog;

    ArrayList<Item> emailList=new ArrayList<>();
    private ListView email_listview;
    private String titleString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mail_webview);
        mContext = this;
        getEmail();
        topbar_web = (TextView) findViewById(R.id.topbar_web);
        titleString = getIntent().getStringExtra("title");
        topbar_web.setText(titleString);
        webView = (WebView) findViewById(R.id.webview);
        webviewSetting();
        email_listview = (ListView) findViewById(R.id.email_listview);


        EmailAdapter myAdapter=new EmailAdapter(mContext,emailList);
        email_listview.setAdapter(myAdapter);
//        setListViewHeight(email_listview);
//        myAdapter.notifyDataSetChanged();


    }
    public void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        //listAdapter.getCount() returns the number of data items
        for (int i = 0,len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // listView.getDividerHeight() gets the height occupied by the separator between children
        // params.height finally gets the height of the entire ListView full display
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *  (listAdapter .getCount() - 1));
        listView.setLayoutParams(params);
    }
    private void webviewSetting() {
        WebSettings settings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(MailWebViewActivity.this, null,
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
                Toast.makeText(MailWebViewActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                webView.loadUrl("Musafir Ali");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        settings.setDefaultTextEncodingName("utf-8");

        loadSectionUrl();
    }

    private void loadSectionUrl() {
            webView.loadUrl("file:///android_asset/direction_6.html");

    }
    private void getEmail(){
        emailList.add(new Item("Armed Forces Institute of Pathology, Dhaka","afipdhaka@yahoo.com"));
        emailList.add(new Item("CMH Barishal","cmhbarishal@army.mil.bd"));
        emailList.add(new Item("CMH Savar","cmhsavar909@gmail.com"));
        emailList.add(new Item("CMH Ramu","cmhramu10div@gmail.com"));
        emailList.add(new Item("CMH Bogura","cmhbogra@gmail.com"));
        emailList.add(new Item("CMH Sylhet","cmhjalalabad@army.mil.bd"));
        emailList.add(new Item("CMH Ghatail","comdtcmhghatail@army.mil.bd"));
        emailList.add(new Item("CMH Chattogram","comdtcmhctg2015@gmail.com"));
        emailList.add(new Item("CMH Cumilla","cmhcomillaabr@gmail.com"));
        emailList.add(new Item("CMH Jessore","cmhjessore@gmail.com"));
        emailList.add(new Item("CMH Rangpur","cmhrnp@66infdiv.com"));
        emailList.add(new Item("CMH Momenshahi","cmhmomenshahi@yahoo.com"));
        emailList.add(new Item("CMH Saidpur","saidpurcmh@gmail.com"));
        emailList.add(new Item("BNS Patenga, Chattogram","patenga@navy.mil.bd"));
        emailList.add(new Item("BNS Upasham, Khulna","upasham@navy.mil.bd"));
    }

    public void BACK(View v) {
        this.finish();

    }
}
