package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListOppSourceConverter extends ListConverter{
	
	private static ListOppSourceConverter instance;
	
	private ListOppSourceConverter (){
		dataMap = new LinkedHashMap<String,String>();
		
		dataMap.put(defaultListValue, defaultListValue);
		dataMap.put("BUSQUEDA EN GOOGLE", "BUSQUEDA EN GOOGLE");
		dataMap.put("EQUIPOS Y SOLUCIONES IT", "EQUIPOS Y SOLUCIONES IT");
		dataMap.put("GUIA DE SOLUCIONES TIC", "GUIA DE SOLUCIONES TIC");

		dataMap.put("LANDING PAGE", "LANDING PAGE");
		dataMap.put("PAGINA WEB", "PAGINA WEB");
		dataMap.put("PUBLICAR", "PUBLICAR");
	}

	
	public static ListOppSourceConverter getInstance(){
		if(instance == null){
			instance = new ListOppSourceConverter();
		}	
		return instance;
	}
	
}
