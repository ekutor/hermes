package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las cuentas.
 */
public class Account extends GenericBean implements Parcelable {

    public static final Creator<Account> CREATOR
            = new Creator<Account>() {
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    private String name, uen, nit;
    
    public Account() {}
    
    public Account(JSONObject obj) throws JSONException {
        setId(obj.getString("id"));
        setName(validate(obj.getString("name")));
        setNit(validate(obj.getString("nit_c")));
        setUen(validate(obj.getString("uen")));
    }

    public Account(Parcel in) {
        setId(in.readString());
        setName(in.readString());
        setNit(in.readString());
        setUen(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getId());
        dest.writeString(getName());
        dest.writeString(getNit());
        dest.writeString(getUen());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getUen() {
		return uen;
	}

	public void setUen(String uen) {
		this.uen = uen;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
