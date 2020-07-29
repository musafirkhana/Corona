package com.baf.bafcoronainfo.parser;

import android.content.Context;
import android.util.Log;

import com.baf.bafcoronainfo.holder.BasewiseStateHolder;
import com.baf.bafcoronainfo.model.BaseWiselistModel;
import com.baf.bafcoronainfo.util.AllUrls;
import com.baf.bafcoronainfo.util.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TodayStatelistParser {
    public static boolean connect(Context con, String result)
            throws JSONException, IOException {

        BasewiseStateHolder.removeBaseStatelist();
        if (result.length() < 1) {
            return false;
        }

        JSONObject mainJsonObject = null;
        JSONObject jsonObject = null;
        JSONObject total_jsonObject = null;
        JSONObject ForTotalTodayYesterday_jsonObject=null;

        if(AllUrls.API_KEY.equalsIgnoreCase(AllUrls.YESTREDAY_KEY)){
            mainJsonObject = new JSONObject(result);
            jsonObject = mainJsonObject.getJSONObject("Yesterday");
            total_jsonObject = mainJsonObject.getJSONObject("Alltimetotal");
             ForTotalTodayYesterday_jsonObject = mainJsonObject.getJSONObject("ForTotalTodayYesterday");

        }else if(AllUrls.API_KEY.equalsIgnoreCase(AllUrls.TODAY_KEY)){
             mainJsonObject = new JSONObject(result);
             jsonObject = mainJsonObject.getJSONObject("Today");
             total_jsonObject = mainJsonObject.getJSONObject("Alltimetotal");
             ForTotalTodayYesterday_jsonObject = mainJsonObject.getJSONObject("ForTotalTodayYesterday");
        }



        BaseWiselistModel baseWiselistModel;
        for (int i = 0; i < jsonObject.length(); i++) {
            Logger.debugLog("Api Today",""+ jsonObject.length()+"   "+total_jsonObject.length());
            Logger.debugLog("Api Today Length",""+ jsonObject.getString("bsr_tested"));
            Logger.debugLog("Api Today",""+ jsonObject.getString("bsr_affected"));

            baseWiselistModel = new BaseWiselistModel();
            BasewiseStateHolder basewiseStateHolder = new BasewiseStateHolder();
            //ALL
            baseWiselistModel.setTotal_tested(total_jsonObject.getString("total_tested"));
            baseWiselistModel.setTotal_affected(total_jsonObject.getString("total_affected"));
            baseWiselistModel.setTotal_recovered(total_jsonObject.getString("total_recovered"));
            baseWiselistModel.setTotal_death(total_jsonObject.getString("total_death"));
//            baseWiselistModel.setTotal_cmh(total_jsonObject.getString("total_cmh"));
//            baseWiselistModel.setTotal_isolation(total_jsonObject.getString("total_isolation"));
//            baseWiselistModel.setTotal_home_quarantine(total_jsonObject.getString("total_home_quarantine"));
            baseWiselistModel.setTotal_cmh(ForTotalTodayYesterday_jsonObject.getString("total_cmh"));
            baseWiselistModel.setTotal_isolation(ForTotalTodayYesterday_jsonObject.getString("total_isolation"));
            baseWiselistModel.setTotal_home_quarantine(ForTotalTodayYesterday_jsonObject.getString("total_home_quarantine"));
            //BSR
            baseWiselistModel.setBsr_affected_total(jsonObject.getString("bsr_affected"));
            baseWiselistModel.setBsr_cmh_total(jsonObject.getString("bsr_cmh"));
            baseWiselistModel.setBsr_death_total(jsonObject.getString("bsr_death"));
            baseWiselistModel.setBsr_home_quarantine_total(jsonObject.getString("bsr_home_quarantine"));
            baseWiselistModel.setBsr_isolation_total(jsonObject.getString("bsr_isolation"));
            baseWiselistModel.setBsr_recovered_total(jsonObject.getString("bsr_recovered"));
            baseWiselistModel.setBsr_tested_total(jsonObject.getString("bsr_tested"));
            //MTR
            baseWiselistModel.setMtr_affected_total(jsonObject.getString("mtr_affected"));
            baseWiselistModel.setMtr_cmh_total(jsonObject.getString("mtr_cmh"));
            baseWiselistModel.setMtr_death_total(jsonObject.getString("mtr_death"));
            baseWiselistModel.setMtr_home_quarantine_total(jsonObject.getString("mtr_home_quarantine"));
            baseWiselistModel.setMtr_isolation_total(jsonObject.getString("mtr_isolation"));
            baseWiselistModel.setMtr_recovered_total(jsonObject.getString("mtr_recovered"));
            baseWiselistModel.setMtr_tested_total(jsonObject.getString("mtr_tested"));
            //BBD
            baseWiselistModel.setBbd_affected_total(jsonObject.getString("bbd_affected"));
            baseWiselistModel.setBbd_cmh_total(jsonObject.getString("bbd_cmh"));
            baseWiselistModel.setBbd_death_total(jsonObject.getString("bbd_death"));
            baseWiselistModel.setBbd_home_quarantine_total(jsonObject.getString("bbd_home_quarantine"));
            baseWiselistModel.setBbd_isolation_total(jsonObject.getString("bbd_isolation"));
            baseWiselistModel.setBbd_recovered_total(jsonObject.getString("bbd_recovered"));
            baseWiselistModel.setBbd_tested_total(jsonObject.getString("bbd_tested"));
            //ZHR
            baseWiselistModel.setZhr_affected_total(jsonObject.getString("zhr_affected"));
            baseWiselistModel.setZhr_cmh_total(jsonObject.getString("zhr_cmh"));
            baseWiselistModel.setZhr_death_total(jsonObject.getString("zhr_death"));
            baseWiselistModel.setZhr_home_quarantine_total(jsonObject.getString("zhr_home_quarantine"));
            baseWiselistModel.setZhr_isolation_total(jsonObject.getString("zhr_isolation"));
            baseWiselistModel.setZhr_recovered_total(jsonObject.getString("zhr_recovered"));
            baseWiselistModel.setZhr_tested_total(jsonObject.getString("zhr_tested"));
            //PKP
            baseWiselistModel.setPkp_affected_total(jsonObject.getString("pkp_affected"));
            baseWiselistModel.setPkp_cmh_total(jsonObject.getString("pkp_cmh"));
            baseWiselistModel.setPkp_death_total(jsonObject.getString("pkp_death"));
            baseWiselistModel.setPkp_home_quarantine_total(jsonObject.getString("pkp_home_quarantine"));
            baseWiselistModel.setPkp_isolation_total(jsonObject.getString("pkp_isolation"));
            baseWiselistModel.setPkp_recovered_total(jsonObject.getString("pkp_recovered"));
            baseWiselistModel.setPkp_tested_total(jsonObject.getString("pkp_tested"));
            //CXB
            baseWiselistModel.setCxb_affected_total(jsonObject.getString("cxb_affected"));
            baseWiselistModel.setCxb_cmh_total(jsonObject.getString("cxb_cmh"));
            baseWiselistModel.setCxb_death_total(jsonObject.getString("cxb_death"));
            baseWiselistModel.setCxb_home_quarantine_total(jsonObject.getString("cxb_home_quarantine"));
            baseWiselistModel.setCxb_isolation_total(jsonObject.getString("cxb_isolation"));
            baseWiselistModel.setCxb_recovered_total(jsonObject.getString("cxb_recovered"));
            baseWiselistModel.setCxb_tested_total(jsonObject.getString("cxb_tested"));


            Log.i("Api Today",jsonObject.getString("cxb_tested"));

            basewiseStateHolder.setBaseStatelist(baseWiselistModel);
            baseWiselistModel = null;
        }


        return true;
    }
}
