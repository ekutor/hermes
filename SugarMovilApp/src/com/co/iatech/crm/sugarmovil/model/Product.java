package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de los productos.
 */
public class Product extends GenericBean implements Parcelable {

    public static final Creator<Product> CREATOR
            = new Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    private String name;

    public Product(JSONObject obj) throws JSONException {
        setId(validate(obj.getString("id")));
        setName( validate (obj.getString("name")));
    }

    public Product(Parcel in) {
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
