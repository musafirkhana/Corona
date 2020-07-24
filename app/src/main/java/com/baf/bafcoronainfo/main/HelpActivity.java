package com.baf.bafcoronainfo.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.holder.BasewiseStateHolder;
import com.baf.bafcoronainfo.holder.HelpHolder;
import com.baf.bafcoronainfo.model.BaseWiselistModel;
import com.baf.bafcoronainfo.model.HelpModel;
import com.baf.bafcoronainfo.parser.BaseStatelistParser;
import com.baf.bafcoronainfo.parser.HelplistParser;
import com.baf.bafcoronainfo.util.AppConstant;
import com.baf.bafcoronainfo.util.ToastUtil;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class HelpActivity extends Activity implements View.OnClickListener {
    private Context mContext;
    List<String> baseList = new ArrayList<String>();

    private String text;
    private String[] respones_results;

    private TextView contact_1tv;
    private TextView contact_2tv;
    private TextView contact_3tv;
    private TextView contact_4tv;
    private TextView topbar;

    private RelativeLayout call_1;
    private RelativeLayout call_2;
    private RelativeLayout call_3;
    private RelativeLayout call_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_help);
        mContext = this;
        baseList.add("Bangabandhu");
        baseList.add("BSR");
        baseList.add("PKP");
        baseList.add("MTR");
        baseList.add("ZHR");
        baseList.add("Sheikh Hasina");
        initUI();
        loadAssetData("help.txt");

    }

    private void initUI() {
        contact_1tv = (TextView) findViewById(R.id.contact_1tv);
        contact_2tv = (TextView) findViewById(R.id.contact_2tv);
        contact_3tv = (TextView) findViewById(R.id.contact_3tv);
        contact_4tv = (TextView) findViewById(R.id.contact_4tv);
        topbar = (TextView) findViewById(R.id.topbar);

        call_1 = (RelativeLayout) findViewById(R.id.call_1);
        call_2 = (RelativeLayout) findViewById(R.id.call_2);
        call_3 = (RelativeLayout) findViewById(R.id.call_3);
        call_4 = (RelativeLayout) findViewById(R.id.call_4);

        call_1.setOnClickListener(this);
        call_2.setOnClickListener(this);
        call_3.setOnClickListener(this);
        call_4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_1:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" +contact_1tv.getText().toString()));
                startActivity(intent);
                break;
            case R.id.call_2:
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:" +contact_2tv.getText().toString()));
                startActivity(intent2);
                break;
            case R.id.call_3:
                Intent intent3 = new Intent(Intent.ACTION_DIAL);
                intent3.setData(Uri.parse("tel:" +contact_3tv.getText().toString()));
                startActivity(intent3);
                break;
            case R.id.call_4:
                Intent intent4 = new Intent(Intent.ACTION_DIAL);
                intent4.setData(Uri.parse("tel:" +contact_4tv.getText().toString()));
                startActivity(intent4);
                break;

        }

    }

    private void showCustomDialog() {
        // String downloadFileSize= FileUtils.getFileSize(downloadUrl);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        Button btn_cancel = (Button) dialogView.findViewById(R.id.btn_cancel);
        Button btn_apply = (Button) dialogView.findViewById(R.id.btn_apply);
        TextView tv_base=(TextView)dialogView.findViewById(R.id.tv_base);
        TextView filter_head=(TextView)dialogView.findViewById(R.id.filter_head);
        final Spinner base_spinner = (Spinner) dialogView.findViewById(R.id.base_spinner);




        ArrayAdapter daynightspinner = new ArrayAdapter(this, R.layout.salutation, baseList);
        daynightspinner.setDropDownViewResource(R.layout.salutation);
        base_spinner.setAdapter(daynightspinner);



        base_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AppConstant.HELP_BASE=base_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //text_download.setText(Appconstant.SURA_NAME + " " + getResources().getText(R.string.download_text));
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    loadAssetData("help.txt");

                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    public void BaseFilter(View v) {
        showCustomDialog();

    }
    public void BACK(View v) {
        this.finish();

    }

    private void loadAssetData(String fileName) {
        try {
            InputStream is = getAssets().open(fileName);

            // We guarantee that the available method returns the total
            // size of the asset... of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            text = new String(buffer);
            Log.i("Hello ", fileName+text);

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }

        new LoadAsyncTask().execute(text);

    }



    /******************************
     * Load Data From Asset Folder
     ********************************/

    public class LoadAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... aurl) {

            respones_results = aurl;
            try {
                if (HelplistParser.connect(getApplicationContext(),aurl[0]));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
        }
        @SuppressLint("NewApi")
        protected void onPostExecute(String getResult) {
            //progDialogConfirm.dismiss();
            setData();
        }
    }


    private void setData(){
        for (int i = 0; i < HelpHolder.getHelplist().size(); i++) {
            HelpModel query = HelpHolder.getHelplist().get(i);
            Log.i("Getting Data",query.getBsr_smo());
            if(AppConstant.HELP_BASE.equalsIgnoreCase("BSR")){
                contact_1tv.setText(query.getBsr_smo());
                contact_2tv.setText(query.getBsr_miroom());
                contact_3tv.setText(query.getBsr_cmh());
                contact_4tv.setText(query.getBsr_ambuloance());
                topbar.setText("BAF BASE "+AppConstant.HELP_BASE);
            }else if(AppConstant.HELP_BASE.equalsIgnoreCase("Bangabandhu")){
                contact_1tv.setText(query.getBbd_smo());
                contact_2tv.setText(query.getBbd_miroom());
                contact_3tv.setText(query.getBbd_cmh());
                contact_4tv.setText(query.getBbd_ambuloance());
                topbar.setText("BAF BASE "+AppConstant.HELP_BASE);

            }else if(AppConstant.HELP_BASE.equalsIgnoreCase("PKP")){
                contact_1tv.setText(query.getPkp_smo());
                contact_2tv.setText(query.getPkp_miroom());
                contact_3tv.setText(query.getPkp_cmh());
                contact_4tv.setText(query.getPkp_ambuloance());
                topbar.setText("BAF BASE "+AppConstant.HELP_BASE);
            }else if(AppConstant.HELP_BASE.equalsIgnoreCase("MTR")){
                contact_1tv.setText(query.getMtr_smo());
                contact_2tv.setText(query.getMtr_miroom());
                contact_3tv.setText(query.getMtr_cmh());
                contact_4tv.setText(query.getMtr_ambuloance());
                topbar.setText("BAF BASE "+AppConstant.HELP_BASE);

            }else if(AppConstant.HELP_BASE.equalsIgnoreCase("ZHR")){
                contact_1tv.setText(query.getZhr_smo());
                contact_2tv.setText(query.getZhr_miroom());
                contact_3tv.setText(query.getZhr_cmh());
                contact_4tv.setText(query.getZhr_ambuloance());
                topbar.setText("BAF BASE "+AppConstant.HELP_BASE);

            }else if(AppConstant.HELP_BASE.equalsIgnoreCase("Sheikh Hasina")){
                contact_1tv.setText(query.getCkb_smo());
                contact_2tv.setText(query.getCkb_miroom());
                contact_3tv.setText(query.getCkb_cmh());
                contact_4tv.setText(query.getCkb_ambuloance());
                topbar.setText("BAF BASE "+AppConstant.HELP_BASE);

            }


        }
    }

}
