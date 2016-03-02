package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListTasksStatusConverter extends ListConverter{
	
	
	private static ListTasksStatusConverter instance;
	
	private ListTasksStatusConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("Not Started", "No Iniciada");
		dataMap.put("In Progress", "En Progreso");
		dataMap.put("Completed", "Completada");
		dataMap.put("Pending Input", "Pendiente de Informacion");
		dataMap.put("Deferred", "Aplazada");
		dataMap.put("Delayed", "Retrasada");
	}
	
	public static ListTasksStatusConverter getInstance(){
		if(instance == null){
			instance = new ListTasksStatusConverter();
		}
		
		return instance;
	}
	
	 
	
}
