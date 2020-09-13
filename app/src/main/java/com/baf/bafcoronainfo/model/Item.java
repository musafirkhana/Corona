package com.baf.bafcoronainfo.model;

public class Item {
    private String unit_name = "";
    private String email = "";

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Item(String unitName,String email)
    {
        this.email=email;
        this.unit_name=unitName;
    }
}
