package com.co.iatech.crm.sugarmovil.activtities.modules;

import android.os.Parcel;
import android.os.Parcelable;

public class ActivityBeanCommunicator implements Parcelable{
	public String id;
	public String name;
	public String additionalInfo;
	private Modules module;
	private ActionActivity action;
	public enum ActionActivity {MAKE_CALL, NONE}

    public static final Creator<ActivityBeanCommunicator> CREATOR
            = new Creator<ActivityBeanCommunicator>() {

				@Override
				public ActivityBeanCommunicator createFromParcel(Parcel source) {
					return new ActivityBeanCommunicator(source);
				}

				@Override
				public ActivityBeanCommunicator[] newArray(int size) {
					 return new ActivityBeanCommunicator[size];
				}
       
    };
    

	public ActivityBeanCommunicator(String id, String name) {
		this.id = id;
		this.name = name;
		module  = Modules.ACTUAL_INFO;
		action = ActionActivity.NONE;
	}

	public ActivityBeanCommunicator(Parcel p) {
		id = p.readString();
		name = p.readString();
		action = ActivityBeanCommunicator.getAction(p.readString());
		additionalInfo = p.readString();
	}
	
	
	public Modules getModule() {
		return module;
	}

	public void setModule(Modules module) {
		this.module = module;
	}
	
	public ActionActivity getAction() {
		return action;
	}
	public static ActionActivity getAction(String name) {
		for(ActionActivity act : ActionActivity.values()){
			if(act.name().equals(name)){
				return act;
			}
		}
		return ActionActivity.NONE;
	}

	public void setAction(ActionActivity action) {
		this.action = action;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@Override
	public int describeContents() {
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
	    dest.writeString(name);
	    dest.writeString(action.name());
	    dest.writeString(additionalInfo);
	}
	
	
}
