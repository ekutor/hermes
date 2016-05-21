package com.co.iatech.crm.sugarmovil.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.core.acl.Actions;
import com.co.iatech.crm.sugarmovil.core.acl.ActionsInfo;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;

/**
 * Representa un objeto parcelable para el manejo de los usuarios.
 */
public class User extends GenericBean implements Parcelable {

	public static final Creator<User> CREATOR = new Creator<User>() {
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		public User[] newArray(int size) {
			return new User[size];
		}
	};
	
	private String id;
	private String user_name;
	private String user_hash;
	private String first_name;
	private String last_name;
	private boolean authenticate;
	private String is_admin;
	private String role;
	private Map<Modules, ActionsInfo> access = new HashMap<Modules, ActionsInfo>();

	public User(JSONObject obj) throws JSONException {
		
		setId(validate(obj.getString("id")));
		setUser_name(validate(obj.getString("user_name")));
		setFirst_name(validate(obj.getString("first_name")));
		setLast_name(validate(obj.getString("last_name")));
		setIs_admin(validate(obj.getString("is_admin")));
		setRole(validate(obj.getString("role")));
		Modules actualModule = null;
		try {
			setAuthenticate(obj.getString("auth"));
			
			JSONArray jArr = obj.getJSONArray("access");
			
			for (int i = 0; i < jArr.length(); i++) {
				JSONObject objAccess = jArr.getJSONObject(i);
				Access ac = new Access(objAccess);
				Log.d("category", ac.getCategory() );
				actualModule = Modules.getModulefromDBName(ac.getCategory());
				Log.d("actualModule", "" + actualModule );
				if(actualModule == null){
					continue;
				}
				ActionsInfo actualBean = access.get(actualModule);
				if (actualBean == null) {
					actualBean = new ActionsInfo();
				}
				if (ac.getAccess_override()) {
					Log.d("accessOverride", ac.getAction()+ " "+ac.getAccess_type() );
					actualBean.addAction(Actions.getAction(ac.getAction()),
							TypeActions.getType(ac.getAccess_type()));
				}
				
				access.put(actualModule, actualBean);
				Log.d("User", "PUT "+actualModule.name()+ " "+actualBean );
			}
			setUser_hash(obj.getString("hash"));

			
		} catch (Exception ne) {
			ne.printStackTrace();
			setAuthenticate(false);
			setUser_hash("");
			//ne.printStackTrace();
		}

	}

	public User() {
	}

	public User(Parcel in) {
		setId(in.readString());
		setAuthenticate(in.readString());
		setId(in.readString());
		setUser_name(in.readString());
		setFirst_name(in.readString());
		setLast_name(in.readString());
		setIs_admin(in.readString());
		setUser_hash(in.readString());
		setRole(in.readString());
		//setAccess(in.readMap(access, null));
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(String.valueOf(this.isAuthenticate()));
		dest.writeString(this.getId());
		dest.writeString(this.getUser_name());
		dest.writeString(this.getFirst_name());
		dest.writeString(this.getLast_name());
		dest.writeString(this.getIs_admin());
		dest.writeString(this.getUser_hash());
		dest.writeString(this.getRole());
		//dest.writeMap(access);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_hash() {
		return user_hash;
	}

	public void setUser_hash(String user_hash) {
		this.user_hash = user_hash;
	}

	public boolean isAuthenticate() {
		return authenticate;
	}

	public void setAuthenticate(boolean authenticate_id) {
		this.authenticate = authenticate_id;
	}

	public void setAuthenticate(String authenticate_id) {
		if ("true".equalsIgnoreCase(authenticate_id)) {
			this.authenticate = true;
		} else {
			this.authenticate = false;
		}

	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Modules, ActionsInfo> getAccess() {
		return access;
	}

	public void setAccess(Map<Modules, ActionsInfo> access) {
		this.access = access;
	}
	
	@Override
	public String getName() {
		return first_name + " "+last_name;
	}
}
