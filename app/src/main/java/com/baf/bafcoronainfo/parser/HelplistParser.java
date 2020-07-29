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
            helpModel.setBase(jsonObject.getString("base"));
            helpModel.setAppoinment_name(jsonObject.getString("appoinment_name"));
            helpModel.setMobile_no(jsonObject.getString("mobile_no"));




            Log.i("appoinment_name",jsonObject.getString("appoinment_name"));

            helpHolder.setHelplist(helpModel);
            helpModel = null;
        }


        return true;
    }
}
