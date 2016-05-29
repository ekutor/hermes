package com.co.iatech.crm.sugarmovil.fragments;

import com.co.iatech.crm.sugarmovil.activtities.modules.IMovilModuleActions;
import com.software.shell.fab.ActionButton;

import android.app.Fragment;
import android.os.Parcelable;
import android.widget.ImageButton;

public abstract class FragmentsModules extends Fragment implements IMovilModuleActions {

	@Override
	public ActionButton getActionButton() {
		return null;
	}

	@Override
	public ImageButton getEditButton() {
		return null;
	}

	@Override
	public String getAssignedUser() {
		return "";
	}

	@Override
	public Parcelable getBean() {
		return null;
	}

	@Override
	public boolean chargeIdPreviousModule() {
		return false;
	}

	@Override
	public void getInfoFromMediator() {
		
	}


}
