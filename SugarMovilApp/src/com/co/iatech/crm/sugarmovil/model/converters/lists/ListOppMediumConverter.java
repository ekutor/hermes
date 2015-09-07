package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.HashMap;


public class ListOppMediumConverter extends ListConverter{

	
	public ListOppMediumConverter (){
		dataMap = new HashMap<String,String>();
		dataMap.put("001", "CAPACITACIONES-EVENTOS");
		dataMap.put("002", "FERIAS");
		dataMap.put("003", "PAUTAS PUBLICITARIAS");

		dataMap.put("006", "E-MAIL MARKETING");
		dataMap.put("007", "ESPECIFICACION");
		dataMap.put("008", "PORTALES WEB");
		dataMap.put("009", "NO APLICA");
		dataMap.put("010", "OTRO");
		dataMap.put("011", "REMITIDO POR PROVEEDOR");
	}


	
}
