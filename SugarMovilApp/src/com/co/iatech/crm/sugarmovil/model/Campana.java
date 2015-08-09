package com.co.iatech.crm.sugarmovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las campanas.
 */
public class Campana implements Parcelable {

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

    public Campana(String id, String nombre) {
        setId(id);
        setName(nombre);
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
}
