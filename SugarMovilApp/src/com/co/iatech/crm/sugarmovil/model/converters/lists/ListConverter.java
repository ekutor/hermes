package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.model.LanguageType;
import com.co.iatech.crm.sugarmovil.model.converters.Converter;


public abstract class ListConverter implements Converter{
	protected Map<String,String> dataMap;
	protected final String defaultListValue = "SELECCIONAR";
	protected final String defaultListKey = "NULL";
	
	public enum DataToGet { VALUE, CODE, POS }
	
	
	public ListConverter (){
	}
	
	public String convert(String value, DataToGet dataType) {
		switch(dataType){
			case VALUE:
				for (String key : dataMap.keySet()) {
				    if(key.equalsIgnoreCase(value)){
				    	return dataMap.get(key);
				    }
				}
				break;
			case CODE:
				if(dataMap.containsValue(value)){
					for(Map.Entry<String,String> eset : dataMap.entrySet()){
						if(eset.getValue().equalsIgnoreCase(value)){
							return eset.getKey();
						}
					}
					
				}
				break;
			case POS:
				
				break;
			default:
				break;
		}
		return "";
	}


	public List<String> getList(DataToGet type) {
		List<String> list1 = null;
		switch(type){
			case CODE:
				list1 =  new ArrayList<String>(dataMap.keySet());
				break;
			case VALUE:
				list1 =  new ArrayList<String>(dataMap.values());
				break;
			default:
				break;
		}
		return list1;
	}
	
	@Override
	public String Transform(LanguageType language, String value) {
		return "";
	}
	 
	
}
