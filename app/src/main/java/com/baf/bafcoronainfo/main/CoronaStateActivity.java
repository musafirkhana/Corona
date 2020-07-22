package com.baf.bafcoronainfo.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.adapter.MainSliderAdapter;
import com.baf.bafcoronainfo.callbackinterface.ServerResponse;
import com.baf.bafcoronainfo.holder.AllStateHolder;
import com.baf.bafcoronainfo.holder.BasewiseStateHolder;
import com.baf.bafcoronainfo.model.BaseWiselistModel;
import com.baf.bafcoronainfo.model.StatelistModel;
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
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;

import static com.android.volley.VolleyLog.TAG;

public class CoronaStateActivity extends Activity {

    private Context mContext;
    private ToastUtil toastUtil;

    private RadioGroup radioGroup;
    private RadioButton rb_total, rb_today, rb_yesterday;
    List<String> baseList = new ArrayList<String>();
    private String text;
    private String respones_results;
    private String[] base_respones_results;
    private BusyDialog mBusyDialog;

    private TextView tv_present;
    private TextView tv_recover;
    private TextView tv_death;
    private TextView tv_cmh;
    private TextView tv_isolation;
    private TextView tv_quarantine;
    private TextView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_state);
        mContext = this;
        toastUtil = new ToastUtil(this);
        baseList.add("All");
        baseList.add("Bangabandhu");
        baseList.add("BSR");
        baseList.add("PKP");
        baseList.add("MTR");
        baseList.add("ZHR");
        baseList.add("Sheikh Hasina");

        initUI();


    }
    private void initUI() {
        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        rb_total = (RadioButton) findViewById(R.id.rb_total);
        rb_today = (RadioButton) findViewById(R.id.rb_today);
        rb_yesterday = (RadioButton) findViewById(R.id.rb_yesterday);
        rb_total.setChecked(true);

        tv_present=(TextView)findViewById(R.id.tv_present);
        tv_recover=(TextView)findViewById(R.id.tv_recover);
        tv_death=(TextView)findViewById(R.id.tv_death);
        tv_cmh=(TextView)findViewById(R.id.tv_cmh);
        tv_isolation=(TextView)findViewById(R.id.tv_isolation);
        tv_quarantine=(TextView)findViewById(R.id.tv_quarantine);
        topbar=(TextView)findViewById(R.id.topbar);
        loadAssetData("corona_state.txt");
        //setData();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                switch (checkedId) {
                    case R.id.rb_yesterday:
                        loadAssetData("corona_state_yesterday.txt");
                        ServerRequest("0");
                        break;
                    case R.id.rb_total:
                        loadAssetData("corona_state.txt");
                        break;
                    case R.id.rb_today:
                        loadAssetData("corona_state_today.txt");
                        break;
                }

            }

        });





    }

    public void Filter(View v) {
        showCustomDialog();

    }
    public void MapLoad(View v) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);

    }

    public void BACK(View v) {
        this.finish();

    }
    private void setData(){
        for (int i = 0; i < AllStateHolder.getAllStatelist().size(); i++) {
            StatelistModel query = AllStateHolder.getAllStatelist().get(i);
            Log.i("Getting Data",query.getHome_quarantine());
            tv_present.setText(query.getPresent_state());
            tv_recover.setText(query.getRecovered());
            tv_death.setText(query.getDeath());
            tv_cmh.setText(query.getCmh());
            tv_isolation.setText(query.getIsolation());
            tv_quarantine.setText(query.getHome_quarantine());
            topbar.setText("ALL DATA");

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
                AppConstant.BASE=base_spinner.getSelectedItem().toString();
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
                if(AppConstant.BASE.equalsIgnoreCase("ALL")){
                    loadAssetData("corona_state.txt");
                }else {
                    loadAssetBaseData("corona_state_base.txt");
                }


                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }




    /*******************************
     * Load Data From Asset Folder
     ***************/
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

        themeList(text);

    }
    private void themeList(final String url_string) {

        final Thread d = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    respones_results = url_string;
                    if (StatelistParser.connect(getApplicationContext(),
                            respones_results))
                        ;

                } catch (final Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        try {

                            setData();
                        } catch (Exception e) {
                        }
                    }

                });

            }
        });

        d.start();

    }
    private void loadAssetBaseData(String fileName) {
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

            base_respones_results = aurl;
            try {
                if (BaseStatelistParser.connect(getApplicationContext(),aurl[0]));
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
            Log.i("BASE",AppConstant.BASE);
            setBaseData();
        }
    }

    private void setBaseData(){
        for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
            BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
            Log.i("Getting Data",query.getBsr_affected_total());
            if(AppConstant.BASE.equalsIgnoreCase("BSR")){
                tv_present.setText(query.getBsr_affected_total());
                tv_recover.setText(query.getBsr_recovered_total());
                tv_death.setText(query.getBsr_death_total());
                tv_cmh.setText(query.getBsr_cmh_total());
                tv_isolation.setText(query.getBsr_isolation_total());
                tv_quarantine.setText(query.getBsr_home_quarantine_total());
                topbar.setText("BAF BASE "+AppConstant.BASE);
            }else if(AppConstant.BASE.equalsIgnoreCase("Bangabandhu")){
                tv_present.setText(query.getBbd_affected_total());
                tv_recover.setText(query.getBbd_recovered_total());
                tv_death.setText(query.getBbd_death_total());
                tv_cmh.setText(query.getBbd_cmh_total());
                tv_isolation.setText(query.getBbd_isolation_total());
                tv_quarantine.setText(query.getBbd_home_quarantine_total());
                topbar.setText("BAF BASE "+AppConstant.BASE);

            }else if(AppConstant.BASE.equalsIgnoreCase("PKP")){
                tv_present.setText(query.getPkp_affected_total());
                tv_recover.setText(query.getPkp_recovered_total());
                tv_death.setText(query.getPkp_death_total());
                tv_cmh.setText(query.getPkp_cmh_total());
                tv_isolation.setText(query.getPkp_isolation_total());
                tv_quarantine.setText(query.getPkp_home_quarantine_total());
                topbar.setText("BAF BASE "+AppConstant.BASE);

            }else if(AppConstant.BASE.equalsIgnoreCase("MTR")){
                tv_present.setText(query.getMtr_affected_total());
                tv_recover.setText(query.getMtr_recovered_total());
                tv_death.setText(query.getMtr_death_total());
                tv_cmh.setText(query.getMtr_cmh_total());
                tv_isolation.setText(query.getMtr_isolation_total());
                tv_quarantine.setText(query.getMtr_home_quarantine_total());
                topbar.setText("BAF BASE "+AppConstant.BASE);

            }else if(AppConstant.BASE.equalsIgnoreCase("ZHR")){
                tv_present.setText(query.getZhr_affected_total());
                tv_recover.setText(query.getZhr_recovered_total());
                tv_death.setText(query.getZhr_death_total());
                tv_cmh.setText(query.getZhr_cmh_total());
                tv_isolation.setText(query.getZhr_isolation_total());
                tv_quarantine.setText(query.getZhr_home_quarantine_total());
                topbar.setText("BAF BASE "+AppConstant.BASE);

            }else if(AppConstant.BASE.equalsIgnoreCase("Sheikh Hasina")){
                tv_present.setText(query.getCxb_affected_total());
                tv_recover.setText(query.getCxb_recovered_total());
                tv_death.setText(query.getCxb_death_total());
                tv_cmh.setText(query.getCxb_cmh_total());
                tv_isolation.setText(query.getCxb_isolation_total());
                tv_quarantine.setText(query.getCxb_home_quarantine_total());
                topbar.setText("BAF BASE "+AppConstant.BASE);

            }


        }
    }



    private void ServerRequest(final String limit) {
        if (!Helpers.isNetworkAvailable(mContext)) {
            Helpers.showOkayDialog(mContext, R.string.no_internet_connection);
            return;
        }
        mBusyDialog = new BusyDialog(mContext);
        mBusyDialog.show();

        final String url = AllUrls.BASEURL;

        Logger.debugLog("Api BaseUrl", url);
        ServerCallsProvider.volleyGetRequest(url, TAG, new ServerResponse() {
            @Override
            public void onSuccess(String statusCode, String responseServer) {
                try {
                    mBusyDialog.dismis();
                    Logger.debugLog("Api responseServer", responseServer);
                    JSONObject mJsonObject = new JSONObject(responseServer);
                    if (mJsonObject.getBoolean("success")) {

                        JSONArray jsonArray = mJsonObject.getJSONArray("data");
                        GsonBuilder builder = new GsonBuilder();
                        Gson mGson = builder.create();
                        /*List<FeedList> posts = new ArrayList<FeedList>();
                        posts = Arrays.asList(mGson.fromJson(jsonArray.toString(), FeedList[].class));
                        ArrayList<FeedList> allLists = new ArrayList<FeedList>(posts);
                        mMyPostListAdapter.addAllList(allLists);*/


                    }
                } catch (Exception e) {
                    Logger.debugLog("Exception", e.getMessage());

                }
            }

            @Override
            public void onFailed(String statusCode, String serverResponse) {
                mBusyDialog.dismis();
                /*if (statusCode.equalsIgnoreCase("404")) {
                    PersistentUser.resetAllData(mContext);
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }*/
            }
        });
    }



}
