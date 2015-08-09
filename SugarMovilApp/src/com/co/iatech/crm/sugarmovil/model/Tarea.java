package com.co.iatech.crm.sugarmovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las tareas.
 */
public class Tarea implements Parcelable {

    public static final Creator<Tarea> CREATOR
            = new Creator<Tarea>() {
        public Tarea createFromParcel(Parcel in) {
            return new Tarea(in);
        }

        public Tarea[] newArray(int size) {
            return new Tarea[size];
        }
    };

    private String id, name;

    public Tarea(String id, String name) {
        setId(id);
        setName(name);
    }

    public Tarea(Parcel in) {
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
