package com.co.iatech.crm.sugarmovil.util;

import com.co.iatech.crm.sugarmovil.model.User;

import android.app.Application;


public class GlobalClass extends Application {
	
    private User usuarioAutenticado;
    private String androidId, deviceId;
    private int selectedItem;
    
    public GlobalClass(){
    	
    }	
    
    
	public int getSelectedItem() {
		return selectedItem;
	}


	public void setSelectedItem(int selectedItem) {
		this.selectedItem = selectedItem;
	}


	public User getUsuarioAutenticado() {
		return usuarioAutenticado;
	}


	public void setUsuarioAutenticado(User usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}


	public String getAndroidId() {
		return androidId;
	}


	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}


	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public void setAuthParams(String android_id, String device_id) {
		deviceId = device_id;
		androidId = android_id;
	}
    
    

}
