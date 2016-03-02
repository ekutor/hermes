package com.co.iatech.crm.sugarmovil.activities;

import android.content.Context;
import android.content.Intent;

import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;

public interface IMediator {

	public void defineActualModule(Modules module);
	public void showActivity(Context context,Modules module);
	public void returnView();
	public void addInfotoActivity(Intent intent);
	public void showEditActivity(Context context, Modules module);
	public String getActualKey();
	public String getActualID();
	public void setActualID(String actualID);
	public String getPreviusID();
	
}
