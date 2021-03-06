package com.baf.bafcoronainfo.parser;

import android.content.Context;
import android.util.Log;

import com.baf.bafcoronainfo.holder.BasewiseStateHolder;
import com.baf.bafcoronainfo.model.BaseWiselistModel;
import com.baf.bafcoronainfo.util.Logger;

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

//        final JSONArray mainJsonObject = new JSONArray(result);
        JSONObject mainJsonObject = new JSONObject(result);
        JSONObject jsonObject = mainJsonObject.getJSONObject("Basewisetotal");
        JSONObject total_jsonObject = mainJsonObject.getJSONObject("Alltimetotal");
        JSONObject ForTotalTodayYesterday_jsonObject = mainJsonObject.getJSONObject("ForTotalTodayYesterday");
        JSONObject CmhisohqforTotalTodayYesterday = mainJsonObject.getJSONObject("CmhisohqforTotalTodayYesterday");

        BaseWiselistModel baseWiselistModel;
        for (int i = 0; i < jsonObject.length(); i++) {
            Logger.debugLog("Api mtr_tested_total",""+ jsonObject.length()+"   "+total_jsonObject.length());
            Logger.debugLog("Api jsonObject Length",""+ jsonObject.getString("mtr_tested_total"));
            Logger.debugLog("Api total_tested",""+ ForTotalTodayYesterday_jsonObject.getString("total_cmh"));

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
//
            baseWiselistModel.setTotal_cmh(ForTotalTodayYesterday_jsonObject.getString("total_cmh"));
            baseWiselistModel.setTotal_isolation(ForTotalTodayYesterday_jsonObject.getString("total_isolation"));
            baseWiselistModel.setTotal_home_quarantine(ForTotalTodayYesterday_jsonObject.getString("total_home_quarantine"));
            baseWiselistModel.setUpdated_at(mainJsonObject.getString("updated_at"));
            //BSR
            baseWiselistModel.setBsr_affected_total(jsonObject.getString("bsr_affected_total"));
            baseWiselistModel.setBsr_cmh_total(CmhisohqforTotalTodayYesterday.getString("bsr_cmh"));
            baseWiselistModel.setBsr_death_total(jsonObject.getString("bsr_death_total"));
            baseWiselistModel.setBsr_home_quarantine_total(CmhisohqforTotalTodayYesterday.getString("bsr_home_quarantine"));
            baseWiselistModel.setBsr_isolation_total(CmhisohqforTotalTodayYesterday.getString("bsr_isolation"));
            baseWiselistModel.setBsr_recovered_total(jsonObject.getString("bsr_recovered_total"));
            baseWiselistModel.setBsr_tested_total(jsonObject.getString("bsr_tested_total"));
            //MTR
            baseWiselistModel.setMtr_affected_total(jsonObject.getString("mtr_affected_total"));
            baseWiselistModel.setMtr_cmh_total(CmhisohqforTotalTodayYesterday.getString("mtr_cmh"));
            baseWiselistModel.setMtr_death_total(jsonObject.getString("mtr_death_total"));
            baseWiselistModel.setMtr_home_quarantine_total(CmhisohqforTotalTodayYesterday.getString("mtr_home_quarantine"));
            baseWiselistModel.setMtr_isolation_total(CmhisohqforTotalTodayYesterday.getString("mtr_isolation"));
            baseWiselistModel.setMtr_recovered_total(jsonObject.getString("mtr_recovered_total"));
            baseWiselistModel.setMtr_tested_total(jsonObject.getString("mtr_tested_total"));
            //BBD
            baseWiselistModel.setBbd_affected_total(jsonObject.getString("bbd_affected_total"));
            baseWiselistModel.setBbd_cmh_total(CmhisohqforTotalTodayYesterday.getString("bbd_cmh"));
            baseWiselistModel.setBbd_death_total(jsonObject.getString("bbd_death_total"));
            baseWiselistModel.setBbd_home_quarantine_total(CmhisohqforTotalTodayYesterday.getString("bbd_home_quarantine"));
            baseWiselistModel.setBbd_isolation_total(CmhisohqforTotalTodayYesterday.getString("bbd_isolation"));
            baseWiselistModel.setBbd_recovered_total(jsonObject.getString("bbd_recovered_total"));
            baseWiselistModel.setBbd_tested_total(jsonObject.getString("bbd_tested_total"));
            //ZHR
            baseWiselistModel.setZhr_affected_total(jsonObject.getString("zhr_affected_total"));
            baseWiselistModel.setZhr_cmh_total(CmhisohqforTotalTodayYesterday.getString("zhr_cmh"));
            baseWiselistModel.setZhr_death_total(jsonObject.getString("zhr_death_total"));
            baseWiselistModel.setZhr_home_quarantine_total(CmhisohqforTotalTodayYesterday.getString("zhr_home_quarantine"));
            baseWiselistModel.setZhr_isolation_total(CmhisohqforTotalTodayYesterday.getString("zhr_isolation"));
            baseWiselistModel.setZhr_recovered_total(jsonObject.getString("zhr_recovered_total"));
            baseWiselistModel.setZhr_tested_total(jsonObject.getString("zhr_tested_total"));
            //PKP
            baseWiselistModel.setPkp_affected_total(jsonObject.getString("pkp_affected_total"));
            baseWiselistModel.setPkp_cmh_total(CmhisohqforTotalTodayYesterday.getString("pkp_cmh"));
            baseWiselistModel.setPkp_death_total(jsonObject.getString("pkp_death_total"));
            baseWiselistModel.setPkp_home_quarantine_total(CmhisohqforTotalTodayYesterday.getString("pkp_home_quarantine"));
            baseWiselistModel.setPkp_isolation_total(CmhisohqforTotalTodayYesterday.getString("pkp_isolation"));
            baseWiselistModel.setPkp_recovered_total(jsonObject.getString("pkp_recovered_total"));
            baseWiselistModel.setPkp_tested_total(jsonObject.getString("pkp_tested_total"));
            //CXB
            baseWiselistModel.setCxb_affected_total(jsonObject.getString("cxb_affected_total"));
            baseWiselistModel.setCxb_cmh_total(CmhisohqforTotalTodayYesterday.getString("cxb_cmh"));
            baseWiselistModel.setCxb_death_total(jsonObject.getString("cxb_death_total"));
            baseWiselistModel.setCxb_home_quarantine_total(CmhisohqforTotalTodayYesterday.getString("cxb_home_quarantine"));
            baseWiselistModel.setCxb_isolation_total(CmhisohqforTotalTodayYesterday.getString("cxb_isolation"));
            baseWiselistModel.setCxb_recovered_total(jsonObject.getString("cxb_recovered_total"));
            baseWiselistModel.setCxb_tested_total(jsonObject.getString("cxb_tested_total"));


            Log.i("cxb_tested_total",jsonObject.getString("cxb_tested_total"));

            basewiseStateHolder.setBaseStatelist(baseWiselistModel);
            baseWiselistModel = null;
        }


        return true;
    }
}
