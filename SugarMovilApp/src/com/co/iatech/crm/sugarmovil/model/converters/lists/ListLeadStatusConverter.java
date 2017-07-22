package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListLeadStatusConverter extends ListConverter{

	private static ListLeadStatusConverter instance;
	
	private ListLeadStatusConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		
		dataMap.put("NUEVO" , "NUEVO");
		dataMap.put("ASIGNADO" , "ASIGNADO");
		dataMap.put("CONVERTIDO_EN_CLIENTE_Y_VENTA" , "CONVERTIDO EN CLIENTE Y VENTA");
		dataMap.put("REFERIDO_A_CANAL" , "REFERIDO A CANAL");
		dataMap.put("COMPRO_EN_EL_CANAL" , "COMPRO EN CANAL");
		dataMap.put("PERDIDO" , "PERDIDO EN CANAL");
		dataMap.put("CONVERTIDO_EN_USUARIO_FINAL" , "CONVERTIDO EN USUARIO FINAL");
		dataMap.put("GENERA_ACCION_SGC" , "GENERA ACCIÓN SGC");
		dataMap.put("SE_REDIRECCIONA_A_LOGISTICA" , "SE REDIRECCIONA A LOGISTICA");
		dataMap.put("CONTACTO_DEL_EXTERIOR" , "CONTACTO DEL EXTERIOR");
		dataMap.put("CLIENTE_ACTIVO" , "CLIENTE ACTIVO");
		dataMap.put("USUARIO_FINAL_SIN_POTENCIAL" , "USUARIO FINAL SIN POTENCIAL. ");
		dataMap.put("SELECCIONE" , "SELECCIONE");
	}
	
	
	public static ListLeadStatusConverter getInstance(){
		if(instance == null){
			instance = new ListLeadStatusConverter();
		}	
		return instance;
	}
}
