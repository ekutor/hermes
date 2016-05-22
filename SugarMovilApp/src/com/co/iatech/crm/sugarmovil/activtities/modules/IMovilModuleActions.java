package com.co.iatech.crm.sugarmovil.activtities.modules;

import com.software.shell.fab.ActionButton;

import android.app.Activity;
import android.os.Parcelable;
import android.widget.ImageButton;


public interface IMovilModuleActions extends MovilModule{
	ActionButton getActionButton();
	ImageButton getEditButton();
	Modules getModule();
	String getAssignedUser();
	Parcelable getBean();
	boolean chargeIdPreviousModule();
	void addInfo(String serverResp);
}
