package com.baf.bafcoronainfo.parser;

import android.content.Context;
import android.util.Log;

import com.baf.bafcoronainfo.holder.AllStateHolder;
import com.baf.bafcoronainfo.model.StatelistModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class StatelistParser {
    public static boolean connect(Context con, String result)
            throws JSONException, IOException {

        AllStateHolder.removeStatelist();
        if (result.length() < 1) {
            return false;

        }

        final JSONArray mainJsonObject = new JSONArray(result);

//		final JSONObject banner = mainJsonObject.getJSONObject("banner");
//		final JSONArray banner_list = mainJsonObject.getJSONArray(result);

        StatelistModel statelistModel;
        for (int i = 0; i < mainJsonObject.length(); i++) {
            JSONObject jsonObject = mainJsonObject.getJSONObject(i);

            statelistModel = new StatelistModel();
            AllStateHolder allArabicList = new AllStateHolder();
            statelistModel.setId(jsonObject.getString("id"));
            statelistModel.setPresent_state(jsonObject.getString("present_state"));
            statelistModel.setRecovered(jsonObject.getString("recovered"));
            statelistModel.setDeath(jsonObject.getString("death"));
            statelistModel.setCmh(jsonObject.getString("cmh"));
            statelistModel.setIsolation(jsonObject.getString("isolation"));
            statelistModel.setHome_quarantine(jsonObject.getString("home_quarantine"));
            Log.i("home_quarantine",jsonObject.getString("home_quarantine"));

            allArabicList.setAllStatelist(statelistModel);
            statelistModel = null;
        }


        return true;
    }
}
