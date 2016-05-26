package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListOppSourceConverter extends ListConverter{
	
	private static ListOppSourceConverter instance;
	
	private ListOppSourceConverter (){
		dataMap = new LinkedHashMap<String,String>();
		
		dataMap.put(defaultListValue, defaultListValue);
		dataMap.put("001", "BUSQUEDA EN GOOGLE");
		dataMap.put("002", "EQUIPOS Y SOLUCIONES IT");
		dataMap.put("003", "GUIA DE SOLUCIONES TIC");

		dataMap.put("006", "LANDING PAGE");
		dataMap.put("007", "PAGINA WEB");
		dataMap.put("008", "PUBLICAR");
	}

	
	public static ListOppSourceConverter getInstance(){
		if(instance == null){
			instance = new ListOppSourceConverter();
		}	
		return instance;
	}
	
}
