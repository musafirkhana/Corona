package com.baf.bafcoronainfo.holder;

import com.baf.bafcoronainfo.model.BaseWiselistModel;
import com.baf.bafcoronainfo.model.HelpModel;

import java.util.Vector;

public class HelpHolder {
    public static Vector<HelpModel> helpModels = new Vector<HelpModel>();

    public static Vector<HelpModel> getHelplist() {
        return helpModels;
    }

    public static void setHelplist(Vector<HelpModel> helpModels) {
        HelpHolder.helpModels = helpModels;
    }

    public static HelpModel getHelplist(int pos) {
        return helpModels.elementAt(pos);
    }

    public static void setHelplist(HelpModel helpModels) {
        HelpHolder.helpModels.addElement(helpModels);
    }

    public static void removeHelplist() {
        HelpHolder.helpModels.removeAllElements();
    }
}
