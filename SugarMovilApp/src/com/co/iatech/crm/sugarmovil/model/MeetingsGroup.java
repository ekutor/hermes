package com.co.iatech.crm.sugarmovil.model;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Representa un objeto parcelable para el manejo de las reuniones.
 */
public class MeetingsGroup extends GenericBean implements Parcelable  	{

    public static final Creator<MeetingsGroup> CREATOR
            = new Creator<MeetingsGroup>() {
        public MeetingsGroup createFromParcel(Parcel in) {
            return new MeetingsGroup(in);
        }

        public MeetingsGroup[] newArray(int size) {
            return new MeetingsGroup[size];
        }
    };

    private String date;
    private String qty;
    private String month;

    
    
    
    public MeetingsGroup() {}
    
    public MeetingsGroup(JSONObject obj) throws JSONException {
        setQty(validate(obj.getString("qty")));
        setDate(validate(obj.getString("date")));
        setMonth(validate(obj.getString("month")));
    }

    public MeetingsGroup(Parcel in) {
        setQty(in.readString());
        setDate(in.readString());
        setMonth(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getQty());
        dest.writeString(getDate());
        dest.writeString(getMonth());
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public Map<String, String> getDataBean() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("qty",qty);
		data.put("date",date);
		data.put("month",month);
	
		return data;
	}

	@Override
	public String getName() {
		return date;
	}

	@Override
	public void setName(String name) {
		
	}

}
