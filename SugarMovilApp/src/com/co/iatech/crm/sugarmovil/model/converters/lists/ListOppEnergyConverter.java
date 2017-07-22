package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListOppEnergyConverter extends ListConverter{

	private static ListOppEnergyConverter instance;
	
	private ListOppEnergyConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		dataMap.put("CABUR", "CABUR");
		dataMap.put("CIRPROTEC", "CIRPROTEC");
		dataMap.put("DELTA", "DELTA");	
		dataMap.put("DKC", "DKC");	
		dataMap.put("EATON", "EATON");	
		dataMap.put("ENERLUX", "ENERLUX");	
		dataMap.put("ENSAMBLES", "ENSAMBLES");	
		dataMap.put("GEROS", "GEROS");	
		dataMap.put("GIC", "GIC");
		dataMap.put("GREENLEE", "GREENLEE");	
		dataMap.put("KLAUKE", "KLAUKE");	
		dataMap.put("LEVITON WID", "LEVITON WID");	
		dataMap.put("LOVATO", "LOVATO");	
		dataMap.put("TCI", "TCI");
		dataMap.put("VCP ELECTRIC", "VCP ELECTRIC");	
		dataMap.put("WOHNER", "WOHNER");	
		dataMap.put("ZIGUA", "ZIGUA");
	}
	
	
	public static ListOppEnergyConverter getInstance(){
		if(instance == null){
			instance = new ListOppEnergyConverter();
		}	
		return instance;
	}
}
