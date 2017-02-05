package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListOppIluminationConverter extends ListConverter{

	
	private static ListOppIluminationConverter instance;
	
	private ListOppIluminationConverter (){
		dataMap = new LinkedHashMap<String,String>();
		
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("VCP", "VCP ECOLIGHTING");
		dataMap.put("EATON_LIGHTING", "EATON LIGHTING");
		
	}
	
	public static ListOppIluminationConverter getInstance(){
		if(instance == null){
			instance = new ListOppIluminationConverter();
		}	
		return instance;
	}
}
