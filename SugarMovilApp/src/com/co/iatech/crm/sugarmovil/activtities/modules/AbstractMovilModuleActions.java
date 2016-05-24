package com.co.iatech.crm.sugarmovil.activtities.modules;

import com.co.iatech.crm.sugarmovil.activities.tasks.GenericTask;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.software.shell.fab.ActionButton;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

public abstract class AbstractMovilModuleActions extends AppCompatActivity implements IMovilModuleActions{
	
	protected ImageButton imgButtonEdit;
	protected static final String RESPONSE_TEXT_CORECT_ID = "results";
	protected ActualInfo  actualInfo = new ActualInfo();
	
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
	    	Intent intent = getIntent();
	    	Modules fromModule = Modules.getModulefromDBName(intent.getStringExtra(Modules.PREVIOUS_UI.name()));
	    	
	    	if(fromModule != null){
	    		String id = intent.getStringExtra( fromModule.name() );
	    		actualInfo = new ActualInfo(fromModule, id);
	    	}
			
	 }

protected class ActualInfo{
	private Modules actualParentModule;
	private String actualParentId;
	private String actualModuleId;
	
	ActualInfo(){
		actualParentModule = Modules.PREVIOUS_UI;
	}
	
	ActualInfo(Modules actualParentModule,String actualParentId ){
		this.actualParentModule = actualParentModule;
		this.actualParentId  = actualParentId;
	}

	public Modules getActualParentModule() {
		return actualParentModule;
	}

	public void setActualParentModule(Modules actualParentModule) {
		this.actualParentModule = actualParentModule;
	}

	public String getActualParentId() {
		return actualParentId;
	}

	public void setActualParentId(String actualParentId) {
		this.actualParentId = actualParentId;
	}

	public String getActualModuleId() {
		return actualModuleId;
	}

	public void setActualModuleId(String actualModuleId) {
		this.actualModuleId = actualModuleId;
	}
	
	
}
	
}