package com.co.iatech.crm.sugarmovil.activities;

import android.content.Context;
import android.content.Intent;

import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;

public interface IMediator {

	public void defineActualModule(Modules module);
	public void returnView();
	public void addInfotoActivity(Intent intent, Modules mod);
	public void showEditActivity(Context context, Modules module,  boolean addActualModule,  boolean editMode);
	public String getActualID(Modules module);
	public void setActualID(String actualID, Modules mod);
	public String getPreviusID();
	void showActivity(Context context, Modules module, String newActualID);
	
}
