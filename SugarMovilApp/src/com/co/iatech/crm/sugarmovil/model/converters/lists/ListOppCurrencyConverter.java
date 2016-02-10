package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.LinkedHashMap;


public class ListOppCurrencyConverter extends ListConverter{
	
	private static ListOppCurrencyConverter instance;
	private ListOppCurrencyConverter (){
		dataMap = new LinkedHashMap<String,String>();
		dataMap.put("0", "COP Pesos");
		dataMap.put("1", "USD Dolares");
	}
	
	@Override
	public String convert(String value, DataToGet dataType) {
		if(value == null ){
			return "COP Pesos";
		}
		return super.convert(value, dataType);
	}
	
	public static ListOppCurrencyConverter getInstance(){
		if(instance == null){
			instance = new ListOppCurrencyConverter();
		}
		
		return instance;
	}
	
}
