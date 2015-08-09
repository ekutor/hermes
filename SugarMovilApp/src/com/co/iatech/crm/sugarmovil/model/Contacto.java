package com.co.iatech.crm.sugarmovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de los contactos.
 */
public class Contacto implements Parcelable {

    public static final Creator<Contacto> CREATOR
            = new Creator<Contacto>() {
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    private String id, first_name, title, phone_mobile, phone_work, phone_other, phone_fax;

    public Contacto(String id, String first_name, String title, String phone_mobile, String phone_work, String phone_other, String phone_fax) {
        setId(id);
        setNombre(first_name);
        setTitulo(title);
        setPhone_mobile(phone_mobile);
        setTelefonoTrabajo(phone_work);
        setTelefonoOtro(phone_other);
        setTelefonoFax(phone_fax);
    }

    public Contacto(Parcel in) {
        setId(in.readString());
        setNombre(in.readString());
        setTitulo(in.readString());
        setPhone_mobile(in.readString());
        setTelefonoTrabajo(in.readString());
        setTelefonoOtro(in.readString());
        setTelefonoFax(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getId());
        dest.writeString(getNombre());
        dest.writeString(getTitulo());
        dest.writeString(getPhone_mobile());
        dest.writeString(getTelefonoTrabajo());
        dest.writeString(getTelefonoOtro());
        dest.writeString(getTelefonoFax());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return first_name;
    }

    public void setNombre(String nombre) {
        this.first_name = nombre;
    }

    public String getTitulo() {
        return title;
    }

    public void setTitulo(String titulo) {
        this.title = titulo;
    }

    public String getPhone_mobile() {
        return phone_mobile;
    }

    public void setPhone_mobile(String phone_mobile) {
        this.phone_mobile = phone_mobile;
    }

    public String getTelefonoTrabajo() {
        return phone_work;
    }

    public void setTelefonoTrabajo(String telefonoTrabajo) {
        this.phone_work = telefonoTrabajo;
    }

    public String getTelefonoOtro() {
        return phone_other;
    }

    public void setTelefonoOtro(String telefonoOtro) {
        this.phone_other = telefonoOtro;
    }

    public String getTelefonoFax() {
        return phone_fax;
    }

    public void setTelefonoFax(String telefonoFax) {
        this.phone_fax = telefonoFax;
    }
}

