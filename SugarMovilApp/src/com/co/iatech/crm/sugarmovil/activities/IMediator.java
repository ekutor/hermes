package com.co.iatech.crm.sugarmovil.activities;

import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;

import android.content.Context;
import android.content.Intent;

public interface IMediator {

	public void defineActualModule(Modules module);
	public void returnView();
	public void addInfotoActivity(Intent intent, Modules mod);
	public void showEditActivity(Context context, Modules module,  boolean editMode);
	public ActivityBeanCommunicator getActualID(Modules module);
	public void setActualID(ActivityBeanCommunicator actualID, Modules mod);
	public ActivityBeanCommunicator getPreviusID();
	void showActivity(Context context, Modules module, ActivityBeanCommunicator newActualID);
	void removeActualID(Modules module);
	
}
