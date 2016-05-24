package com.co.iatech.crm.sugarmovil.activities;

import java.util.HashMap;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

/**
 * clase que implementa el Patron Mediator
 * @author hectsaga
 *
 */
public class ActivitiesMediator implements IMediator {
	
	private Modules actualModule;
	private String previusID;
	private Parcelable beanInfo;
	private static ActivitiesMediator instance;
	private Map<Modules,String> currentIDs;
	private Modules lastModuleFrom;

	private ActivitiesMediator(){
		currentIDs = new HashMap<Modules,String>();
	}
	
	public static ActivitiesMediator getInstance(){
		if(instance == null){
			instance = new ActivitiesMediator();
		}
		return instance;
	}


	@Override
	public void defineActualModule(Modules module) {
		this.actualModule = module;
	}

	/**
	 * carga el actual ID del modulo a desplegar 
	 */
	@Override
	public void showActivity(Context context, Modules moduleToStart, String newActualID) {
		setActualID(newActualID, moduleToStart);
		Intent intent = null;
		switch( moduleToStart){
			case ACCOUNTS:
				intent = new Intent(context, AccountActivity.class);
				break;
			case OPPORTUNITIES:
				intent = new Intent(context, OpportunityActivity.class);
				break;
			case TASKS:
				intent = new Intent(context, TaskActivity.class);
				break;
			case CALLS:
				intent = new Intent(context, CallActivity.class);
				break;
			case CONTACTS:
				intent = new Intent(context, ContactActivity.class);
				break;
		default:
			break;
		}
		//set current module id to activity
		this.addInfotoActivity(intent, moduleToStart);
		chargeLastModuleCaller(intent);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	context.startActivity(intent);
		
	}
	private void chargeLastModuleCaller(Intent intent) {
		if(lastModuleFrom != null){
			//set actual module from
			intent.putExtra(Modules.PREVIOUS_UI.name(), lastModuleFrom.getSugarDBName());
			this.addInfotoActivity(intent, lastModuleFrom);
		}
		
	}

	@Override
	public void showEditActivity(Context context, Modules targetView, boolean addActualModule) {
		//aqui se debe poner logica para saber que viene de otra vista igual que en la de show view
		Intent intent = null;     
		switch( targetView){
			case ACCOUNTS:
				break;
			case OPPORTUNITIES:
				intent = new Intent(context, AddOpportunityActivity.class);
				break;
			case TASKS:
				intent = new Intent(context, AddTaskActivity.class);
				break;
			case CALLS:
				intent = new Intent(context, AddCallActivity.class);
				break;
		default:
			break;
		}
		if(addActualModule){
			addInfotoActivity(intent, actualModule);
		}
		chargeLastModuleCaller(intent);
		addBeanInfo(intent, targetView );
		addInfotoActivity(intent, targetView);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	context.startActivity(intent);
		
	}
	
	private void addBeanInfo(Intent intent , Modules module) {
		 if(intent != null){
			 intent.putExtra(module.getModuleName(), beanInfo);
		 }
		
	}

	public void showList(Context context, Modules viewtoStart, Modules viewCaller ,boolean chargeActualModule) {
		try{
		Intent intent = null;
		lastModuleFrom = viewCaller;
		switch( viewtoStart){
			case CONTACTS:
				intent = new Intent(context, ListContactActivity.class);
				break;
			case OPPORTUNITIES:
				intent = new Intent(context, ListOpportunityActivity.class);
				break;
			case TASKS:
				intent = new Intent(context, ListTasksActivity.class);
				break;
			case CALLS:
				intent = new Intent(context, ListCallsActivity.class);
				break;
		default:
			break;
			
		}
		if(chargeActualModule){
			//set current module id to activity
			this.addInfotoActivity(intent, actualModule);
			chargeLastModuleCaller(intent);
		}
		
    	context.startActivity(intent);
		}catch(Exception e){
			Message.showShortExt(Utils.errorToString(e) , context);
		}
		
	}
	
	/**
	 * carga el actual ID del modulo pasado como parametro 
	 */
	@Override
	public void addInfotoActivity(Intent intent, Modules mod){
		if(intent != null){
			intent.putExtra(mod.name(), currentIDs.get(mod));
		}
	}
	
	
	@Override
	public void returnView() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public String getPreviusID() {
		return previusID;
	}


	public void addObjectInfo(Parcelable beanInfo) {
		this.beanInfo =  beanInfo;
	}
	
	public Parcelable getBeanInfo() {
		return beanInfo;
	}

	public void deleteSelectedBean(){
		this.beanInfo = null;
	}

	@Override
	public void setActualID(String actualID, Modules module) {
		if(actualID != null){
			this.previusID = currentIDs.get(module);
			currentIDs.put(module, actualID);
		}
	}
	public void setActualViewCaller(String actualIDViewCaller, Modules module) {
		if(actualIDViewCaller != null){
			currentIDs.put(module, actualIDViewCaller);
			this.lastModuleFrom = module;
		}
	}


	@Override
	public String getActualID(Modules module) {
		return currentIDs.get(module);
	}	
	

}
