package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListLeadBrandConverter extends ListConverter{

	private static ListLeadBrandConverter instance;
	
	private ListLeadBrandConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put(defaultListKey, defaultListValue);
		
		dataMap.put("0022", "CABUR");
		dataMap.put("0004", "CIRPROTEC");
		dataMap.put("0002", "CROMPTON GREAVES");
		dataMap.put("0006", "DELTA");
		dataMap.put("0007", "DKC");
		dataMap.put("0008", "EATON");
		dataMap.put("0025", "EATON LIGHTING");
		dataMap.put("0010", "ENERLUX");
		dataMap.put("0011", "GEROS");
		dataMap.put("0012", "GREENLEE");
		dataMap.put("0013", "KLAUKE");
		dataMap.put("0014", "LEVELONE");
		dataMap.put("0019", "LEVITON NETWORK SOLUTIONS");
		dataMap.put("0017", "LEVITON WIRING DEVICES");
		dataMap.put("0020", "LOVATO");
		dataMap.put("0023", "PORTAFOLIO LAUMAYER");
		dataMap.put("0021", "SUPERIOR ESSEX");
		dataMap.put("0005", "VCP CONNECT +");
		dataMap.put("0024", "VCP ECOLIGHTING ");
		dataMap.put("0001", "VCP ELECTRIC");
		dataMap.put("0018", "WOHNER");
	}
	
	
	public static ListLeadBrandConverter getInstance(){
		if(instance == null){
			instance = new ListLeadBrandConverter();
		}	
		return instance;
	}
}
