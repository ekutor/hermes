package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListMeetStatusConverter extends ListConverter{
	
	
	private static ListMeetStatusConverter instance;
	
	private ListMeetStatusConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("Planned", "Planificada");
		dataMap.put("Held", "Realizada");
		dataMap.put("Not Held", "No Realizada");
		dataMap.put("accept", "Aceptada");

	}
	
	public static ListMeetStatusConverter getInstance(){
		if(instance == null){
			instance = new ListMeetStatusConverter();
		}
		
		return instance;
	}
	
	 
	
}
