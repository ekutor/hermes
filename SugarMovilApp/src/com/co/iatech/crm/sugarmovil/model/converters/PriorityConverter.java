package com.co.iatech.crm.sugarmovil.model.converters;

import java.util.HashMap;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.model.LanguageType;


public class PriorityConverter implements Converter{
	private Map<String,String> dataMap;
	
	
	public PriorityConverter (){
		dataMap = new HashMap<String,String>();
		dataMap.put("High", "Alta");
		dataMap.put("Medium", "Media");
		dataMap.put("Low", "Baja");
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
