package com.baf.bafcoronainfo.holder;

import com.baf.bafcoronainfo.model.BaseWiselistModel;

import java.util.Vector;

public class BasewiseStateHolder {
    public static Vector<BaseWiselistModel> baseWiselistModels = new Vector<BaseWiselistModel>();

    public static Vector<BaseWiselistModel> getBaseStatelist() {
        return baseWiselistModels;
    }

    public static void setBaseStatelist(Vector<BaseWiselistModel> baseWiselistModels) {
        BasewiseStateHolder.baseWiselistModels = baseWiselistModels;
    }

    public static BaseWiselistModel getBaseStatelist(int pos) {
        return baseWiselistModels.elementAt(pos);
    }

    public static void setBaseStatelist(BaseWiselistModel baseWiselistModels) {
        BasewiseStateHolder.baseWiselistModels.addElement(baseWiselistModels);
    }

    public static void removeBaseStatelist() {
        BasewiseStateHolder.baseWiselistModels.removeAllElements();
    }
}
