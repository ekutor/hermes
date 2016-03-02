package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListCallDirectionConverter extends ListConverter{
	
	
	private static ListCallDirectionConverter instance;
	
	private ListCallDirectionConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("Inbound", "Entrante");
		dataMap.put("Outbound", "Saliente");
	}
	
	public static ListCallDirectionConverter getInstance(){
		if(instance == null){
			instance = new ListCallDirectionConverter();
		}
		
		return instance;
	}
	
	 
	
}
