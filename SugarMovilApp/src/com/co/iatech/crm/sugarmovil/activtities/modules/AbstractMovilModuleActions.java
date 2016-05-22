package com.co.iatech.crm.sugarmovil.activtities.modules;

import com.co.iatech.crm.sugarmovil.activities.tasks.GenericTask;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.software.shell.fab.ActionButton;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

public abstract class AbstractMovilModuleActions extends AppCompatActivity implements IMovilModuleActions{
	
	protected ImageButton imgButtonEdit;
	protected static final String RESPONSE_TEXT_CORECT_ID = "results";
	
	public abstract void applyActions();
	
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
		return imgButtonEdit;
	}
	
	/**
	 * Metodo para ejecutar tareas genericas asincronas
	 */
	public void executeTask(String[] params, TypeInfoServer typeInfo){
		this.executeTask(params, typeInfo, null);
	}
	
	public void executeTask(String[] params, TypeInfoServer typeInfo, String userMessage){
		GenericTask task = new GenericTask(this, typeInfo, userMessage);
  		task.execute(params);
	}

}
