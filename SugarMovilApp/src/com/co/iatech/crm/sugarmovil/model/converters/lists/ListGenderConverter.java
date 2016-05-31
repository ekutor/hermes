package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListGenderConverter extends ListConverter{
	
	
	private static ListGenderConverter instance;
	
	private ListGenderConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("M", "Masculino");
		dataMap.put("F", "Femenino");
	}
	
	public static ListGenderConverter getInstance(){
		if(instance == null){
			instance = new ListGenderConverter();
		}
		
		return instance;
	}
	
	 
	
}
