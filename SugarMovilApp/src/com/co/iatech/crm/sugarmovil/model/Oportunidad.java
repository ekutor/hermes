package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import com.co.iatech.crm.sugarmovil.activities.listeners.DataVisitor;
import com.co.iatech.crm.sugarmovil.activities.listeners.Visitable;

/**
 * Representa un objeto parcelable para el manejo de las oportunidades.
 */
public class Oportunidad extends GenericBean implements Parcelable, Visitable {

    public static final Creator<Oportunidad> CREATOR
            = new Creator<Oportunidad>() {
        public Oportunidad createFromParcel(Parcel in) {
            return new Oportunidad(in);
        }

        public Oportunidad[] newArray(int size) {
            return new Oportunidad[size];
        }
    };

    private String name;

    public Oportunidad(JSONObject obj ) throws JSONException {
        setId(validate(obj.getString("id")));
        setName(validate(obj.getString("name")));
    }
    
    public Oportunidad(OportunidadDetalle obj ) throws JSONException {
        setId(obj.getId_c());
        setName(obj.getName());
    }

    public Oportunidad(Parcel in) {
        setId(validate(in.readString()));
        setName(validate(in.readString()));
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

	@Override
	public void accept(DataVisitor visitor) {
		visitor.add(this);
	}
}
