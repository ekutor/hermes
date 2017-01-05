package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListMeetsConverter extends ListConverter{
	
	
	private static ListMeetsConverter instance;
	
	private ListMeetsConverter (){
		dataMap = new LinkedHashMap<String,String>();
		
		dataMap.put("CLIENTE ACTUAL", "CLIENTE ACTUAL");
		dataMap.put("CLIENTE POTENCIAL", "CLIENTE POTENCIAL");
		dataMap.put("ESPECIFICACIÓN", "ESPECIFICACIÓN");
		dataMap.put("TÉCNICA", "TÉCNICA");
		dataMap.put("CAPACITACIÓN", "CAPACITACIÓN");
		dataMap.put("INTERNA", "INTERNA");
		dataMap.put("PERSONAL", "PERSONAL");
		dataMap.put("VIAJE", "VIAJE");

	}
	
	public static ListMeetsConverter getInstance(){
		if(instance == null){
			instance = new ListMeetsConverter();
		}
		
		return instance;
	}
	
	 
	
}
