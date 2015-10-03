package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de los usuarios.
 */
public class User extends GenericBean implements Parcelable {

    public static final Creator<User> CREATOR
            = new Creator<User>() {
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


    public User(JSONObject obj) throws JSONException {
    	
        setId(validate(obj.getString("id")));
        setUser_name(validate(obj.getString("user_name")));
        setFirst_name(validate(obj.getString("first_name")));
        setLast_name(validate(obj.getString("last_name")));
        setIs_admin(validate(obj.getString("is_admin")));
        try{
	        setAuthenticate(obj.getString("auth"));
	        setUser_hash(obj.getString("hash"));
        }catch(Exception ne){
        	setAuthenticate(false);
        	setUser_hash("");
        }
       
    }
    public User() {       
    }
    
    public static User createUserProof()  {
    	User u = new User();
    	u.setAuthenticate(true);
        u.setId("93ac3a3f-2e2d-41fd-e78c-55e669e0fe32");
        u.setUser_name("");
        u.setFirst_name("Luz Estella");
        u.setLast_name("Rodriguez R");
        u.setIs_admin("0");
        u.setUser_hash("7b6ac3e5c197bca87b13ced8b200c96a");
        return u;
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
    	if("true".equalsIgnoreCase(authenticate_id)){
    		this.authenticate = true;
    	}else{
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
	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
