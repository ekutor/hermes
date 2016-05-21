package com.co.iatech.crm.sugarmovil.activtities.modules;

import android.os.Parcelable;
import android.widget.ImageButton;

import com.software.shell.fab.ActionButton;


public interface IMovilModuleActions extends MovilModule{
	ActionButton getActionButton();
	ImageButton getEditButton();
	Modules getModule();
	String getAssignedUser();
	Parcelable getBean();
	boolean chargeIdPreviousModule();
}
