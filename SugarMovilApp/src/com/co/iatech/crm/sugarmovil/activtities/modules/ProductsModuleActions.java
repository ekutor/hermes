package com.co.iatech.crm.sugarmovil.activtities.modules;

import com.co.iatech.crm.sugarmovil.model.ProductDetail;
import com.software.shell.fab.ActionButton;

import android.os.Parcelable;
import android.widget.ImageButton;

public abstract class ProductsModuleActions extends AbstractMovilModuleActions implements ProductsModule{
	protected ProductDetail selectedBean;

	@Override
	public Modules getModule() {
		return MODULE;
	}

	@Override
	public String getAssignedUser() {
		return selectedBean.getAssigned_user_id();
	}

	@Override
	public Parcelable getBean() {
		return selectedBean;
	}
	
	@Override
	public boolean chargeIdPreviousModule() {
		return true;
	}
	
	@Override
	public ActionButton getActionButton() {
		return null;
	}

	@Override
	public ImageButton getEditButton() {
		return null;
	}
}
