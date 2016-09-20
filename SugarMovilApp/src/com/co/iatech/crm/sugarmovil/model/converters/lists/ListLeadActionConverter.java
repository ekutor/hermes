package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListLeadActionConverter extends ListConverter{

	private static ListLeadActionConverter instance;
	
	private ListLeadActionConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		
		dataMap.put("ASESORIA", "ASESORIA");
		dataMap.put("COTIZACION", "COTIZACION");
	}
	
	
	public static ListLeadActionConverter getInstance(){
		if(instance == null){
			instance = new ListLeadActionConverter();
		}	
		return instance;
	}
}
