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
	/**
	 * metodo para recibir la respuesta de las tareas asincronas
	 * @param serverResp
	 */
	void addInfo(String serverResp);
	void chargeViewInfo();
}
