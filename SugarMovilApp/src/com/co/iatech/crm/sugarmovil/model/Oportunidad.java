package com.co.iatech.crm.sugarmovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las oportunidades.
 */
public class Oportunidad implements Parcelable {

    public static final Creator<Oportunidad> CREATOR
            = new Creator<Oportunidad>() {
        public Oportunidad createFromParcel(Parcel in) {
            return new Oportunidad(in);
        }

        public Oportunidad[] newArray(int size) {
            return new Oportunidad[size];
        }
    };

    private String id, name;

    public Oportunidad(String id, String name) {
        setId(id);
        setName(name);
    }

    public Oportunidad(Parcel in) {
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
}
