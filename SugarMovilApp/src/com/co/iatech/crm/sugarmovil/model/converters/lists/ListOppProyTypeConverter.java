package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.HashMap;


public class ListOppProyTypeConverter extends ListConverter{

	public ListOppProyTypeConverter (){
		dataMap = new HashMap<String,String>();
		dataMap.put("Proyecto1", "Proyecto de Ingenieria");
		dataMap.put("Proyecto2", "Proyecto de Componentes");
		dataMap.put("Proyecto3", "Proyecto de Especificacion");	
	}


	
}
