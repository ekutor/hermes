package com.co.iatech.crm.sugarmovil.core.acl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.util.Log;

public class ActionsInfo {
	private Map<Actions, TypeActions> info;
	public ActionsInfo(){
		info = new HashMap<Actions, TypeActions>();
	}
	
	public void addAction(Actions action, TypeActions type ){
		if(action != null && type != null){
			Log.d("ActionsInfo", "add action "+action.name()+ " "+type.name() );
			info.put(action, type);
		}
	}
	public boolean hasAction(Actions a){
		Log.d("AccesControl", "contains a "+a.name() + " "+info.containsKey(a) );
		return info.containsKey(a);
	}
	public TypeActions getType(Actions a){
		return info.get(a);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ActionsInfo [info=");
		Iterator<Entry<Actions, TypeActions>> it = info.entrySet().iterator();
		while(it.hasNext()){
			Entry<Actions, TypeActions> en = it.next();
			Actions a = en.getKey();
			TypeActions t = en.getValue();
			sb.append("\t");sb.append(a.name()); sb.append("={");
			sb.append("\t");sb.append(t.name()); sb.append("{");
		}
		return sb.toString();
	}
	
	
}
