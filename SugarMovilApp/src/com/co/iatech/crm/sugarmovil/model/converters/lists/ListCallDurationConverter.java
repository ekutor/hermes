package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListCallDurationConverter extends ListConverter{
	
	
	private static ListCallDurationConverter instance;
	
	private ListCallDurationConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("00", "00");
		dataMap.put("15", "15");
		dataMap.put("30", "30");
		dataMap.put("45", "45");
	}
	
	public static ListCallDurationConverter getInstance(){
		if(instance == null){
			instance = new ListCallDurationConverter();
		}
		
		return instance;
	}
	
	 
	
}
