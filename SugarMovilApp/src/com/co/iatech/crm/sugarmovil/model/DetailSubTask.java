package com.co.iatech.crm.sugarmovil.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.activities.listeners.DataVisitor;
import com.co.iatech.crm.sugarmovil.activities.listeners.Visitable;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de una nota.
 */
public class DetailSubTask extends GenericBean implements Parcelable , Visitable{


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DetailSubTask> CREATOR = new Parcelable.Creator<DetailSubTask>() {
        @Override
        public DetailSubTask createFromParcel(Parcel in) {
            return new DetailSubTask(in);
        }

        @Override
        public DetailSubTask[] newArray(int size) {
            return new DetailSubTask[size];
        }
    };
   
    private String name;
    private String date_entered;
    private String date_modified;
    private String modified_user_id;
    private String created_by;
    private String file_mime_type;
    private String filename;
    private String parent_type;
    private String parent_id;
    private String contact_id;
    private String portal_flag;
    private String embed_flag;
    private String description;
    private String id_c;
    private String estado_c;
    private String fechainicio_c;
    private String fechafin_c;
    private String ejecuted_date_c;
    private String assigned_user_id;
    private String deleted;
    private String assigned_user_name;
    private String contact_name;
    private String parent_name;
    
    public DetailSubTask(){
    	
    }
    
    public DetailSubTask(String id,String name){
    	this.id = id;
    	this.name = name;
    }
    
    public DetailSubTask(JSONObject obj) throws JSONException {
		setId(validate(obj.getString("id")));
	    setName(validate(obj.getString("name")));
	    setDate_entered(validate(obj.getString("date_entered")));
	    setDate_modified(validate(obj.getString("date_modified")));
	    setModified_user_id(validate(obj.getString("modified_user_id")));	    
	    setCreated_by(validate(obj.getString("created_by")));
	    setFile_mime_type(validate(obj.getString("file_mime_type")));
	    setFilename(validate(obj.getString("filename")));
	    setParent_type(validate(obj.getString("parent_type")));
	    setParent_id(validate(obj.getString("parent_id")));
	    setContact_id(validate(obj.getString("contact_id")));
	    setPortal_flag(validate(obj.getString("portal_flag")));
	    setEmbed_flag(validate(obj.getString("embed_flag")));
	    setDescription(validate(obj.getString("description")));
	    setId_c(validate(obj.getString("id_c")));
	    setEstado_c(validate(obj.getString("estado_c")));
	    setFechainicio_c(validate(obj.getString("fechainicio_c")));
	    setFechafin_c(validate(obj.getString("fechafin_c")));
	    setEjecuted_date_c(validate(obj.getString("ejecuted_date_c")));
	    setAssigned_user_id(validate(obj.getString("assigned_user_id")));
	    setDeleted(validate(obj.getString("deleted")));
	    setAssigned_user_name(validate(obj.getString("assigned_user_name")));;
	    setContact_name(validate(obj.getString("contact_name")));;
	    setParent_name(validate(obj.getString("parent_name")));;
	    
    }

    protected DetailSubTask(Parcel in) {
        id = in.readString();
        name = in.readString();
        date_entered = in.readString();
        date_modified = in.readString();
        modified_user_id = in.readString();
        created_by = in.readString();
        file_mime_type = in.readString();
        filename = in.readString();
        parent_type = in.readString();
        parent_id = in.readString();
        contact_id = in.readString();
        portal_flag = in.readString();
        embed_flag = in.readString();
        description = in.readString();
        id_c = in.readString();
        estado_c = in.readString();
        fechainicio_c = in.readString();
        fechafin_c = in.readString();
        ejecuted_date_c = in.readString();
        assigned_user_id = in.readString();
        deleted = in.readString();     
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
    
    public String getId_c() {
        return id_c;
    }

    public void setId_c(String id_c) {
        this.id_c = id_c;
    }

    public String getEjecuted_date_c() {
        return ejecuted_date_c;
    }

    public void setEjecuted_date_c(String ejecuted_date_c) {
        this.ejecuted_date_c = ejecuted_date_c;
    }

    public String getFile_mime_type() {
		return file_mime_type;
	}

	public void setFile_mime_type(String file_mime_type) {
		this.file_mime_type = file_mime_type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPortal_flag() {
		return portal_flag;
	}

	public void setPortal_flag(String portal_flag) {
		this.portal_flag = portal_flag;
	}

	public String getEmbed_flag() {
		return embed_flag;
	}

	public void setEmbed_flag(String embed_flag) {
		this.embed_flag = embed_flag;
	}

	public String getEstado_c() {
		return estado_c;
	}

	public void setEstado_c(String estado_c) {
		this.estado_c = estado_c;
	}

	public String getFechainicio_c() {
		return fechainicio_c;
	}

	public void setFechainicio_c(String fechainicio_c) {
		this.fechainicio_c = fechainicio_c;
	}

	public String getFechafin_c() {
		return fechafin_c;
	}

	public void setFechafin_c(String fechafin_c) {
		this.fechafin_c = fechafin_c;
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
        dest.writeString(file_mime_type);
        dest.writeString(filename);
        dest.writeString(parent_type);
        dest.writeString(parent_id);
        dest.writeString(contact_id);
        dest.writeString(portal_flag);
        dest.writeString(embed_flag);
        dest.writeString(description);
        dest.writeString(id_c);
        dest.writeString(estado_c);
        dest.writeString(fechainicio_c);
        dest.writeString(fechafin_c);
        dest.writeString(ejecuted_date_c);
        dest.writeString(assigned_user_id);
        dest.writeString(deleted); 
        dest.writeString(assigned_user_name);
        dest.writeString(contact_name);
        dest.writeString(parent_name);
    }

	@Override
	public Map<String, String> getDataBean() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("id",id);
		data.put("name",name);
		data.put("date_entered",date_entered);
		data.put("date_modified",date_modified);
		data.put("modified_user_id",modified_user_id);
		data.put("created_by",created_by);
		data.put("file_mime_type",file_mime_type);
		data.put("filename",filename);
		data.put("parent_type",parent_type);
		data.put("parent_id",parent_id);
		data.put("contact_id",contact_id);
		data.put("portal_flag",portal_flag);
		data.put("embed_flag",embed_flag);
		data.put("description",Utils.hideTabs(description));
		data.put("id_c",id_c);
		data.put("estado_c",estado_c);
		data.put("fechainicio_c",fechainicio_c);
		data.put("fechafin_c",fechafin_c);
		data.put("ejecuted_date_c",ejecuted_date_c);
		data.put("assigned_user_id",assigned_user_id);
		data.put("deleted",deleted);
		data.put("assigned_user_name",assigned_user_name);
		data.put("contact_name",contact_name);
		data.put("parent_name",parent_name);
		return data;
	}

	@Override
	public void accept(DataVisitor visitor) {
		visitor.add(this);
	}
}
