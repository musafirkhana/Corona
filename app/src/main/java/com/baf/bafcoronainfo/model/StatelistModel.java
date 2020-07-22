package com.baf.bafcoronainfo.model;

public class StatelistModel {
    private String id = "";
    private String present_state = "";
    private String recovered = "";
    private String death = "";
    private String cmh = "";
    private String isolation = "";
    private String home_quarantine = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPresent_state() {
        return present_state;
    }

    public void setPresent_state(String present_state) {
        this.present_state = present_state;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getCmh() {
        return cmh;
    }

    public void setCmh(String cmh) {
        this.cmh = cmh;
    }

    public String getIsolation() {
        return isolation;
    }

    public void setIsolation(String isolation) {
        this.isolation = isolation;
    }

    public String getHome_quarantine() {
        return home_quarantine;
    }

    public void setHome_quarantine(String home_quarantine) {
        this.home_quarantine = home_quarantine;
    }



    @Override
    public String toString() {
        return "StatelistModel{" +
                "id='" + id + '\'' +
                ", present_state='" + present_state + '\'' +
                ", recovered='" + recovered + '\'' +
                ", death='" + death + '\'' +
                ", cmh='" + cmh + '\'' +
                ", isolation='" + isolation + '\'' +
                ", home_quarantine='" + home_quarantine + '\'' +
                '}';
    }
}
