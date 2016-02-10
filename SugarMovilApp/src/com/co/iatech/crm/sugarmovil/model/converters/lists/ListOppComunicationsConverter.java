package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListOppComunicationsConverter extends ListConverter{
	
	private static ListOppComunicationsConverter instance;
	
	private ListOppComunicationsConverter (){
		dataMap = new LinkedHashMap<String,String>();
		
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("CIRPROTEC", "CIRPROTEC");
		dataMap.put("GREENLEE", "GREENLEE");
		dataMap.put("KLAUKE", "KLAUKE");	
		dataMap.put("LEVELONE", "LEVELONE");	
		dataMap.put("LEVITON", "LEVITON");	
		dataMap.put("SUPERIOR ESSEX", "SUPERIOR ESSEX");	
		dataMap.put("VCP CONNECT +", "VCP CONNECT +");	
		
	}
	
	public static ListOppComunicationsConverter getInstance(){
		if(instance == null){
			instance = new ListOppComunicationsConverter();
		}	
		return instance;
	}
}
