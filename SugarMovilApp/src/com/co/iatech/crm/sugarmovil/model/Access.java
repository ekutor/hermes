package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las campanas.
 */
public class Access extends GenericBean implements Parcelable {


    private String role, aclaccess,action,category,acltype,access_type;
    private boolean access_override;

    public Access(JSONObject obj) throws JSONException {
    	setAclaccess(validate(obj.getString("aclaccess")));
    	setAction(validate(obj.getString("action")));
    	setCategory(validate(obj.getString("category")));
    	setAccess_override(obj.getBoolean("access_override"));
    	setAccess_type(validate(obj.getString("access_type")));
    	
    }

    public Access(Parcel in) {
        setAclaccess(in.readString());
    	setAction(in.readString());
    	setCategory(in.readString());
    	
    	setAccess_type(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
       
    }

 

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAclaccess() {
		return aclaccess;
	}

	public void setAclaccess(String aclaccess) {
		this.aclaccess = aclaccess;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAcltype() {
		return acltype;
	}

	public void setAcltype(String acltype) {
		this.acltype = acltype;
	}

	public boolean getAccess_override() {
		return access_override;
	}

	public void setAccess_override(boolean access_override) {
		this.access_override = access_override;
	}

	public String getAccess_type() {
		return access_type;
	}

	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "Not Implemented";
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
}
