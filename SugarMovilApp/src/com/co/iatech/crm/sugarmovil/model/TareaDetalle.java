package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de una tarea.
 */
public class TareaDetalle extends GenericBean implements Parcelable {


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TareaDetalle> CREATOR = new Parcelable.Creator<TareaDetalle>() {
        @Override
        public TareaDetalle createFromParcel(Parcel in) {
            return new TareaDetalle(in);
        }

        @Override
        public TareaDetalle[] newArray(int size) {
            return new TareaDetalle[size];
        }
    };
    private String id;
    private String name;
    private String date_entered;
    private String date_modified;
    private String modified_user_id;
    private String created_by;
    private String description;
    private String deleted;
    private String assigned_user_id;
    private String status;
    private String date_due_flag;
    private String date_due;
    private String date_start_flag;
    private String date_start;
    private String parent_type;
    private String parent_id;
    private String contact_id;
    private String priority;
    private String id_c;
    private String aviso_c;
    private String trabajo_estimado_c;
    private String ejecuted_date_c;
    private String assigned_user_name;
    private String contact_name;
    private String parent_name;
    
    public TareaDetalle(){
    	
    }
    
    public TareaDetalle(JSONObject obj) throws JSONException {
		setId(validate(obj.getString("id")));;
	    setName(validate(obj.getString("name")));;
	    setDate_entered(validate(obj.getString("date_entered")));;
	    setDate_modified(validate(obj.getString("date_modified")));;
	    setModified_user_id(validate(obj.getString("modified_user_id")));;
	    setCreated_by(validate(obj.getString("created_by")));;
	    setDescription(validate(obj.getString("description")));;
	    setDeleted(validate(obj.getString("deleted")));;
	    setAssigned_user_id(validate(obj.getString("assigned_user_id")));;
	    setStatus(validate(obj.getString("status")));;
	    setDate_due_flag(validate(obj.getString("date_due_flag")));;
	    setDate_due(validate(obj.getString("date_due")));;
	    setDate_start_flag(validate(obj.getString("date_start_flag")));;
	    setDate_start(validate(obj.getString("date_start")));;
	    setParent_type(validate(obj.getString("parent_type")));;
	    setParent_id(validate(obj.getString("parent_id")));;
	    setContact_id(validate(obj.getString("contact_id")));;
	    setPriority(validate(obj.getString("priority")));;
	    setId_c(validate(obj.getString("id_c")));;
	    setAviso_c(validate(obj.getString("aviso_c")));;
	    setTrabajo_estimado_c(validate(obj.getString("trabajo_estimado_c")));;
	    setEjecuted_date_c(validate(obj.getString("ejecuted_date_c")));;
	    setAssigned_user_name(validate(obj.getString("assigned_user_name")));;
	    setContact_name(validate(obj.getString("contact_name")));;
	    setParent_name(validate(obj.getString("parent_name")));;
    }

    protected TareaDetalle(Parcel in) {
        id = in.readString();
        name = in.readString();
        date_entered = in.readString();
        date_modified = in.readString();
        modified_user_id = in.readString();
        created_by = in.readString();
        description = in.readString();
        deleted = in.readString();
        assigned_user_id = in.readString();
        status = in.readString();
        date_due_flag = in.readString();
        date_due = in.readString();
        date_start_flag = in.readString();
        date_start = in.readString();
        parent_type = in.readString();
        parent_id = in.readString();
        contact_id = in.readString();
        priority = in.readString();
        id_c = in.readString();
        aviso_c = in.readString();
        trabajo_estimado_c = in.readString();
        ejecuted_date_c = in.readString();
        assigned_user_name = in.readString();
        contact_name = in.readString();
        parent_name = in.readString();
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

    public String getDate_entered() {
        return date_entered;
    }

    public void setDate_entered(String date_entered) {
        this.date_entered = date_entered;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public String getModified_user_id() {
        return modified_user_id;
    }

    public void setModified_user_id(String modified_user_id) {
        this.modified_user_id = modified_user_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getAssigned_user_id() {
        return assigned_user_id;
    }

    public void setAssigned_user_id(String assigned_user_id) {
        this.assigned_user_id = assigned_user_id;
    }

    public String getStatus() {
        return status;
    }
    
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate_due_flag() {
        return date_due_flag;
    }

    public void setDate_due_flag(String date_due_flag) {
        this.date_due_flag = date_due_flag;
    }

    public String getDate_due() {
        return date_due;
    }

    public void setDate_due(String date_due) {
        this.date_due = date_due;
    }

    public String getDate_start_flag() {
        return date_start_flag;
    }

    public void setDate_start_flag(String date_start_flag) {
        this.date_start_flag = date_start_flag;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getParent_type() {
        return parent_type;
    }

    public void setParent_type(String parent_type) {
        this.parent_type = parent_type;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }
    
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getId_c() {
        return id_c;
    }

    public void setId_c(String id_c) {
        this.id_c = id_c;
    }

    public String getAviso_c() {
        return aviso_c;
    }

    public void setAviso_c(String aviso_c) {
        this.aviso_c = aviso_c;
    }

    public String getTrabajo_estimado_c() {
        return trabajo_estimado_c;
    }

    public void setTrabajo_estimado_c(String trabajo_estimado_c) {
        this.trabajo_estimado_c = trabajo_estimado_c;
    }

    public String getEjecuted_date_c() {
        return ejecuted_date_c;
    }

    public void setEjecuted_date_c(String ejecuted_date_c) {
        this.ejecuted_date_c = ejecuted_date_c;
    }

    public String getAssigned_user_name() {
        return assigned_user_name;
    }

    public void setAssigned_user_name(String assigned_user_name) {
        this.assigned_user_name = assigned_user_name;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(date_entered);
        dest.writeString(date_modified);
        dest.writeString(modified_user_id);
        dest.writeString(created_by);
        dest.writeString(description);
        dest.writeString(deleted);
        dest.writeString(assigned_user_id);
        dest.writeString(status);
        dest.writeString(date_due_flag);
        dest.writeString(date_due);
        dest.writeString(date_start_flag);
        dest.writeString(date_start);
        dest.writeString(parent_type);
        dest.writeString(parent_id);
        dest.writeString(contact_id);
        dest.writeString(priority);
        dest.writeString(id_c);
        dest.writeString(aviso_c);
        dest.writeString(trabajo_estimado_c);
        dest.writeString(ejecuted_date_c);
        dest.writeString(assigned_user_name);
        dest.writeString(contact_name);
        dest.writeString(parent_name);
    }

	@Override
	public Map<String, String> getDataBean() {
		// TODO Auto-generated method stub
		return null;
	}
}
