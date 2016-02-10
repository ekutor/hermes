package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.HashMap;



public class ListChannelConverter extends ListConverter{
	
	private static ListChannelConverter instance;
	
	private ListChannelConverter (){
		dataMap = new HashMap<String,String>();
		dataMap.put("01", "ALMACEN");
		dataMap.put("02", "TABLERISTA");
		dataMap.put("03", "BOMBERO");
		dataMap.put("04", "ENSAMBLADOR");
		dataMap.put("05", "FIRMA DE INGENIERIA");
		dataMap.put("06", "USUARIO DIRECTO");
		dataMap.put("07", "FERRETERO");
		dataMap.put("08", "INTEGRADOR AUTOMATIZACION");
		dataMap.put("09", "PLANTERO");
		dataMap.put("10", "CONSTRUCTORA");
		dataMap.put("11", "INTEGRADOR COMUNICACIO");
		dataMap.put("12", "EMPRESA DE TELECOMUNICACION");
		dataMap.put("13", "INTEGRADOR DOMOTICO");
		dataMap.put("14", "DISEÑADOR DE ESPACIOS");
		dataMap.put("15", "EMPRESAS DE SEGURIDAD");
		dataMap.put("16", "UNION TEMPORAL");
		dataMap.put("17", "CONSORCIO");
		dataMap.put("18", "USUARIO FINAL");
		dataMap.put("19", "PROVEEDOR");
		dataMap.put("99", "EMPLEADO");
	}


	public static ListChannelConverter getInstance(){
		if(instance == null){
			instance = new ListChannelConverter();
		}
		
		return instance;
	}
}
