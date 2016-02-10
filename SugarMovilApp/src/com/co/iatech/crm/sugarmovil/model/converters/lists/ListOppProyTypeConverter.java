package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListOppProyTypeConverter extends ListConverter{

	private static ListOppProyTypeConverter instance;
	private ListOppProyTypeConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListValue, defaultListValue);
		dataMap.put("Proyecto1", "Proyecto de Ingenieria");
		dataMap.put("Proyecto2", "Proyecto de Componentes");
		dataMap.put("Proyecto3", "Proyecto de Especificacion");	
	}
	
	public static ListOppProyTypeConverter getInstance(){
		if(instance == null){
			instance = new ListOppProyTypeConverter();
		}	
		return instance;
	}

	
}
