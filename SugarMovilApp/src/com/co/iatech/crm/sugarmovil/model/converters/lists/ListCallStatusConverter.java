package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListCallStatusConverter extends ListConverter{
	
	
	private static ListCallStatusConverter instance;
	
	private ListCallStatusConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("Planned", "Planeada");
		dataMap.put("Held", "Realizada");
		dataMap.put("Not Held", "No Realizada");
	}
	
	public static ListCallStatusConverter getInstance(){
		if(instance == null){
			instance = new ListCallStatusConverter();
		}
		
		return instance;
	}
	
	 
	
}
