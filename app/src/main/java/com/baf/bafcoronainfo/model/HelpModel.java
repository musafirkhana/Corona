package com.baf.bafcoronainfo.model;

public class HelpModel {
    private String base = "";
    private String appoinment_name = "";
    private String mobile_no = "";

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getAppoinment_name() {
        return appoinment_name;
    }

    public void setAppoinment_name(String appoinment_name) {
        this.appoinment_name = appoinment_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    @Override
    public String toString() {
        return "HelpModel{" +
                "base='" + base + '\'' +
                ", appoinment_name='" + appoinment_name + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                '}';
    }
}
