package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.HashMap;


public class ListOppComunicationsConverter extends ListConverter{

	public ListOppComunicationsConverter (){
		dataMap = new HashMap<String,String>();
		dataMap.put("CIRPROTEC", "CIRPROTEC");
		dataMap.put("GREENLEE", "GREENLEE");
		dataMap.put("KLAUKE", "KLAUKE");	
		dataMap.put("LEVELONE", "LEVELONE");	
		dataMap.put("LEVITON", "LEVITON");	
		dataMap.put("SUPERIOR ESSEX", "SUPERIOR ESSEX");	
		dataMap.put("VCP CONNECT +", "VCP CONNECT +");	
		
	}
}
