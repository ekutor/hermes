package com.co.iatech.crm.sugarmovil.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

import com.co.iatech.crm.sugarmovil.activities.listeners.DataVisitor;
import com.co.iatech.crm.sugarmovil.activities.listeners.Visitable;

/**
 * Representa un objeto parcelable para el manejo de las llamadas.
 */
public class Call extends GenericBean implements Parcelable, Visitable {

    public static final Creator<Call> CREATOR
            = new Creator<Call>() {
        public Call createFromParcel(Parcel in) {
            return new Call(in);
        }

        public Call[] newArray(int size) {
            return new Call[size];
        }
    };

    private String name;
    private String date_entered;
    private String date_modified;
    private String modified_user_id;
    private String created_by;
    private String description;
    private String deleted;
    private String assigned_user_id;
    private String duration_hours;
    private String duration_minutes;
    private String date_start;
    private String date_end;
    private String parent_type;
    private String status;
    private String direction;
    private String parent_id;
    private String reminder_time;
    private String email_reminder_time;
    private String email_reminder_sent;
    private String outlook_id;
    private String repeat_type;
    private String repeat_interval;
    private String repeat_dow;
    private String repeat_until;
    private String repeat_count;
    private String repeat_parent_id;
    private String recurring_source;
    private String id_c;
    private String resultadodelallamada_c;
    private String created_by_name;
    private String assigned_user_name;
    private String parent_name;
    private String campaign_name;
    private String campaign_id;

    public Call(JSONObject obj) throws JSONException {
    	setId(validate(obj.getString("id")));
    	setName(validate(obj.getString("name")));
    	setDate_entered(validate(obj.getString("date_entered")));
    	setDate_modified(validate(obj.getString("date_modified")));
    	setModified_user_id(validate(obj.getString("modified_user_id")));
    	setCreated_by(validate(obj.getString("created_by")));
    	setDescription(validate(obj.getString("description")));
    	setDeleted(validate(obj.getString("deleted")));
    	setAssigned_user_id(validate(obj.getString("assigned_user_id")));
    	setDuration_hours(validate(obj.getString("duration_hours")));
    	setDuration_minutes(validate(obj.getString("duration_minutes")));
    	setDate_start(validate(obj.getString("date_start")));
    	setDate_end(validate(obj.getString("date_end")));
    	setParent_type(validate(obj.getString("parent_type")));
    	setStatus(validate(obj.getString("status")));
    	setDirection(validate(obj.getString("direction")));
    	setParent_id(validate(obj.getString("parent_id")));
    	setReminder_time(validate(obj.getString("reminder_time")));
    	setEmail_reminder_time(validate(obj.getString("email_reminder_time")));
    	setEmail_reminder_sent(validate(obj.getString("email_reminder_sent")));
    	setOutlook_id(validate(obj.getString("outlook_id")));
    	setRepeat_type(validate(obj.getString("repeat_type")));
    	setRepeat_interval(validate(obj.getString("repeat_interval")));
    	setRepeat_dow(validate(obj.getString("repeat_dow")));
    	setRepeat_until(validate(obj.getString("repeat_until")));
    	setRepeat_count(validate(obj.getString("repeat_count")));
    	setRepeat_parent_id(validate(obj.getString("repeat_parent_id")));
    	setRecurring_source(validate(obj.getString("recurring_source")));
    	setId_c(validate(obj.getString("id_c")));
    	setResultadodelallamada_c(validate(obj.getString("resultadodelallamada_c")));
    	setCreated_by_name(validate(obj.getString("created_by_name")));
    	setAssigned_user_name(validate(obj.getString("assigned_user_name")));
    	setParent_name(validate(obj.getString("parent_name")));
    	setCampaign_name(validate(obj.getString("campaign_name")));
    	setCampaign_id(validate(obj.getString("campaign_id")));
    }

    public Call(Parcel in) {
    	setId(validate(in.readString()));
    	setName(validate(in.readString()));
    	setDate_entered(validate(in.readString()));
    	setDate_modified(validate(in.readString()));
    	setModified_user_id(validate(in.readString()));
    	setCreated_by(validate(in.readString()));
    	setDescription(validate(in.readString()));
    	setDeleted(validate(in.readString()));
    	setAssigned_user_id(validate(in.readString()));
    	setDuration_hours(validate(in.readString()));
    	setDuration_minutes(validate(in.readString()));
    	setDate_start(validate(in.readString()));
    	setDate_end(validate(in.readString()));
    	setParent_type(validate(in.readString()));
    	setStatus(validate(in.readString()));
    	setDirection(validate(in.readString()));
    	setParent_id(validate(in.readString()));
    	setReminder_time(validate(in.readString()));
    	setEmail_reminder_time(validate(in.readString()));
    	setEmail_reminder_sent(validate(in.readString()));
    	setOutlook_id(validate(in.readString()));
    	setRepeat_type(validate(in.readString()));
    	setRepeat_interval(validate(in.readString()));
    	setRepeat_dow(validate(in.readString()));
    	setRepeat_until(validate(in.readString()));
    	setRepeat_count(validate(in.readString()));
    	setRepeat_parent_id(validate(in.readString()));
    	setRecurring_source(validate(in.readString()));
    	setId_c(validate(in.readString()));
    	setResultadodelallamada_c(validate(in.readString()));
    	setCreated_by_name(validate(in.readString()));
    	setAssigned_user_name(validate(in.readString()));
    	setParent_name(validate(in.readString()));
    	setCampaign_name(validate(in.readString()));
    	setCampaign_id(validate(in.readString()));
    }

    public Call() {
	}

	@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getId());
        dest.writeString(getName());
        dest.writeString(getDate_entered());
        dest.writeString(getDate_modified());
        dest.writeString(getModified_user_id());
        dest.writeString(getCreated_by());
        dest.writeString(getDescription());
        dest.writeString(getDeleted());
        dest.writeString(getAssigned_user_id());
        dest.writeString(getDuration_hours());
        dest.writeString(getDuration_minutes());
        dest.writeString(getDate_start());
        dest.writeString(getDate_end());
        dest.writeString(getParent_type());
        dest.writeString(getStatus());
        dest.writeString(getDirection());
        dest.writeString(getParent_id());
        dest.writeString(getReminder_time());
        dest.writeString(getEmail_reminder_time());
        dest.writeString(getEmail_reminder_sent());
        dest.writeString(getOutlook_id());
        dest.writeString(getRepeat_type());
        dest.writeString(getRepeat_interval());
        dest.writeString(getRepeat_dow());
        dest.writeString(getRepeat_until());
        dest.writeString(getRepeat_count());
        dest.writeString(getRepeat_parent_id());
        dest.writeString(getRecurring_source());
        dest.writeString(getId_c());
        dest.writeString(getResultadodelallamada_c());
        dest.writeString(getCreated_by_name());
        dest.writeString(getAssigned_user_name());
        dest.writeString(getParent_name());
        dest.writeString(getCampaign_name());
        dest.writeString(getCampaign_id());
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

    public String getDuration_hours() {
        return duration_hours;
    }

    public void setDuration_hours(String duration_hours) {
        this.duration_hours = validateNull(duration_hours);
    }

    public String getDuration_minutes() {
        return duration_minutes;
    }

    public void setDuration_minutes(String duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getParent_type() {
        return parent_type;
    }

    public void setParent_type(String parent_type) {
        this.parent_type = parent_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getReminder_time() {
        return reminder_time;
    }

    public void setReminder_time(String reminder_time) {
        this.reminder_time = reminder_time;
    }

    public String getEmail_reminder_time() {
        return email_reminder_time;
    }

    public void setEmail_reminder_time(String email_reminder_time) {
        this.email_reminder_time = email_reminder_time;
    }

    public String getEmail_reminder_sent() {
        return email_reminder_sent;
    }

    public void setEmail_reminder_sent(String email_reminder_sent) {
        this.email_reminder_sent = email_reminder_sent;
    }

    public String getOutlook_id() {
        return outlook_id;
    }

    public void setOutlook_id(String outlook_id) {
        this.outlook_id = outlook_id;
    }

    public String getRepeat_type() {
        return repeat_type;
    }

    public void setRepeat_type(String repeat_type) {
        this.repeat_type = repeat_type;
    }

    public String getRepeat_interval() {
        return repeat_interval;
    }

    public void setRepeat_interval(String repeat_interval) {
        this.repeat_interval = repeat_interval;
    }

    public String getRepeat_dow() {
        return repeat_dow;
    }

    public void setRepeat_dow(String repeat_dow) {
        this.repeat_dow = repeat_dow;
    }

    public String getRepeat_until() {
        return repeat_until;
    }

    public void setRepeat_until(String repeat_until) {
        this.repeat_until = repeat_until;
    }

    public String getRepeat_count() {
        return repeat_count;
    }

    public void setRepeat_count(String repeat_count) {
        this.repeat_count = repeat_count;
    }

    public String getRepeat_parent_id() {
        return repeat_parent_id;
    }

    public void setRepeat_parent_id(String repeat_parent_id) {
        this.repeat_parent_id = repeat_parent_id;
    }

    public String getRecurring_source() {
        return recurring_source;
    }

    public void setRecurring_source(String recurring_source) {
        this.recurring_source = recurring_source;
    }

    public String getId_c() {
        return id_c;
    }

    public void setId_c(String id_c) {
        this.id_c = id_c;
    }

    public String getResultadodelallamada_c() {
        return resultadodelallamada_c;
    }

    public void setResultadodelallamada_c(String resultadodelallamada_c) {
        this.resultadodelallamada_c = resultadodelallamada_c;
    }

    public String getCreated_by_name() {
        return created_by_name;
    }

    public void setCreated_by_name(String created_by_name) {
        this.created_by_name = created_by_name;
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

    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

	@Override
	public void accept(DataVisitor visitor) {
		visitor.add(this);
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
		data.put("description",description);
		data.put("deleted",deleted);
		data.put("assigned_user_id",assigned_user_id);
		data.put("duration_hours",duration_hours);
		data.put("duration_minutes",duration_minutes);
		data.put("date_start",date_start);
		data.put("date_end",date_end);
		data.put("parent_type",parent_type);
		data.put("status",status);
		data.put("direction",direction);
		data.put("parent_id",parent_id);
		data.put("reminder_time",reminder_time);
		data.put("email_reminder_time",email_reminder_time);
		data.put("email_reminder_sent",email_reminder_sent);
		data.put("outlook_id",outlook_id);
		data.put("repeat_type",repeat_type);
		data.put("repeat_interval",repeat_interval);
		data.put("repeat_dow",repeat_dow);
		data.put("repeat_until",repeat_until);
		data.put("repeat_count",repeat_count);
		data.put("repeat_parent_id",repeat_parent_id);
		data.put("recurring_source",recurring_source);
		data.put("id_c",id_c);
		data.put("resultadodelallamada_c",resultadodelallamada_c);
		data.put("created_by_name",created_by_name);
		data.put("assigned_user_name",assigned_user_name);
		data.put("parent_name",parent_name);
		data.put("campaign_name",campaign_name);
		data.put("campaign_id",campaign_id);
		return data;
	}
}
