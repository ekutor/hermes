package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las cuentas.
 */
public class Cuenta extends GenericBean implements Parcelable {

    public static final Creator<Cuenta> CREATOR
            = new Creator<Cuenta>() {
        public Cuenta createFromParcel(Parcel in) {
            return new Cuenta(in);
        }

        public Cuenta[] newArray(int size) {
            return new Cuenta[size];
        }
    };

    private String name, uen;
    
    public Cuenta() {}
    
    public Cuenta(JSONObject obj) throws JSONException {
        setId(obj.getString("id"));
        setName(validate(obj.getString("name")));
        setUen(validate(obj.getString("uen")));
    }

    public Cuenta(Parcel in) {
        setId(in.readString());
        setName(in.readString());
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

	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
