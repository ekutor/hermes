package com.co.iatech.crm.sugarmovil.activtities.modules;

import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activities.tasks.GenericTask;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.util.Utils;
import com.software.shell.fab.ActionButton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

public abstract class AbstractMovilModuleActions extends AppCompatActivity implements IMovilModuleActions{
	
	protected ImageButton imgButtonEdit;
	protected static final String RESPONSE_TEXT_CORECT_ID = "results";
	protected ActualInfo  actualInfo;
	protected boolean isEditMode;
	protected ActivityBeanCommunicator beanCommunicator;
	
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
	
	 public void getInfoFromMediator() {
		try{
			Intent intent = getIntent();
	    	Modules fromModule = Modules.getModulefromDBName(intent.getStringExtra(Modules.PREVIOUS_UI.name()));
	    	
	    	actualInfo= new ActualInfo();
	    	isEditMode = intent.getBooleanExtra(ActivitiesMediator.EDIT_MODE, false);
	    	ActivityBeanCommunicator info = null;
	    	if(fromModule != null){
	    		info = intent.getParcelableExtra( fromModule.name() );
	    		actualInfo = new ActualInfo(fromModule, info);
	    	}
	    	Modules principalModule = ActivitiesMediator.getInstance().getActualModule();
	    	info = intent.getParcelableExtra(principalModule.name());
	    	actualInfo.setActualPrincipalModule(principalModule);
	    	actualInfo.setActualPrincipalInfo(info);
		}catch(Exception e){
			Message.showShortExt(Utils.errorToString(e), this);
		}
    	
	 }
	
}