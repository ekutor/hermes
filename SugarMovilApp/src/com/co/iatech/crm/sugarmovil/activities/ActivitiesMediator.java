package com.co.iatech.crm.sugarmovil.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.core.Info;

/**
 * clase que implementa el Patron Mediator
 * @author hectsaga
 *
 */
public class ActivitiesMediator implements IMediator {
	
	private Modules actualModule;
	private String actualID, previusID;
	private Parcelable beanInfo;
	private static ActivitiesMediator instance;
	
	private ActivitiesMediator(){
		
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

	@Override
	public void showActivity(Context context, Modules module) {
		Intent intent = null;
		switch( module){
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
		start(context, intent);
		
	}
	@Override
	public void showEditActivity(Context context, Modules module) {
		Intent intent = null;
		Log.d("ActionMediator ", module.name()+" Click + Show Edit Activity");
		switch( module){
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
		addBeanInfo(intent);
        start(context, intent);
		
	}
	
	private void addBeanInfo(Intent intent) {
		 if(intent != null){
			 intent.putExtra(Info.OBJECT.name(), beanInfo);
		 }
		
	}

	private void start(Context context, Intent intent) {
		 if(intent != null){
	        	addInfotoActivity(intent);
	        	context.startActivity(intent);
		 }
	}

	public void showList(Context context, Modules module) {
		Intent intent = null;
		switch( module){
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
		start(context, intent);
		
	}
	
	@Override
	public void addInfotoActivity(Intent intent){
		intent.putExtra(Info.ID.name(), actualID);
	}
	
	
	@Override
	public void returnView() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getActualKey() {
		String res = "";
		switch( actualModule ){
			case ACCOUNTS:
				res = "idAccount";
			break;
			case OPPORTUNITIES:	
				res = "idAccount";
			break;
			case TASKS:
				res = "idAccount";
			break;
			case CALLS:
				res = "idAccount";
			break;
			default:
				break;
			
		}
		return res;
		
	}
	
	@Override
	public String getActualID() {
		return actualID;
	}
	
	@Override
	public String getPreviusID() {
		return previusID;
	}


	public void addObjectInfo(Parcelable beanInfo) {
		this.beanInfo =  beanInfo;
	}
	
	public void deleteSelectedBean(){
		this.beanInfo = null;
	}

	@Override
	public void setActualID(String actualID) {
		this.previusID = this.actualID;
		this.actualID = actualID;
	}

	public void returnPrevID() {
		this.actualID = previusID;
	}	
	

}
