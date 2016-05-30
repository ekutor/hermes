package com.co.iatech.crm.sugarmovil.activtities.modules;

import android.os.Parcel;
import android.os.Parcelable;

public class ActivityBeanCommunicator implements Parcelable{
	public String id;
	public String name;
	public Modules module;
	

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
	}

	public ActivityBeanCommunicator(Parcel p) {
		id = p.readString();
		name = p.readString();
	}
	
	
	public Modules getModule() {
		return module;
	}

	public void setModule(Modules module) {
		this.module = module;
	}

	@Override
	public int describeContents() {
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
	    dest.writeString(name);
	}
	
	
}
