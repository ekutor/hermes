package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListTasksPriorityConverter extends ListConverter{
	
	
	private static ListTasksPriorityConverter instance;
	
	private ListTasksPriorityConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("High", "Alta");
		dataMap.put("Medium", "Media");
		dataMap.put("Low", "Baja");
	}
	
	public static ListTasksPriorityConverter getInstance(){
		if(instance == null){
			instance = new ListTasksPriorityConverter();
		}
		
		return instance;
	}
	
	 
	
}
