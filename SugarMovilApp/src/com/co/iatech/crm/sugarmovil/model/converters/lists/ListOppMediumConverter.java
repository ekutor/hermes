package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListOppMediumConverter extends ListConverter{
	
	private static ListOppMediumConverter instance;
	
	private ListOppMediumConverter (){
		dataMap = new LinkedHashMap<String,String>();
		
		dataMap.put(defaultListValue, defaultListValue);
		dataMap.put("001", "CAPACITACIONES-EVENTOS");
		dataMap.put("002", "FERIAS");
		dataMap.put("003", "PAUTAS PUBLICITARIAS");

		dataMap.put("006", "E-MAIL MARKETING");
		dataMap.put("007", "ESPECIFICACION");
		dataMap.put("008", "PORTALES WEB");
		dataMap.put("009", "NO APLICA");
		dataMap.put("010", "OTRO");
		dataMap.put("011", "REMITIDO POR PROVEEDOR");
	}

	
	public static ListOppMediumConverter getInstance(){
		if(instance == null){
			instance = new ListOppMediumConverter();
		}	
		return instance;
	}
	
}
