package com.co.iatech.crm.sugarmovil.activtities.modules;

import com.software.shell.fab.ActionButton;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

public abstract class AbstractMovilModuleActions extends AppCompatActivity implements IMovilModuleActions{
	
	public abstract void applyActions();
	
	protected static final String RESPONSE_TEXT_CORECT_ID = "results";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAssignedUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parcelable getBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
