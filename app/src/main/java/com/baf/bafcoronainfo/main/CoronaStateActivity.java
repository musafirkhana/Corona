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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.callbackinterface.ServerResponse;
import com.baf.bafcoronainfo.holder.AllStateHolder;
import com.baf.bafcoronainfo.holder.BasewiseStateHolder;
import com.baf.bafcoronainfo.model.BaseWiselistModel;
import com.baf.bafcoronainfo.model.StatelistModel;
import com.baf.bafcoronainfo.networkcalls.ServerCallsProvider;
import com.baf.bafcoronainfo.parser.BaseStatelistParser;
import com.baf.bafcoronainfo.parser.TodayStatelistParser;
import com.baf.bafcoronainfo.util.AllUrls;
import com.baf.bafcoronainfo.util.AppConstant;
import com.baf.bafcoronainfo.util.BusyDialog;
import com.baf.bafcoronainfo.util.Helpers;
import com.baf.bafcoronainfo.util.Logger;
import com.baf.bafcoronainfo.util.PersistentUser;
import com.baf.bafcoronainfo.util.ToastUtil;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static com.android.volley.VolleyLog.TAG;

public class CoronaStateActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

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
    private TextView tv_tested_total;
    private RelativeLayout re_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_state);
        mContext = this;
        toastUtil = new ToastUtil(this);
        baseList.add("ALL BASES");
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
        re_filter=(RelativeLayout)findViewById(R.id.re_filter);

        tv_present=(TextView)findViewById(R.id.tv_present);
        tv_recover=(TextView)findViewById(R.id.tv_recover);
        tv_death=(TextView)findViewById(R.id.tv_death);
        tv_cmh=(TextView)findViewById(R.id.tv_cmh);
        tv_isolation=(TextView)findViewById(R.id.tv_isolation);
        tv_quarantine=(TextView)findViewById(R.id.tv_quarantine);
        topbar=(TextView)findViewById(R.id.topbar);
        tv_tested_total=(TextView)findViewById(R.id.tv_tested_total);

        re_filter.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        rb_total.setChecked(true);


        //ServerRequest("basewisetotal");

    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_yesterday:
                    AppConstant.BASE="ALL BASES";
                    AllUrls.API_KEY=AllUrls.YESTREDAY_KEY;
                    ServerRequest(AllUrls.YESTREDAY_KEY);
                    break;
                case R.id.rb_total:
                    AppConstant.BASE="ALL BASES";
                    AllUrls.API_KEY=AllUrls.BASE_WISE_KEY;
                    ServerRequest(AllUrls.BASE_WISE_KEY);
                    break;
                case R.id.rb_today:
                    AppConstant.BASE="ALL BASES";
                    AllUrls.API_KEY=AllUrls.TODAY_KEY;
                    ServerRequest(AllUrls.TODAY_KEY);
                    break;
            }

        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_filter:
                showCustomDialog();
                break;
    }
    }

    /*public void MapLoad(View v) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);

    }*/
    public void Logout(View view){
        PersistentUser.setUserpassword(mContext,"");
        this.finish();
    }

    public void BACK(View v) {
        this.finish();

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
                Log.i("Spinner Chk",AppConstant.BASE);
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
                setBaseData();

                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }





    private void setBaseData(){

            Log.i("Getting Data",topbar.getText().toString());
            if(AppConstant.BASE.equalsIgnoreCase("BSR")){
                for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                    BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                    tv_present.setText(query.getBsr_affected_total());
                    tv_recover.setText(query.getBsr_recovered_total());
                    tv_death.setText(query.getBsr_death_total());
                    tv_cmh.setText(query.getBsr_cmh_total());
                    tv_isolation.setText(query.getBsr_isolation_total());
                    tv_quarantine.setText(query.getBsr_home_quarantine_total());
                    tv_tested_total.setText(query.getBsr_tested_total());
                    topbar.setText("BAF BASE " + AppConstant.BASE);
                }
            }else if(AppConstant.BASE.equalsIgnoreCase("Bangabandhu")){


                for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                    BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                    tv_present.setText(query.getBbd_affected_total());
                    tv_recover.setText(query.getBbd_recovered_total());
                    tv_death.setText(query.getBbd_death_total());
                    tv_cmh.setText(query.getBbd_cmh_total());
                    tv_isolation.setText(query.getBbd_isolation_total());
                    tv_quarantine.setText(query.getBbd_home_quarantine_total());
                    tv_tested_total.setText(query.getBbd_tested_total());
                    topbar.setText("BAF BASE "+AppConstant.BASE);
                }

            }else if(AppConstant.BASE.equalsIgnoreCase("PKP")){

                for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                    BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                    tv_present.setText(query.getPkp_affected_total());
                    tv_recover.setText(query.getPkp_recovered_total());
                    tv_death.setText(query.getPkp_death_total());
                    tv_cmh.setText(query.getPkp_cmh_total());
                    tv_isolation.setText(query.getPkp_isolation_total());
                    tv_quarantine.setText(query.getPkp_home_quarantine_total());
                    tv_tested_total.setText(query.getPkp_tested_total());
                    topbar.setText("BAF BASE "+AppConstant.BASE);
                }

            }else if(AppConstant.BASE.equalsIgnoreCase("MTR")){

                for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                    BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                    tv_present.setText(query.getMtr_affected_total());
                    tv_recover.setText(query.getMtr_recovered_total());
                    tv_death.setText(query.getMtr_death_total());
                    tv_cmh.setText(query.getMtr_cmh_total());
                    tv_isolation.setText(query.getMtr_isolation_total());
                    tv_quarantine.setText(query.getMtr_home_quarantine_total());
                    tv_tested_total.setText(query.getMtr_tested_total());
                    topbar.setText("BAF BASE "+AppConstant.BASE);
                }


            }else if(AppConstant.BASE.equalsIgnoreCase("ZHR")){


                for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                    BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                    tv_present.setText(query.getZhr_affected_total());
                    tv_recover.setText(query.getZhr_recovered_total());
                    tv_death.setText(query.getZhr_death_total());
                    tv_cmh.setText(query.getZhr_cmh_total());
                    tv_isolation.setText(query.getZhr_isolation_total());
                    tv_quarantine.setText(query.getZhr_home_quarantine_total());
                    tv_tested_total.setText(query.getZhr_tested_total());
                    topbar.setText("BAF BASE "+AppConstant.BASE);
                }

            }else if(AppConstant.BASE.equalsIgnoreCase("Sheikh Hasina")){


                for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                    BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                    tv_present.setText(query.getCxb_affected_total());
                    tv_recover.setText(query.getCxb_recovered_total());
                    tv_death.setText(query.getCxb_death_total());
                    tv_cmh.setText(query.getCxb_cmh_total());
                    tv_isolation.setText(query.getCxb_isolation_total());
                    tv_quarantine.setText(query.getCxb_home_quarantine_total());
                    tv_tested_total.setText(query.getCxb_tested_total());
                    topbar.setText("BAF BASE "+AppConstant.BASE);
                }

            }else if(AppConstant.BASE.equalsIgnoreCase("ALL BASES")){


                for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                    BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                    tv_present.setText(query.getTotal_affected());
                    tv_recover.setText(query.getTotal_recovered());
                    tv_death.setText(query.getTotal_death());
                    tv_cmh.setText(query.getTotal_cmh());
                    tv_isolation.setText(query.getTotal_isolation());
                    tv_quarantine.setText(query.getTotal_home_quarantine());
                    tv_tested_total.setText(query.getTotal_tested());
                    topbar.setText("ALL BASES ");
                    Log.i("Getting Data",BasewiseStateHolder.getBaseStatelist().get(1).getBsr_affected_total());
                }
            }



    }

    //Get Total and Basewise Data from Server
    private void ServerRequest(final String apiName) {
        if (!Helpers.isNetworkAvailable(mContext)) {
            Helpers.showOkayDialog(mContext, R.string.no_internet_connection);
            return;
        }
        mBusyDialog = new BusyDialog(mContext);
        mBusyDialog.show();

        final String url = AllUrls.BASE_URL+apiName;

        Logger.debugLog("Api BaseUrl", url);
        ServerCallsProvider.volleyGetRequest(url, TAG, new ServerResponse() {
            @Override
            public void onSuccess(String statusCode, String responseServer) {
                try {
                    mBusyDialog.dismis();

                    try {
                        if(apiName.equalsIgnoreCase(AllUrls.BASE_WISE_KEY)){
                            if (BaseStatelistParser.connect(getApplicationContext(), responseServer));

                            setBaseData();
                        }else {
                            if (TodayStatelistParser.connect(getApplicationContext(), responseServer));

                            setBaseData();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } catch (Exception e) {
                    Logger.debugLog("Exception", e.getMessage());

                }
            }

            @Override
            public void onFailed(String statusCode, String serverResponse) {
                mBusyDialog.dismis();


            }
        });
    }



}
