package com.co.iatech.crm.sugarmovil.activtities.modules;

import com.co.iatech.crm.sugarmovil.model.ContactoDetalle;
import com.software.shell.fab.ActionButton;

import android.os.Parcelable;
import android.widget.ImageButton;

public abstract class ContactsModuleActions extends AbstractMovilModuleActions implements ContactsModule{
	protected ContactoDetalle selectedBean;
	
	@Override
	public Modules getModule() {
		return MODULE;
	}
	
	@Override
	public ImageButton getEditButton() {
		return null;
	}
	
	@Override
	public ActionButton getActionButton() {
		return null;
	}

	@Override
	public String getAssignedUser() {
		return selectedBean.getAssigned_user_id();
	}

	@Override
	public Parcelable getBean() {
		return selectedBean;
	}

}
