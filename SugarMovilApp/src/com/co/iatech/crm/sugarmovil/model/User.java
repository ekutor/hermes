package com.co.iatech.crm.sugarmovil.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de los usuarios.
 */
public class User implements Parcelable {

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
    	setAuthenticate(obj.getString("auth"));
        setId(obj.getString("idUsuario"));
        setUser_name(obj.getString("user_name"));
        setFirst_name(obj.getString("first_name"));
        setLast_name(obj.getString("last_name"));
        setIs_admin(obj.getString("is_admin"));
        setUser_hash(obj.getString("hash"));
       
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
    
}
