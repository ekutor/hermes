package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.HashMap;



public class ListZoneConverter extends ListConverter{

	private static ListZoneConverter instance;
	
	private ListZoneConverter (){
		dataMap = new HashMap<String,String>();
		dataMap.put("1", "ANTIOQUIA");
		dataMap.put("2", "BOGOTA CENTRO");
		dataMap.put("3", "CALI OCCIDENTE");
		dataMap.put("4", "COSTA NORTE");
		dataMap.put("5", "SANTANDER");
		dataMap.put("6", "EJE CAFETERO");
	}
	
	public static ListZoneConverter getInstance(){
		if(instance == null){
			instance = new ListZoneConverter();
		}
		
		return instance;
	}

	
}
