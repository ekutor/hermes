package com.co.iatech.crm.sugarmovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de los usuarios.
 */
public class User implements Parcelable {

    public static final Creator<User> CREATOR
            = new Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private String id;
    private String user_name;
    private String user_hash;
    private String system_generated_password;
    private String pwd_last_changed;
    private String authenticate_id;
    private String sugar_login;

    public User(String id, String user_name, String user_hash, String system_generated_password, String pwd_last_changed, String authenticate_id, String sugar_login) {
        setId(id);
        setUser_name(user_name);
        setUser_hash(user_hash);
        setSystem_generated_password(system_generated_password);
        setPwd_last_changed(pwd_last_changed);
        setAuthenticate_id(authenticate_id);
        setSugar_login(sugar_login);
    }

    public User(Parcel in) {
        setId(in.readString());
        setUser_name(in.readString());
        setUser_hash(in.readString());
        setSystem_generated_password(in.readString());
        setPwd_last_changed(in.readString());
        setAuthenticate_id(in.readString());
        setSugar_login(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getId());
        dest.writeString(getUser_name());
        dest.writeString(getUser_hash());
        dest.writeString(getSystem_generated_password());
        dest.writeString(getPwd_last_changed());
        dest.writeString(getAuthenticate_id());
        dest.writeString(getSugar_login());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_hash() {
        return user_hash;
    }

    public void setUser_hash(String user_hash) {
        this.user_hash = user_hash;
    }

    public String getSystem_generated_password() {
        return system_generated_password;
    }

    public void setSystem_generated_password(String system_generated_password) {
        this.system_generated_password = system_generated_password;
    }

    public String getPwd_last_changed() {
        return pwd_last_changed;
    }

    public void setPwd_last_changed(String pwd_last_changed) {
        this.pwd_last_changed = pwd_last_changed;
    }

    public String getAuthenticate_id() {
        return authenticate_id;
    }

    public void setAuthenticate_id(String authenticate_id) {
        this.authenticate_id = authenticate_id;
    }

    public String getSugar_login() {
        return sugar_login;
    }

    public void setSugar_login(String sugar_login) {
        this.sugar_login = sugar_login;
    }
}
