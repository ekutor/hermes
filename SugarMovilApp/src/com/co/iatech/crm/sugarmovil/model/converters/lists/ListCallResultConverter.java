package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListCallResultConverter extends ListConverter{
	
	
	private static ListCallResultConverter instance;
	
	private ListCallResultConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);

		dataMap.put("CONTACTADO E INTERESADO" , "CONTACTADO E INTERESADO");
		dataMap.put("CONTACTADO NO  INTERESADO" , "CONTACTADO NO  INTERESADO");
		dataMap.put("REALIZA PEDIDO" , "REALIZA PEDIDO");
		dataMap.put("NO SE LOGRO CONTACTO" , "NO SE LOGRO CONTACTO");
		dataMap.put("VOLVER A LLAMAR" , "VOLVER A LLAMAR");
		dataMap.put("SE BRINDO SOPORTE" , "SE BRINDO SOPORTE");
		dataMap.put("SE HIZO ESPECIFICACION" , "SE HIZO ESPECIFICACION");
		dataMap.put("ASISTENCIAEVENTO" , "ASISTENCIA EVENTO");
		dataMap.put("NOCONFIRMAASISTENCIAALEVENTO" , "NO CONFIRMA ASISTENCIA AL EVENTO");
		dataMap.put("ACTUALIZACIONDEDATOS" , "ACTUALIZACION DE DATOS");
	}
	
	public static ListCallResultConverter getInstance(){
		if(instance == null){
			instance = new ListCallResultConverter();
		}
		
		return instance;
	}
	
	 
	
}
