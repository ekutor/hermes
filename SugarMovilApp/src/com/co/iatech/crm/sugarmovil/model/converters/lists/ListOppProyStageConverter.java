package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListOppProyStageConverter extends ListConverter{

	private static ListOppProyStageConverter instance;
	
	private ListOppProyStageConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("ESPECIFICACION", "ESPECIFICACION");
		dataMap.put("OFERTADO", "OFERTADO");
		dataMap.put("PENDIENTE POR OFERTA", "PENDIENTE POR OFERTA");	
		dataMap.put("EN ESPERA DE DECISION", "EN ESPERA DE DECISION");	
		dataMap.put("ADJUDICADO", "ADJUDICADO");	
		dataMap.put("PERDIDO", "PERDIDO");	
		dataMap.put("APLAZADO", "APLAZADO");	
		dataMap.put("CANCELADO", "CANCELADO");	
		//dataMap.put("GANADO POR CANAL", "GANADO POR CANAL");	
	}
	
	
	public static ListOppProyStageConverter getInstance(){
		if(instance == null){
			instance = new ListOppProyStageConverter();
		}	
		return instance;
	}

	
}
