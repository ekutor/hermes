package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.HashMap;

import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;


public class ListOppCurrencyConverter extends ListConverter{

	public ListOppCurrencyConverter (){
		dataMap = new HashMap<String,String>();
		dataMap.put("0", "USD Dolares");
		dataMap.put("1", "COP Pesos");
	}
	
	@Override
	public String convert(String value, DataToGet dataType) {
		if(value == null ){
			return "COP Pesos";
		}
		return super.convert(value, dataType);
	}

	
}
