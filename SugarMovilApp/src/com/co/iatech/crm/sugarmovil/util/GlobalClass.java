package com.co.iatech.crm.sugarmovil.util;

import android.app.Application;

import com.co.iatech.crm.sugarmovil.model.User;


public class GlobalClass extends Application {

    // Global Variables
    private String mUrl = "http://crmlaumayer.com/movil/CRMLaumayerWS/index.php/";
    private User usuario;
    private int mSelectedButton;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }


    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public int getmSelectedButton() {
        return mSelectedButton;
    }

    public void setmSelectedButton(int mSelectedButton) {
        this.mSelectedButton = mSelectedButton;
    }
}
