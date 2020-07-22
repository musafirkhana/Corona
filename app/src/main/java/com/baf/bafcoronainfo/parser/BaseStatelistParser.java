package com.baf.bafcoronainfo.parser;

import android.content.Context;
import android.util.Log;

import com.baf.bafcoronainfo.holder.AllStateHolder;
import com.baf.bafcoronainfo.holder.BasewiseStateHolder;
import com.baf.bafcoronainfo.model.BaseWiselistModel;
import com.baf.bafcoronainfo.model.StatelistModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class BaseStatelistParser {
    public static boolean connect(Context con, String result)
            throws JSONException, IOException {

        BasewiseStateHolder.removeBaseStatelist();
        if (result.length() < 1) {
            return false;

        }

        final JSONArray mainJsonObject = new JSONArray(result);

//		final JSONObject banner = mainJsonObject.getJSONObject("banner");
//		final JSONArray banner_list = mainJsonObject.getJSONArray(result);

        BaseWiselistModel baseWiselistModel;
        for (int i = 0; i < mainJsonObject.length(); i++) {
            JSONObject jsonObject = mainJsonObject.getJSONObject(i);

            baseWiselistModel = new BaseWiselistModel();
            BasewiseStateHolder basewiseStateHolder = new BasewiseStateHolder();
            //BSR
            baseWiselistModel.setBsr_affected_total(jsonObject.getString("bsr_affected_total"));
            baseWiselistModel.setBsr_cmh_total(jsonObject.getString("bsr_cmh_total"));
            baseWiselistModel.setBsr_death_total(jsonObject.getString("bsr_death_total"));
            baseWiselistModel.setBsr_home_quarantine_total(jsonObject.getString("bsr_home_quarantine_total"));
            baseWiselistModel.setBsr_isolation_total(jsonObject.getString("bsr_isolation_total"));
            baseWiselistModel.setBsr_recovered_total(jsonObject.getString("bsr_recovered_total"));
            baseWiselistModel.setBsr_tested_total(jsonObject.getString("bsr_tested_total"));
            //MTR
            baseWiselistModel.setMtr_affected_total(jsonObject.getString("mtr_affected_total"));
            baseWiselistModel.setMtr_cmh_total(jsonObject.getString("mtr_cmh_total"));
            baseWiselistModel.setMtr_death_total(jsonObject.getString("mtr_death_total"));
            baseWiselistModel.setMtr_home_quarantine_total(jsonObject.getString("mtr_home_quarantine_total"));
            baseWiselistModel.setMtr_isolation_total(jsonObject.getString("mtr_isolation_total"));
            baseWiselistModel.setMtr_recovered_total(jsonObject.getString("mtr_recovered_total"));
            baseWiselistModel.setMtr_tested_total(jsonObject.getString("mtr_tested_total"));
            //BBD
            baseWiselistModel.setBbd_affected_total(jsonObject.getString("bbd_affected_total"));
            baseWiselistModel.setBbd_cmh_total(jsonObject.getString("bbd_cmh_total"));
            baseWiselistModel.setBbd_death_total(jsonObject.getString("bbd_death_total"));
            baseWiselistModel.setBbd_home_quarantine_total(jsonObject.getString("bbd_home_quarantine_total"));
            baseWiselistModel.setBbd_isolation_total(jsonObject.getString("bbd_isolation_total"));
            baseWiselistModel.setBbd_recovered_total(jsonObject.getString("bbd_recovered_total"));
            baseWiselistModel.setBbd_tested_total(jsonObject.getString("bbd_tested_total"));
            //ZHR
            baseWiselistModel.setZhr_affected_total(jsonObject.getString("zhr_affected_total"));
            baseWiselistModel.setZhr_cmh_total(jsonObject.getString("zhr_cmh_total"));
            baseWiselistModel.setZhr_death_total(jsonObject.getString("zhr_death_total"));
            baseWiselistModel.setZhr_home_quarantine_total(jsonObject.getString("zhr_home_quarantine_total"));
            baseWiselistModel.setZhr_isolation_total(jsonObject.getString("zhr_isolation_total"));
            baseWiselistModel.setZhr_recovered_total(jsonObject.getString("zhr_recovered_total"));
            baseWiselistModel.setZhr_tested_total(jsonObject.getString("zhr_tested_total"));
            //PKP
            baseWiselistModel.setPkp_affected_total(jsonObject.getString("pkp_affected_total"));
            baseWiselistModel.setPkp_cmh_total(jsonObject.getString("pkp_cmh_total"));
            baseWiselistModel.setPkp_death_total(jsonObject.getString("pkp_death_total"));
            baseWiselistModel.setPkp_home_quarantine_total(jsonObject.getString("pkp_home_quarantine_total"));
            baseWiselistModel.setPkp_isolation_total(jsonObject.getString("pkp_isolation_total"));
            baseWiselistModel.setPkp_recovered_total(jsonObject.getString("pkp_recovered_total"));
            baseWiselistModel.setPkp_tested_total(jsonObject.getString("pkp_tested_total"));
            //CXB
            baseWiselistModel.setCxb_affected_total(jsonObject.getString("cxb_affected_total"));
            baseWiselistModel.setCxb_cmh_total(jsonObject.getString("cxb_cmh_total"));
            baseWiselistModel.setCxb_death_total(jsonObject.getString("cxb_death_total"));
            baseWiselistModel.setCxb_home_quarantine_total(jsonObject.getString("cxb_home_quarantine_total"));
            baseWiselistModel.setCxb_isolation_total(jsonObject.getString("cxb_isolation_total"));
            baseWiselistModel.setCxb_recovered_total(jsonObject.getString("cxb_recovered_total"));
            baseWiselistModel.setCxb_tested_total(jsonObject.getString("cxb_tested_total"));


            Log.i("cxb_tested_total",jsonObject.getString("cxb_tested_total"));

            basewiseStateHolder.setBaseStatelist(baseWiselistModel);
            baseWiselistModel = null;
        }


        return true;
    }
}
