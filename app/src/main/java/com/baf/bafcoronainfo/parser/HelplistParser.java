package com.baf.bafcoronainfo.parser;

import android.content.Context;
import android.util.Log;

import com.baf.bafcoronainfo.holder.BasewiseStateHolder;
import com.baf.bafcoronainfo.holder.HelpHolder;
import com.baf.bafcoronainfo.model.BaseWiselistModel;
import com.baf.bafcoronainfo.model.HelpModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HelplistParser {
    public static boolean connect(Context con, String result)
            throws JSONException, IOException {

        HelpHolder.removeHelplist();
        if (result.length() < 1) {
            return false;

        }

        final JSONArray mainJsonObject = new JSONArray(result);

//		final JSONObject banner = mainJsonObject.getJSONObject("banner");
//		final JSONArray banner_list = mainJsonObject.getJSONArray(result);

        HelpModel helpModel;
        for (int i = 0; i < mainJsonObject.length(); i++) {
            JSONObject jsonObject = mainJsonObject.getJSONObject(i);

            helpModel = new HelpModel();
            HelpHolder helpHolder = new HelpHolder();
            //BSR
            helpModel.setBsr_smo(jsonObject.getString("bsr_cmh_coronacell"));
            helpModel.setBsr_miroom(jsonObject.getString("bsr_miroom"));
            helpModel.setBsr_ambuloance(jsonObject.getString("bsr_coronacell"));
            helpModel.setBsr_cmh(jsonObject.getString("bsr_cmh"));

            helpModel.setBbd_smo(jsonObject.getString("bbd_cmh_coronacell"));
            helpModel.setBbd_miroom(jsonObject.getString("bbd_miroom"));
            helpModel.setBbd_ambuloance(jsonObject.getString("bbd_coronacell"));
            helpModel.setBbd_cmh(jsonObject.getString("bbd_cmh"));

            helpModel.setPkp_smo(jsonObject.getString("pkp_cmh_coronacell"));
            helpModel.setPkp_miroom(jsonObject.getString("pkp_miroom"));
            helpModel.setPkp_ambuloance(jsonObject.getString("pkp_coronacell"));
            helpModel.setPkp_cmh(jsonObject.getString("pkp_cmh"));

            helpModel.setMtr_smo(jsonObject.getString("mtr_cmh_coronacell"));
            helpModel.setMtr_miroom(jsonObject.getString("mtr_miroom"));
            helpModel.setMtr_ambuloance(jsonObject.getString("mtr_coronacell"));
            helpModel.setMtr_cmh(jsonObject.getString("mtr_cmh"));

            helpModel.setZhr_smo(jsonObject.getString("zhr_cmh_coronacell"));
            helpModel.setZhr_miroom(jsonObject.getString("zhr_miroom"));
            helpModel.setZhr_ambuloance(jsonObject.getString("zhr_coronacell"));
            helpModel.setZhr_cmh(jsonObject.getString("zhr_cmh"));

            helpModel.setCkb_smo(jsonObject.getString("ckb_cmh_coronacell"));
            helpModel.setCkb_miroom(jsonObject.getString("ckb_miroom"));
            helpModel.setCkb_ambuloance(jsonObject.getString("ckb_coronacell"));
            helpModel.setCkb_cmh(jsonObject.getString("ckb_cmh"));



            Log.i("ckb_cmh",jsonObject.getString("ckb_cmh"));

            helpHolder.setHelplist(helpModel);
            helpModel = null;
        }


        return true;
    }
}
