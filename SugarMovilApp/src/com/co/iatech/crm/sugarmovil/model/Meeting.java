package com.co.iatech.crm.sugarmovil.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.activities.listeners.DataVisitor;
import com.co.iatech.crm.sugarmovil.activities.listeners.Visitable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las reuniones.
 */
public class Meeting extends GenericBean implements Parcelable  	{

    public static final Creator<Meeting> CREATOR
            = new Creator<Meeting>() {
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    private String name, dateStart, dateEnd;
    private String location;
    private String durationHours;
    private String durationMinutes;
    private String status;
    private String parentType;
    private String parentId;
    private String type;
    private String reminderTime;
    private String userId;
    private String description;
    private String objetivos;
    private String compromisos;
    private String tipo_c;
    //private String created_by;
    
    
    
    public Meeting() {}
    
    public Meeting(JSONObject obj) throws JSONException {
        setId(validate(obj.getString("id")));
        setName(validate(obj.getString("name")));
        setDateStart(validate(obj.getString("date_start")));
        setDateEnd(validate(obj.getString("date_end")));
        setLocation(validate(obj.getString("location")));
        setDurationHours(validate(obj.getString("duration_hours")));
        setDurationMinutes(validate(obj.getString("duration_minutes")));
        setStatus(validate(obj.getString("status")));
        setParentType(validate(obj.getString("parent_type")));
        setParentId(validate(obj.getString("parent_id")));
        setType(validate(obj.getString("type")));
        setReminderTime(validate(obj.getString("reminder_time")));
        setUserId(validate(obj.getString("user_id")));
        setDescription(validate(obj.getString("description")));
        setTipo_c(validate(obj.getString("tipo_c")));
        setCompromisos(validate(obj.getString("compromiso_c")));
        setObjetivos(validate(obj.getString("objetivos_c")));
        
    }

    public Meeting(Parcel in) {
        setId(in.readString());
        setName(in.readString());
        setDateStart(in.readString());
        setDateEnd(in.readString());
        setLocation(in.readString());
        setDurationHours(in.readString());
        setDurationMinutes(in.readString());
        setStatus(in.readString());
        setParentType(in.readString());
        setParentId(in.readString());
        setType(in.readString());
        setReminderTime(in.readString());
        setUserId(in.readString());
        setDescription(in.readString());
        setTipo_c(in.readString());
        setCompromisos(in.readString());
        setObjetivos(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getId());
        dest.writeString(getName());
        dest.writeString(getDateStart());
        dest.writeString(getDateEnd());
        dest.writeString(getLocation());
        dest.writeString(getDurationHours());
        dest.writeString(getDurationMinutes());
        dest.writeString(getStatus());
        dest.writeString(getParentType());
        dest.writeString(getParentId());
        dest.writeString(getType());
        dest.writeString(getReminderTime());
        dest.writeString(getUserId());
        dest.writeString(getDescription());
        dest.writeString(getTipo_c());
        dest.writeString(getCompromisos());
        dest.writeString(getObjetivos());
       
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
    	
	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(String durationHours) {
		this.durationHours = durationHours;
	}

	public String getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(String durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public String getCompromisos() {
		return compromisos;
	}

	public void setCompromisos(String compromisos) {
		this.compromisos = compromisos;
	}

	public String getTipo_c() {
		return tipo_c;
	}

	public void setTipo_c(String tipo_c) {
		if("".equals(tipo_c)){
			tipo_c= "INTERNA";
		}
		this.tipo_c = tipo_c;
	}

	@Override
	public Map<String, String> getDataBean() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("id",id);
		data.put("name",name);
		data.put("date_start",dateStart);
		data.put("date_end",dateEnd);
		data.put("location",location);
		data.put("description",description);
		data.put("commitment",compromisos);
		data.put("objectives",objetivos);
		data.put("type",tipo_c);
		data.put("status",status);
		data.put("duration_hours",durationHours);
		data.put("duration_minutes",durationMinutes);
		
		data.put("parent_type",parentType);
		data.put("parent_id",parentId);
		
		data.put("assigned_user_id",userId);
		data.put("created_by",userId);

		data.put("reminder_time",reminderTime);
	
		return data;
	}

}
