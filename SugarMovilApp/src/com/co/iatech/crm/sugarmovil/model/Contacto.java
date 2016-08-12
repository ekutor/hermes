package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de los contactos.
 */
public class Contacto extends GenericBean implements Parcelable {

    public static final Creator<Contacto> CREATOR
            = new Creator<Contacto>() {
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };
    
    public Contacto(){}
    
    private String first_name, title, phone_mobile, phone_work, phone_other, phone_fax, account_id;
    
    public Contacto(JSONObject obj) throws JSONException {
    	setId(validate(obj.getString("id")));
        setName(validate(obj.getString("first_name")));
        setTitulo(validate(obj.getString("title")));
        setPhone_mobile(validate(obj.getString("phone_mobile")));
        setTelefonoTrabajo(validate(obj.getString("phone_work")));
        setTelefonoOtro(validate(obj.getString("phone_other")));
        setTelefonoFax(validate(obj.getString("phone_fax")));
        setAccount_id(validate(obj.getString("account_id")));
        
    }

    public Contacto(Parcel in) {
    	setId(validate(in.readString()));
        setName(validate(in.readString()));
        setTitulo(validate(in.readString()));
        setPhone_mobile(validate(in.readString()));
        setTelefonoTrabajo(validate(in.readString()));
        setTelefonoOtro(validate(in.readString()));
        setTelefonoFax(validate(in.readString()));
        setAccount_id(validate(in.readString()));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getId());
        dest.writeString(getName());
        dest.writeString(getTitulo());
        dest.writeString(getPhone_mobile());
        dest.writeString(getTelefonoTrabajo());
        dest.writeString(getTelefonoOtro());
        dest.writeString(getTelefonoFax());
        dest.writeString(getAccount_id());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getName() {
		 return first_name;
	}

	@Override
	public void setName(String name) {
		 first_name = name;
		
	}
}

