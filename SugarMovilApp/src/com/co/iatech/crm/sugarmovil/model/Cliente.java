package com.co.iatech.crm.sugarmovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de los clientes.
 */
public class Cliente implements Parcelable {

    public static final Creator<Cliente> CREATOR
            = new Creator<Cliente>() {
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    private String id, first_name, last_name;

    public Cliente(String id, String nombre, String apellido) {
        setId(id);
        setFirst_name(nombre);
        setLast_name(apellido);
    }

    public Cliente(Parcel in) {
        setId(in.readString());
        setFirst_name(in.readString());
        setLast_name(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getId());
        dest.writeString(getFirst_name());
        dest.writeString(getLast_name());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
