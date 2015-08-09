package com.co.iatech.crm.sugarmovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las cuentas.
 */
public class Cuenta implements Parcelable {

    public static final Creator<Cuenta> CREATOR
            = new Creator<Cuenta>() {
        public Cuenta createFromParcel(Parcel in) {
            return new Cuenta(in);
        }

        public Cuenta[] newArray(int size) {
            return new Cuenta[size];
        }
    };

    private String id, name;

    public Cuenta(String id, String name) {
        setId(id);
        setName(name);
    }

    public Cuenta(Parcel in) {
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
