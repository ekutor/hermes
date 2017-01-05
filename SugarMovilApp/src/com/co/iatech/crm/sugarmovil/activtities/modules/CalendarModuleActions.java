package com.co.iatech.crm.sugarmovil.activtities.modules;

import com.co.iatech.crm.sugarmovil.model.Meeting;

import android.os.Parcelable;

public abstract class CalendarModuleActions extends AbstractMovilModuleActions implements CalendarModule {
	protected Meeting selectedBean;

	@Override
	public Modules getModule() {
		return MODULE;
	}

	@Override
	public String getAssignedUser() {
		return selectedBean.getUserId();
	}

	@Override
	public Parcelable getBean() {
		return selectedBean;
	}
	
	@Override
	public boolean chargeIdPreviousModule() {
		return true;
	}
}
