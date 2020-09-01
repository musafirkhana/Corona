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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.callbackinterface.ServerResponse;
import com.baf.bafcoronainfo.holder.BasewiseStateHolder;
import com.baf.bafcoronainfo.model.BaseWiselistModel;
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
import com.google.android.material.card.MaterialCardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import static com.android.volley.VolleyLog.TAG;

public class CoronaStateActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Context mContext;
    private ToastUtil toastUtil;


    private RadioGroup radioGroup;
    private RadioButton rb_total, rb_today, rb_yesterday, rb_country;
    List<String> baseList = new ArrayList<String>();
    private String text;
    private String respones_results;
    private String[] base_respones_results;
    private BusyDialog mBusyDialog;

    private TextView tv_present, tv_recover, tv_death,
            tv_cmh, tv_isolation, tv_quarantine;
    private TextView topbar;
    private TextView tv_tested_total;
    private RelativeLayout re_filter;
    private TextView tv_updatetime;

    private MaterialCardView card_corfirmed, card_recover, card_death,
            card_cmh, card_isolation, card_homeq;


    private LinearLayout country_li;
    private LinearLayout li_baf;

    private TextView country_conf_today, country_conf_total, country_recover_today, country_recover_total,
            country_death_today, country_death_total, country_test_total, country_test_rate;

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
        country_li = (LinearLayout) findViewById(R.id.country_li);
        li_baf = (LinearLayout) findViewById(R.id.li_baf);

        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        rb_total = (RadioButton) findViewById(R.id.rb_total);
        rb_today = (RadioButton) findViewById(R.id.rb_today);
        rb_yesterday = (RadioButton) findViewById(R.id.rb_yesterday);
        rb_country = (RadioButton) findViewById(R.id.rb_country);
        re_filter = (RelativeLayout) findViewById(R.id.re_filter);

        tv_present = (TextView) findViewById(R.id.tv_present);
        tv_recover = (TextView) findViewById(R.id.tv_recover);
        tv_death = (TextView) findViewById(R.id.tv_death);
        tv_cmh = (TextView) findViewById(R.id.tv_cmh);
        tv_isolation = (TextView) findViewById(R.id.tv_isolation);
        tv_quarantine = (TextView) findViewById(R.id.tv_quarantine);
        topbar = (TextView) findViewById(R.id.topbar);
        tv_tested_total = (TextView) findViewById(R.id.tv_tested_total);
        tv_updatetime = (TextView) findViewById(R.id.tv_updatetime);

        card_corfirmed = (MaterialCardView) findViewById(R.id.card_corfirmed);
        card_recover = (MaterialCardView) findViewById(R.id.card_recover);
        card_death = (MaterialCardView) findViewById(R.id.card_death);
        card_cmh = (MaterialCardView) findViewById(R.id.card_cmh);
        card_isolation = (MaterialCardView) findViewById(R.id.card_isolation);
        card_homeq = (MaterialCardView) findViewById(R.id.card_homeq);


        re_filter.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        rb_total.setChecked(true);
        card_corfirmed.setOnClickListener(this);
        card_recover.setOnClickListener(this);
        card_death.setOnClickListener(this);
        card_cmh.setOnClickListener(this);
        card_isolation.setOnClickListener(this);
        card_homeq.setOnClickListener(this);


        //ServerRequest("basewisetotal");
        countryUI();
    }

    private void countryUI() {
        country_conf_today = (TextView) findViewById(R.id.country_conf_today);
        country_conf_total = (TextView) findViewById(R.id.country_conf_total);
        country_recover_today = (TextView) findViewById(R.id.country_recover_today);
        country_recover_total = (TextView) findViewById(R.id.country_recover_total);
        country_death_today = (TextView) findViewById(R.id.country_death_today);
        country_death_total = (TextView) findViewById(R.id.country_death_total);
        country_test_total = (TextView) findViewById(R.id.country_test_total);
        country_test_rate = (TextView) findViewById(R.id.country_test_rate);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_yesterday:
                country_li.setVisibility(View.GONE);
                li_baf.setVisibility(View.VISIBLE);
                //AppConstant.BASE="ALL BASES";
                AppConstant.SELECTION = "2";
                AllUrls.API_KEY = AllUrls.YESTREDAY_KEY;
                ServerRequest(AllUrls.YESTREDAY_KEY);
                break;
            case R.id.rb_total:
                country_li.setVisibility(View.GONE);
                li_baf.setVisibility(View.VISIBLE);
                AppConstant.SELECTION = "0";
                //AppConstant.BASE="ALL BASES";
                AllUrls.API_KEY = AllUrls.BASE_WISE_KEY;
                ServerRequest(AllUrls.BASE_WISE_KEY);
                break;
            case R.id.rb_today:
                country_li.setVisibility(View.GONE);
                li_baf.setVisibility(View.VISIBLE);
                AppConstant.SELECTION = "1";
                //  AppConstant.BASE="ALL BASES";
                AllUrls.API_KEY = AllUrls.TODAY_KEY;
                ServerRequest(AllUrls.TODAY_KEY);
                break;

            case R.id.rb_country:
                li_baf.setVisibility(View.GONE);
                country_li.setVisibility(View.VISIBLE);
                countryServerRequest(AllUrls.COUNTRY_API);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_filter:
                showCustomDialog();
                break;
            case R.id.card_corfirmed:
                Intent intent = new Intent(this, StateDetailActivity.class);
                intent.putExtra("section", "CONFIRMED");
                startActivity(intent);
                break;
            case R.id.card_recover:
                Intent intentRecover = new Intent(this, StateDetailActivity.class);
                intentRecover.putExtra("section", "RECOVERED");
                startActivity(intentRecover);
                break;
            case R.id.card_death:
                Intent intentDeath = new Intent(this, StateDetailActivity.class);
                intentDeath.putExtra("section", "DEATH");
                startActivity(intentDeath);
                break;
            case R.id.card_cmh:
                Intent intentCmh = new Intent(this, StateDetailActivity.class);
                intentCmh.putExtra("section", "CMH");
                startActivity(intentCmh);
                break;
            case R.id.card_isolation:
                Intent intentIsolation = new Intent(this, StateDetailActivity.class);
                intentIsolation.putExtra("section", "ISOLATION");
                startActivity(intentIsolation);
                break;
            case R.id.card_homeq:
                Intent intentH = new Intent(this, StateDetailActivity.class);
                intentH.putExtra("section", "HOME QUARANTINE");
                startActivity(intentH);
                break;

        }
    }


    public void Logout(View view) {
        PersistentUser.setUserpassword(mContext, "");
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
        TextView filter_head = (TextView) dialogView.findViewById(R.id.filter_head);
        final Spinner base_spinner = (Spinner) dialogView.findViewById(R.id.base_spinner);


        ArrayAdapter daynightspinner = new ArrayAdapter(this, R.layout.salutation, baseList);
        daynightspinner.setDropDownViewResource(R.layout.salutation);
        base_spinner.setAdapter(daynightspinner);


        base_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AppConstant.BASE = base_spinner.getSelectedItem().toString();

                Log.i("Spinner Chk", AppConstant.BASE);
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
                if (AppConstant.SELECTION.equalsIgnoreCase("0")) {
                    setTotalData();
                } else {
                    setBaseData();
                }


                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    private void setBaseData() {

        Log.i("Getting Data", topbar.getText().toString());


        if (AppConstant.BASE.equalsIgnoreCase("BSR")) {
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
                tv_updatetime.setText(query.getUpdated_at());
            }
        } else if (AppConstant.BASE.equalsIgnoreCase("Bangabandhu")) {


            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                tv_present.setText(query.getBbd_affected_total());
                tv_recover.setText(query.getBbd_recovered_total());
                tv_death.setText(query.getBbd_death_total());
                tv_cmh.setText(query.getBbd_cmh_total());
                tv_isolation.setText(query.getBbd_isolation_total());
                tv_quarantine.setText(query.getBbd_home_quarantine_total());
                tv_tested_total.setText(query.getBbd_tested_total());
                topbar.setText("BAF BASE " + AppConstant.BASE);
                tv_updatetime.setText(query.getUpdated_at());
            }

        } else if (AppConstant.BASE.equalsIgnoreCase("PKP")) {

            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                tv_present.setText(query.getPkp_affected_total());
                tv_recover.setText(query.getPkp_recovered_total());
                tv_death.setText(query.getPkp_death_total());
                tv_cmh.setText(query.getPkp_cmh_total());
                tv_isolation.setText(query.getPkp_isolation_total());
                tv_quarantine.setText(query.getPkp_home_quarantine_total());
                tv_tested_total.setText(query.getPkp_tested_total());
                topbar.setText("BAF BASE " + AppConstant.BASE);
                tv_updatetime.setText(query.getUpdated_at());
            }

        } else if (AppConstant.BASE.equalsIgnoreCase("MTR")) {

            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                tv_present.setText(query.getMtr_affected_total());
                tv_recover.setText(query.getMtr_recovered_total());
                tv_death.setText(query.getMtr_death_total());
                tv_cmh.setText(query.getMtr_cmh_total());
                tv_isolation.setText(query.getMtr_isolation_total());
                tv_quarantine.setText(query.getMtr_home_quarantine_total());
                tv_tested_total.setText(query.getMtr_tested_total());
                topbar.setText("BAF BASE " + AppConstant.BASE);
                tv_updatetime.setText(query.getUpdated_at());
            }


        } else if (AppConstant.BASE.equalsIgnoreCase("ZHR")) {


            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                tv_present.setText(query.getZhr_affected_total());
                tv_recover.setText(query.getZhr_recovered_total());
                tv_death.setText(query.getZhr_death_total());
                tv_cmh.setText(query.getZhr_cmh_total());
                tv_isolation.setText(query.getZhr_isolation_total());
                tv_quarantine.setText(query.getZhr_home_quarantine_total());
                tv_tested_total.setText(query.getZhr_tested_total());
                topbar.setText("BAF BASE " + AppConstant.BASE);
                tv_updatetime.setText(query.getUpdated_at());
            }

        } else if (AppConstant.BASE.equalsIgnoreCase("Sheikh Hasina")) {


            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                tv_present.setText(query.getCxb_affected_total());
                tv_recover.setText(query.getCxb_recovered_total());
                tv_death.setText(query.getCxb_death_total());
                tv_cmh.setText(query.getCxb_cmh_total());
                tv_isolation.setText(query.getCxb_isolation_total());
                tv_quarantine.setText(query.getCxb_home_quarantine_total());
                tv_tested_total.setText(query.getCxb_tested_total());
                topbar.setText("BAF BASE " + AppConstant.BASE);
                tv_updatetime.setText(query.getUpdated_at());
            }

        } else if (AppConstant.BASE.equalsIgnoreCase("ALL BASES")) {


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
                tv_updatetime.setText(query.getUpdated_at());
                Log.i("Getting Data", BasewiseStateHolder.getBaseStatelist().get(1).getBsr_affected_total());
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

        final String url = AllUrls.BASE_URL + apiName;

        Logger.debugLog("Api BaseUrl", url);
        ServerCallsProvider.volleyGetRequest(url, TAG, new ServerResponse() {
            @Override
            public void onSuccess(String statusCode, String responseServer) {
                try {
                    mBusyDialog.dismis();

                    try {
                        if (apiName.equalsIgnoreCase(AllUrls.BASE_WISE_KEY)) {
                            if (BaseStatelistParser.connect(getApplicationContext(), responseServer))
                                ;
                            setTotalData();
                        } else {
                            if (TodayStatelistParser.connect(getApplicationContext(), responseServer))
                                ;
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

    //Get Total and Basewise Data from Server
    private void countryServerRequest(final String apiName) {
        if (!Helpers.isNetworkAvailable(mContext)) {
            Helpers.showOkayDialog(mContext, R.string.no_internet_connection);
            return;
        }
        mBusyDialog = new BusyDialog(mContext);
        mBusyDialog.show();

        final String url = apiName;

        Logger.debugLog("Api BaseUrl", url);
        ServerCallsProvider.volleyGetRequest(url, TAG, new ServerResponse() {
            @Override
            public void onSuccess(String statusCode, String responseServer) {
                try {
                    mBusyDialog.dismis();

                    try {
                        JSONObject mainJsonObject = new JSONObject(responseServer);
                        Logger.debugLog("Api responseServer", responseServer);
                        Logger.debugLog("Api todayCases", "" + mainJsonObject.getInt("todayCases"));

                        if(mainJsonObject.getInt("todayCases")!=0){
                            PersistentUser.setCountryConfToday(mContext,""+mainJsonObject.getInt("todayCases"));
                        }
                        if(mainJsonObject.getInt("todayDeaths")!=0){
                            PersistentUser.setCountryDeath(mContext,""+mainJsonObject.getInt("todayDeaths"));
                        }

                        topbar.setText("BANGLADESH");

                        country_conf_today.setText(": " + PersistentUser.getCountryConfToday(mContext));
                        country_conf_total.setText(": " + mainJsonObject.getInt("cases"));

                        country_recover_today.setText(": " + mainJsonObject.getInt("active"));
                        country_recover_total.setText(": " + mainJsonObject.getInt("recovered"));

                        country_death_today.setText(": " + PersistentUser.getCountryDeath(mContext));
                        country_death_total.setText(": " + mainJsonObject.getInt("deaths"));

                        country_test_total.setText(": " + mainJsonObject.getInt("totalTests"));
                        country_test_rate.setText(": " + mainJsonObject.getInt("testsPerOneMillion"));

                    } catch (JSONException e) {
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

    private void setTotalData() {

        Log.i("Getting Data", topbar.getText().toString());

        DecimalFormat df2 = new DecimalFormat("#.##");


        if (AppConstant.BASE.equalsIgnoreCase("BSR")) {
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
                tv_updatetime.setText(query.getUpdated_at());
            }
        } else if (AppConstant.BASE.equalsIgnoreCase("Bangabandhu")) {


            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                tv_present.setText(query.getBbd_affected_total());
                tv_recover.setText(query.getBbd_recovered_total());
                tv_death.setText(query.getBbd_death_total());
                tv_cmh.setText(query.getBbd_cmh_total());
                tv_isolation.setText(query.getBbd_isolation_total());
                tv_quarantine.setText(query.getBbd_home_quarantine_total());
                tv_tested_total.setText(query.getBbd_tested_total());
                topbar.setText("BAF BASE " + AppConstant.BASE);
                tv_updatetime.setText(query.getUpdated_at());
            }

        } else if (AppConstant.BASE.equalsIgnoreCase("PKP")) {

            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                tv_present.setText(query.getPkp_affected_total());
                tv_recover.setText(query.getPkp_recovered_total());
                tv_death.setText(query.getPkp_death_total());
                tv_cmh.setText(query.getPkp_cmh_total());
                tv_isolation.setText(query.getPkp_isolation_total());
                tv_quarantine.setText(query.getPkp_home_quarantine_total());
                tv_tested_total.setText(query.getPkp_tested_total());
                topbar.setText("BAF BASE " + AppConstant.BASE);
                tv_updatetime.setText(query.getUpdated_at());
            }

        } else if (AppConstant.BASE.equalsIgnoreCase("MTR")) {

            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                tv_present.setText(query.getMtr_affected_total());
                tv_recover.setText(query.getMtr_recovered_total());
                tv_death.setText(query.getMtr_death_total());
                tv_cmh.setText(query.getMtr_cmh_total());
                tv_isolation.setText(query.getMtr_isolation_total());
                tv_quarantine.setText(query.getMtr_home_quarantine_total());
                tv_tested_total.setText(query.getMtr_tested_total());
                topbar.setText("BAF BASE " + AppConstant.BASE);
                tv_updatetime.setText(query.getUpdated_at());
            }


        } else if (AppConstant.BASE.equalsIgnoreCase("ZHR")) {


            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);
                tv_present.setText(query.getZhr_affected_total());
                tv_recover.setText(query.getZhr_recovered_total());
                tv_death.setText(query.getZhr_death_total());
                tv_cmh.setText(query.getZhr_cmh_total());
                tv_isolation.setText(query.getZhr_isolation_total());
                tv_quarantine.setText(query.getZhr_home_quarantine_total());
                tv_tested_total.setText(query.getZhr_tested_total());
                topbar.setText("BAF BASE " + AppConstant.BASE);
                tv_updatetime.setText(query.getUpdated_at());
            }

        } else if (AppConstant.BASE.equalsIgnoreCase("Sheikh Hasina")) {


            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);

                tv_present.setText(query.getCxb_affected_total());
                tv_recover.setText(query.getCxb_recovered_total());
                tv_death.setText(query.getCxb_death_total());
                tv_cmh.setText(query.getCxb_cmh_total());
                tv_isolation.setText(query.getCxb_isolation_total());
                tv_quarantine.setText(query.getCxb_home_quarantine_total());
                tv_tested_total.setText(query.getCxb_tested_total());
                topbar.setText("BAF BASE " + AppConstant.BASE);
                tv_updatetime.setText(query.getUpdated_at());
            }

        } else if (AppConstant.BASE.equalsIgnoreCase("ALL BASES")) {


            for (int i = 0; i < BasewiseStateHolder.getBaseStatelist().size(); i++) {
                BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(i);

                double recoverPer = (Double.parseDouble(query.getTotal_recovered()) / Double.parseDouble(query.getTotal_affected())) * 100;
                double deathPer = (Double.parseDouble(query.getTotal_death()) / Double.parseDouble(query.getTotal_affected())) * 100;
                double cmhPer = (Double.parseDouble(query.getTotal_cmh()) / Double.parseDouble(query.getTotal_affected())) * 100;


                tv_recover.setText(query.getTotal_recovered() + "( " + df2.format(recoverPer) + " )");
                tv_death.setText(query.getTotal_death() + "( " + df2.format(deathPer) + " )");
                tv_cmh.setText(query.getTotal_cmh() + "( " + df2.format(cmhPer) + " )");

                tv_present.setText(query.getTotal_affected());
                tv_isolation.setText(query.getTotal_isolation());
                tv_quarantine.setText(query.getTotal_home_quarantine());
                tv_tested_total.setText(query.getTotal_tested());
                topbar.setText("ALL BASES ");
                tv_updatetime.setText(query.getUpdated_at());
                Log.i("Getting Data", BasewiseStateHolder.getBaseStatelist().get(1).getBsr_affected_total());
            }
        }


    }
}
