package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las campanas.
 */
public class Campana extends GenericBean implements Parcelable {

    public static final Creator<Campana> CREATOR
            = new Creator<Campana>() {
        public Campana createFromParcel(Parcel in) {
            return new Campana(in);
        }

        public Campana[] newArray(int size) {
            return new Campana[size];
        }
    };

    private String id, name;

    public Campana(JSONObject obj) throws JSONException {
    	 setId(validate(obj.getString("id")));
         setName(validate(obj.getString("name")));
    }

    public Campana(Parcel in) {
        setId(in.readString());
        setName(in.readString());
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

	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}
}
