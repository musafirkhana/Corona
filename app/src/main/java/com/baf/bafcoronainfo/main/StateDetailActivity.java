package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.holder.BasewiseStateHolder;
import com.baf.bafcoronainfo.model.BaseWiselistModel;
import com.baf.bafcoronainfo.util.AppConstant;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class StateDetailActivity extends Activity {
    private Context mContext;
    private static final String TAG = StateDetailActivity.class.getSimpleName();


    private String section_name;
    private TextView tv_head, tv_data_total;
    private TextView tv_data_base_1, tv_data_base_2, tv_data_base_3,
            tv_data_base_4, tv_data_base_5, tv_data_base_6;


    private static final int MAX_X_VALUE = 7;
    private static final int MAX_Y_VALUE = 50;
    private static final int MIN_Y_VALUE = 5;
    private static final String SET_LABEL = "App Downloads";
    private static final String[] DAYS = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

    ArrayList dataSets = null;

    private BarChart chart;
    ArrayList valueSet1 = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_state_detail);
        mContext = this;

        section_name = getIntent().getStringExtra("section");
        chart = (BarChart) findViewById(R.id.fragment_verticalbarchart_chart);
        initUI();
        setHead();




    }



    private ArrayList getXAxisValues() {
        ArrayList xAxis = new ArrayList();
        xAxis.add("BSR");
        xAxis.add("BBD");
        xAxis.add("PKP");
        xAxis.add("MTR");
        xAxis.add("ZHR");
        xAxis.add("CXB");
        return xAxis;
    }

    private void initUI() {
        tv_head = (TextView) findViewById(R.id.tv_head);
        tv_data_total = (TextView) findViewById(R.id.tv_data_total);
        tv_data_base_1 = (TextView) findViewById(R.id.tv_data_base_1);
        tv_data_base_2 = (TextView) findViewById(R.id.tv_data_base_2);
        tv_data_base_3 = (TextView) findViewById(R.id.tv_data_base_3);
        tv_data_base_4 = (TextView) findViewById(R.id.tv_data_base_4);
        tv_data_base_5 = (TextView) findViewById(R.id.tv_data_base_5);
        tv_data_base_6 = (TextView) findViewById(R.id.tv_data_base_6);
        tv_data_total = (TextView) findViewById(R.id.tv_data_total);
        setData();
    }

    private void setData() {
        if (section_name.equalsIgnoreCase("CONFIRMED")) {
            BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(0);
            tv_data_total.setText(query.getTotal_affected());
            tv_data_base_1.setText(query.getBsr_affected_total());
            tv_data_base_2.setText(query.getBbd_affected_total());
            tv_data_base_3.setText(query.getPkp_affected_total());
            tv_data_base_4.setText(query.getMtr_affected_total());
            tv_data_base_5.setText(query.getZhr_affected_total());
            tv_data_base_6.setText(query.getCxb_affected_total());

            // Bar Chart
            BarEntry v1e1 = new BarEntry(Integer.valueOf(query.getBsr_affected_total()), 0); // BSR
            valueSet1.add(v1e1);
            BarEntry v1e2 = new BarEntry(Integer.valueOf(query.getBbd_affected_total()), 1); // BBD
            valueSet1.add(v1e2);
            BarEntry v1e3 = new BarEntry(Integer.valueOf(query.getPkp_affected_total()), 2); // PKP
            valueSet1.add(v1e3);
            BarEntry v1e4 = new BarEntry(Integer.valueOf(query.getMtr_affected_total()), 3); // MTR
            valueSet1.add(v1e4);
            BarEntry v1e5 = new BarEntry(Integer.valueOf(query.getZhr_affected_total()), 4); // ZHR
            valueSet1.add(v1e5);
            BarEntry v1e6 = new BarEntry(Integer.valueOf(query.getCxb_affected_total()), 5); // CXB
            valueSet1.add(v1e6);

            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Base");
            barDataSet1.setColor(Color.rgb(255, 178, 89));

            dataSets = new ArrayList();
            dataSets.add(barDataSet1);

            BarData data = new BarData(getXAxisValues(), dataSets);
            chart.setData(data);
            chart.setDescription("");
            chart.animateXY(2000, 2000);
            chart.invalidate();

        } else if (section_name.equalsIgnoreCase("RECOVERED")) {

            BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(0);
            tv_data_total.setText(query.getTotal_recovered());
            tv_data_base_1.setText(query.getBsr_recovered_total());
            tv_data_base_2.setText(query.getBbd_recovered_total());
            tv_data_base_3.setText(query.getPkp_recovered_total());
            tv_data_base_4.setText(query.getMtr_recovered_total());
            tv_data_base_5.setText(query.getZhr_recovered_total());
            tv_data_base_6.setText(query.getCxb_recovered_total());

            // Bar Chart
            BarEntry v1e1 = new BarEntry(Integer.valueOf(query.getBsr_recovered_total()), 0); // BSR
            valueSet1.add(v1e1);
            BarEntry v1e2 = new BarEntry(Integer.valueOf(query.getBbd_recovered_total()), 1); // BBD
            valueSet1.add(v1e2);
            BarEntry v1e3 = new BarEntry(Integer.valueOf(query.getPkp_recovered_total()), 2); // PKP
            valueSet1.add(v1e3);
            BarEntry v1e4 = new BarEntry(Integer.valueOf(query.getMtr_recovered_total()), 3); // MTR
            valueSet1.add(v1e4);
            BarEntry v1e5 = new BarEntry(Integer.valueOf(query.getZhr_recovered_total()), 4); // ZHR
            valueSet1.add(v1e5);
            BarEntry v1e6 = new BarEntry(Integer.valueOf(query.getCxb_recovered_total()), 5); // CXB
            valueSet1.add(v1e6);

            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Base");
            barDataSet1.setColor(Color.rgb(76, 217, 123));

            dataSets = new ArrayList();
            dataSets.add(barDataSet1);

            BarData data = new BarData(getXAxisValues(), dataSets);
            chart.setData(data);
            chart.setDescription("");
            chart.animateXY(2000, 2000);
            chart.invalidate();

        } else if (section_name.equalsIgnoreCase("DEATH")) {
            BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(0);
            tv_data_total.setText(query.getTotal_death());
            tv_data_base_1.setText(query.getBsr_death_total());
            tv_data_base_2.setText(query.getBbd_death_total());
            tv_data_base_3.setText(query.getPkp_death_total());
            tv_data_base_4.setText(query.getMtr_death_total());
            tv_data_base_5.setText(query.getZhr_death_total());
            tv_data_base_6.setText(query.getCxb_death_total());

            // Bar Chart
            BarEntry v1e1 = new BarEntry(Integer.valueOf(query.getBsr_death_total()), 0); // BSR
            valueSet1.add(v1e1);
            BarEntry v1e2 = new BarEntry(Integer.valueOf(query.getBbd_death_total()), 1); // BBD
            valueSet1.add(v1e2);
            BarEntry v1e3 = new BarEntry(Integer.valueOf(query.getPkp_death_total()), 2); // PKP
            valueSet1.add(v1e3);
            BarEntry v1e4 = new BarEntry(Integer.valueOf(query.getMtr_death_total()), 3); // MTR
            valueSet1.add(v1e4);
            BarEntry v1e5 = new BarEntry(Integer.valueOf(query.getZhr_death_total()), 4); // ZHR
            valueSet1.add(v1e5);
            BarEntry v1e6 = new BarEntry(Integer.valueOf(query.getCxb_death_total()), 5); // CXB
            valueSet1.add(v1e6);

            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Base");
            barDataSet1.setColor(Color.rgb(255, 89, 89));

            dataSets = new ArrayList();
            dataSets.add(barDataSet1);

            BarData data = new BarData(getXAxisValues(), dataSets);
            chart.setData(data);
            chart.setDescription("");
            chart.animateXY(2000, 2000);
            chart.invalidate();

        } else if (section_name.equalsIgnoreCase("CMH")) {
            BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(0);
            tv_data_total.setText(query.getTotal_cmh());
            tv_data_base_1.setText(query.getBsr_cmh_total());
            tv_data_base_2.setText(query.getBbd_cmh_total());
            tv_data_base_3.setText(query.getPkp_cmh_total());
            tv_data_base_4.setText(query.getMtr_cmh_total());
            tv_data_base_5.setText(query.getZhr_cmh_total());
            tv_data_base_6.setText(query.getCxb_cmh_total());

            // Bar Chart
            BarEntry v1e1 = new BarEntry(Integer.valueOf(query.getBsr_cmh_total()), 0); // BSR
            valueSet1.add(v1e1);
            BarEntry v1e2 = new BarEntry(Integer.valueOf(query.getBbd_cmh_total()), 1); // BBD
            valueSet1.add(v1e2);
            BarEntry v1e3 = new BarEntry(Integer.valueOf(query.getPkp_cmh_total()), 2); // PKP
            valueSet1.add(v1e3);
            BarEntry v1e4 = new BarEntry(Integer.valueOf(query.getMtr_cmh_total()), 3); // MTR
            valueSet1.add(v1e4);
            BarEntry v1e5 = new BarEntry(Integer.valueOf(query.getZhr_cmh_total()), 4); // ZHR
            valueSet1.add(v1e5);
            BarEntry v1e6 = new BarEntry(Integer.valueOf(query.getCxb_cmh_total()), 5); // CXB
            valueSet1.add(v1e6);

            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Base");
            barDataSet1.setColor(Color.rgb(76, 181, 255));

            dataSets = new ArrayList();
            dataSets.add(barDataSet1);

            BarData data = new BarData(getXAxisValues(), dataSets);
            chart.setData(data);
            chart.setDescription("");
            chart.animateXY(2000, 2000);
            chart.invalidate();

        } else if (section_name.equalsIgnoreCase("ISOLATION")) {
            BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(0);
            tv_data_total.setText(query.getTotal_isolation());
            tv_data_base_1.setText(query.getBsr_isolation_total());
            tv_data_base_2.setText(query.getBbd_isolation_total());
            tv_data_base_3.setText(query.getPkp_isolation_total());
            tv_data_base_4.setText(query.getMtr_isolation_total());
            tv_data_base_5.setText(query.getZhr_isolation_total());
            tv_data_base_6.setText(query.getCxb_isolation_total());

            // Bar Chart
            BarEntry v1e1 = new BarEntry(Integer.valueOf(query.getBsr_isolation_total()), 0); // BSR
            valueSet1.add(v1e1);
            BarEntry v1e2 = new BarEntry(Integer.valueOf(query.getBbd_isolation_total()), 1); // BBD
            valueSet1.add(v1e2);
            BarEntry v1e3 = new BarEntry(Integer.valueOf(query.getPkp_isolation_total()), 2); // PKP
            valueSet1.add(v1e3);
            BarEntry v1e4 = new BarEntry(Integer.valueOf(query.getMtr_isolation_total()), 3); // MTR
            valueSet1.add(v1e4);
            BarEntry v1e5 = new BarEntry(Integer.valueOf(query.getZhr_isolation_total()), 4); // ZHR
            valueSet1.add(v1e5);
            BarEntry v1e6 = new BarEntry(Integer.valueOf(query.getCxb_isolation_total()), 5); // CXB
            valueSet1.add(v1e6);

            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Base");
            barDataSet1.setColor(Color.rgb(144, 89, 255));

            dataSets = new ArrayList();
            dataSets.add(barDataSet1);

            BarData data = new BarData(getXAxisValues(), dataSets);
            chart.setData(data);
            chart.setDescription("");
            chart.animateXY(2000, 2000);
            chart.invalidate();

        } else if (section_name.equalsIgnoreCase("HOME QUARANTINE")) {
            BaseWiselistModel query = BasewiseStateHolder.getBaseStatelist().get(0);
            tv_data_total.setText(query.getTotal_home_quarantine());
            tv_data_base_1.setText(query.getBsr_home_quarantine_total());
            tv_data_base_2.setText(query.getBbd_home_quarantine_total());
            tv_data_base_3.setText(query.getPkp_home_quarantine_total());
            tv_data_base_4.setText(query.getMtr_home_quarantine_total());
            tv_data_base_5.setText(query.getZhr_home_quarantine_total());
            tv_data_base_6.setText(query.getCxb_home_quarantine_total());

            // Bar Chart
            BarEntry v1e1 = new BarEntry(Integer.valueOf(query.getBsr_home_quarantine_total()), 0); // BSR
            valueSet1.add(v1e1);
            BarEntry v1e2 = new BarEntry(Integer.valueOf(query.getBbd_home_quarantine_total()), 1); // BBD
            valueSet1.add(v1e2);
            BarEntry v1e3 = new BarEntry(Integer.valueOf(query.getPkp_home_quarantine_total()), 2); // PKP
            valueSet1.add(v1e3);
            BarEntry v1e4 = new BarEntry(Integer.valueOf(query.getMtr_home_quarantine_total()), 3); // MTR
            valueSet1.add(v1e4);
            BarEntry v1e5 = new BarEntry(Integer.valueOf(query.getZhr_home_quarantine_total()), 4); // ZHR
            valueSet1.add(v1e5);
            BarEntry v1e6 = new BarEntry(Integer.valueOf(query.getCxb_home_quarantine_total()), 5); // CXB
            valueSet1.add(v1e6);

            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Base");
            barDataSet1.setColor(Color.rgb(71, 63, 151));

            dataSets = new ArrayList();
            dataSets.add(barDataSet1);

            BarData data = new BarData(getXAxisValues(), dataSets);
            chart.setData(data);
            chart.setDescription("");
            chart.animateXY(2000, 2000);
            chart.invalidate();

        }


    }

    private void setHead() {
        if (AppConstant.SELECTION.equalsIgnoreCase("0")) {
            tv_head.setText("TOTAL " + section_name + " ");
        } else if (AppConstant.SELECTION.equalsIgnoreCase("1")) {
            tv_head.setText("TODAY " + section_name + " ");
        } else {
            tv_head.setText("YESTERDAY " + section_name + " ");
        }

    }

    public void BACK(View v) {
        this.finish();

    }
}
