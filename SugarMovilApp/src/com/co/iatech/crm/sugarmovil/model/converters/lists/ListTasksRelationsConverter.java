package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListTasksRelationsConverter extends ListConverter{
	
	
	private static ListTasksRelationsConverter instance;
	
	private ListTasksRelationsConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put("Cases", "Bitacora");
		dataMap.put("Leads", "Cliente Potencial");
		dataMap.put("Contacts", "Contacto");
		dataMap.put("Accounts", "Cuenta");
		dataMap.put("psg_Eventos", "Evento");
		dataMap.put("Bugs", "Incidencia");
		dataMap.put("Opportunities", "Oportunidad de Negocio");
		dataMap.put("Project", "Proyecto");
		dataMap.put("Prospects", "Publico Objetivo");
		dataMap.put("Tasks", "Tarea");
		dataMap.put("ProjectTask", "Tarea de Proyecto");
	}
	
	public static ListTasksRelationsConverter getInstance(){
		if(instance == null){
			instance = new ListTasksRelationsConverter();
		}
		
		return instance;
	}
	
	 
	
}
