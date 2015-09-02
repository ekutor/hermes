package com.co.iatech.crm.sugarmovil.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de los productos.
 */
public class Producto extends GenericBean implements Parcelable {

    public static final Creator<Producto> CREATOR
            = new Creator<Producto>() {
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    private String id, name, saldo;

    public Producto(JSONObject obj) throws JSONException {
        setId(validate(obj.getString("id")));
        setName( validate (obj.getString("name")));
        setSaldo( validate (obj.getString("saldo")));
    }

    public Producto(Parcel in) {
        setId(in.readString());
        setName(in.readString());
        setSaldo( in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getId());
        dest.writeString(getName());
        dest.writeString(getSaldo());
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

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
    
    
}
