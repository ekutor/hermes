package com.co.iatech.crm.sugarmovil.model.converters;

import java.util.HashMap;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.model.LanguageType;


public class CallsStatusConverter implements Converter{
	private Map<String,String> dataMap;
	
	
	public CallsStatusConverter (){
		dataMap = new HashMap<String,String>();
		dataMap.put("Planned", "Planeada");
		dataMap.put("Held", "Realizada");
		dataMap.put("Not Held", "No Realizada");
	}
	
	@Override
	public String Transform(LanguageType language, String value) {
		switch(language){
			case SPANISH:
				for (String key : dataMap.keySet()) {
				    if(key.equalsIgnoreCase(value)){
				    	return dataMap.get(key);
				    }
				}
				break;
			case ENGLISH:
				if(dataMap.containsValue(value)){
					for(Map.Entry<String,String> eset : dataMap.entrySet()){
						if(eset.getValue().equalsIgnoreCase(value)){
							return eset.getKey();
						}
					}
					
				}
				break;
			default:
				break;
		}
		return "";
	};
	 
	
}
