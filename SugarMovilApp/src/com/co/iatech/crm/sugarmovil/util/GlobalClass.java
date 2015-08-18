package com.co.iatech.crm.sugarmovil.util;

import android.app.Application;
import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.co.iatech.crm.sugarmovil.model.User;


public class GlobalClass extends Application {
	
    // Global Variables
    private String mUrl = "http://crmlaumayer.com/movil/CRMLaumayerWS/index.php/";
    private User usuario;
    private int mSelectedButton;
    private String android_id;
    private String device_id;
    
    public GlobalClass(){
    	
    }

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

	public String getAndroidID() {
		return android_id;
	}

	public String getDeviceID() {
		return device_id;
	}

	public void setAndroidID(String android_id) {
		this.android_id = android_id;
	}

	public void setDeviceID(String device_id) {
		this.device_id = device_id;
	}
    
    
}
