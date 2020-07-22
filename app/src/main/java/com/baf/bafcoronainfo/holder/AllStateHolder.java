package com.baf.bafcoronainfo.holder;

import com.baf.bafcoronainfo.model.StatelistModel;

import java.util.Vector;

public class AllStateHolder {
    public static Vector<StatelistModel> statelistModels = new Vector<StatelistModel>();

    public static Vector<StatelistModel> getAllStatelist() {
        return statelistModels;
    }

    public static void setAllStatelist(Vector<StatelistModel> statelistModels) {
        AllStateHolder.statelistModels = statelistModels;
    }

    public static StatelistModel getAllStatelist(int pos) {
        return statelistModels.elementAt(pos);
    }

    public static void setAllStatelist(StatelistModel statelistModels) {
        AllStateHolder.statelistModels.addElement(statelistModels);
    }

    public static void removeStatelist() {
        AllStateHolder.statelistModels.removeAllElements();
    }
}
