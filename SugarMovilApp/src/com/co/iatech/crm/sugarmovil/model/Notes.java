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
public class Notes extends GenericBean implements Parcelable , Visitable{


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Notes> CREATOR = new Parcelable.Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };
   
    private String name;
    private String date_entered;
    private String date_modified;
    private String modified_user_id;
    private String created_by;
    private String file_mime_type;
    private String filename;
    private String parent_id;
    private String description;
    private String assigned_user_id;
    private String deleted;
    private String assigned_user_name;
    private String parent_name;
    private String file_ext;
    private String active_date;
    private String exp_date;	
    private String status_id;
    
    public Notes(){
    	
    }
    
    public Notes(String id,String name){
    	this.id = id;
    	this.name = name;
    }
    
    public Notes(JSONObject obj) throws JSONException {
		setId(validate(obj.getString("id")));
	    setName(validate(obj.getString("document_name")));
	    setDate_entered(validate(obj.getString("date_entered")));
	    setDate_modified(validate(obj.getString("date_modified")));
	    setModified_user_id(validate(obj.getString("modified_user_id")));	    
	    setCreated_by(validate(obj.getString("created_by")));
	    setFile_mime_type(validate(obj.getString("file_mime_type")));
	    setFilename(validate(obj.getString("filename")));
	    setParent_id(validate(obj.getString("parent_id")));
	    setDescription(validate(obj.getString("description")));
	    setAssigned_user_id(validate(obj.getString("assigned_user_id")));
	    setDeleted(validate(obj.getString("deleted")));
	    setAssigned_user_name(validate(obj.getString("assigned_user_name")));
	    setParent_name(validate(obj.getString("parent_name")));
	    setFile_ext(validate(obj.getString("file_ext")));
	    setActive_date(validate(obj.getString("active_date")));
	    setExp_date(validate(obj.getString("exp_date")));
	    setStatus_id(validate(obj.getString("status_id")));
	    
    }

    protected Notes(Parcel in) {
        id = in.readString();
        name = in.readString();
        date_entered = in.readString();
        date_modified = in.readString();
        modified_user_id = in.readString();
        created_by = in.readString();
        file_mime_type = in.readString();
        filename = in.readString();
        parent_id = in.readString();
        description = in.readString();
        assigned_user_id = in.readString();
        deleted = in.readString();     
        assigned_user_name = in.readString();
        parent_name = in.readString();
        file_ext = in.readString();
        active_date = in.readString();
        exp_date = in.readString();
        status_id = in.readString();
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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
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

	public String getAssigned_user_name() {
		return assigned_user_name;
	}

	public void setAssigned_user_name(String assigned_user_name) {
		this.assigned_user_name = assigned_user_name;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	
	public String getFile_ext() {
		return file_ext;
	}

	public void setFile_ext(String file_ext) {
		this.file_ext = file_ext;
	}

	public String getActive_date() {
		return active_date;
	}

	public void setActive_date(String active_date) {
		this.active_date = active_date;
	}

	public String getExp_date() {
		return exp_date;
	}

	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
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
        dest.writeString(parent_id);
        dest.writeString(description);
        dest.writeString(assigned_user_id);
        dest.writeString(deleted); 
        dest.writeString(assigned_user_name);
        dest.writeString(parent_name);
        dest.writeString(file_ext);
        dest.writeString(active_date);
        dest.writeString(exp_date);
        dest.writeString(status_id);
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
		data.put("parent_id",parent_id);
		data.put("description",Utils.hideTabs(description));
		data.put("assigned_user_id",assigned_user_id);
		data.put("deleted",deleted);
		data.put("assigned_user_name",assigned_user_name);
		data.put("parent_name",parent_name);
		data.put("file_ext",file_ext);
		data.put("active_date",active_date);
		data.put("exp_date",exp_date);
		data.put("status_id",status_id);
		return data;
	}

	@Override
	public void accept(DataVisitor visitor) {
		visitor.add(this);
	}
}
