package com.co.iatech.crm.sugarmovil.activities;

import java.util.HashMap;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
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
	private ActivityBeanCommunicator previusID;
	private Parcelable beanInfo;
	private static ActivitiesMediator instance;
	private Map<Modules,ActivityBeanCommunicator> currentIDs;
	private Modules lastModuleFrom;
	private GenericBean parentBean;
	public static final String EDIT_MODE = "MODE";

	private ActivitiesMediator(){
		currentIDs = new HashMap<Modules,ActivityBeanCommunicator>();
	}
	
	public static ActivitiesMediator getInstance(){
		if(instance == null){
			instance = new ActivitiesMediator();
		}
		return instance;
	}


	@Override
	public void defineActualModule(Modules module) {
		currentIDs.clear();
		lastModuleFrom = module;
		this.actualModule = module;
	}
	
	public Modules getActualModule() {
		return this.actualModule;
	}
	/**
	 * carga el actual ID del modulo a desplegar 
	 */
	@Override
	public void showActivity(Context context, Modules moduleToStart, ActivityBeanCommunicator newActualID) {
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
			case PRODUCTS:
				intent = new Intent(context, ProductActivity.class);
				break;
			case SUBTASKS:
				intent = new Intent(context, SubTaskActivity.class);
				break;
			case NOTES:
				intent = new Intent(context, NoteActivity.class);
				break;
			case LEADS:
				intent = new Intent(context, LeadActivity.class);
				break;
		default:
			break;
		}
		//set current module id to activity
		this.addInfotoActivity(intent, moduleToStart);
		chargeLastModuleCaller(intent);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//Message.showShortExt(" Show "+actualModule + newActualID + moduleToStart.name() + " last "+lastModuleFrom, context);
    	context.startActivity(intent);
		
	}
	private void chargeLastModuleCaller(Intent intent) {
		this.addInfotoActivity(intent, actualModule);
		if(lastModuleFrom != null){
			//set actual module from
			intent.putExtra(Modules.PREVIOUS_UI.name(), lastModuleFrom.getSugarDBName());
			this.addInfotoActivity(intent, lastModuleFrom);
		}
		
	}

	@Override
	public void showEditActivity(Context context, Modules targetView,boolean editMode) {
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
			case SUBTASKS:
				intent = new Intent(context, AddSubTaskActivity.class);
				break;
			case NOTES:
				intent = new Intent(context, AddNoteActivity.class);
				break;
			case LEADS:
				intent = new Intent(context, AddLeadActivity.class);
				break;

		}
		//si es nulo estoy en un fragment y voy a mostrar una nueva pantalla del mismo modulo
		if(lastModuleFrom == null){
			lastModuleFrom = targetView;
		}
		intent.putExtra(EDIT_MODE, editMode);
		chargeLastModuleCaller(intent);
		addBeanInfo(intent, targetView );
		addInfotoActivity(intent, targetView);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//Message.showShortExt("Edit "+actualModule + addActualModule + targetView.name() + " last "+lastModuleFrom , context);
	    
    	context.startActivity(intent);
		
	}
	
	private void addBeanInfo(Intent intent , Modules module) {
		 if(intent != null){
			 intent.putExtra(module.getModuleName(), beanInfo);
		 }
		
	}

	public void showList(Context context, Modules viewtoStart, Modules viewCaller) {
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
			case SUBTASKS:
				intent = new Intent(context, ListSubTasksActivity.class);
				break;
			case NOTES:
				intent = new Intent(context, ListNotesActivity.class);
				break;
			case LEADS:
				intent = new Intent(context, ListLeadsActivity.class);
				break;
		default:
			break;
			
		}

		chargeLastModuleCaller(intent);
		//Message.showShortExt("actual Module "+actualModule + chargeActualModule + viewCaller.name() + viewtoStart.name()+ " last "+lastModuleFrom, context);
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
		try{
			if(intent != null){
				intent.putExtra(mod.name(), currentIDs.get(mod));
			}
		}catch(Exception e){
			
		}
	}
	
	
	@Override
	public void returnView() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public ActivityBeanCommunicator getPreviusID() {
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
	public void setActualID(ActivityBeanCommunicator actualID, Modules module) {
		if(actualID != null){
			this.previusID = currentIDs.get(module);
			currentIDs.put(module, actualID);
		}
	}

	@Override
	public ActivityBeanCommunicator getActualID(Modules module) {
		return currentIDs.get(module);
	}
	
	@Override
	public void removeActualID(Modules module) {
		currentIDs.remove(module);
	}
	
	
	public GenericBean getParentBean() {
		return parentBean;
	}

	public void setParentBean(GenericBean parentBean) {
		this.parentBean = parentBean;
	}

}
