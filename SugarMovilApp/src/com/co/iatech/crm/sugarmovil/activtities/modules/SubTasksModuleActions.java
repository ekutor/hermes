package com.co.iatech.crm.sugarmovil.activtities.modules;

import com.co.iatech.crm.sugarmovil.model.DetailSubTask;

import android.os.Parcelable;

public abstract class SubTasksModuleActions extends AbstractMovilModuleActions implements SubTasksModule {
	protected DetailSubTask selectedBean;

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
}
