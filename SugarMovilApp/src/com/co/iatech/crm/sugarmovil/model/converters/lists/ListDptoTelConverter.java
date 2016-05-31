package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.HashMap;


public class ListDptoTelConverter extends ListConverter{
	
	private static ListDptoTelConverter instance;
	
	public static ListDptoTelConverter getInstance(){
		if(instance == null){
			instance = new ListDptoTelConverter();
		}
		
		return instance;
	}
	
	private ListDptoTelConverter (){
		dataMap = new HashMap<String,String>();
		dataMap.put("91","038");
		dataMap.put("05", "034");
		dataMap.put("81", "037");
		dataMap.put("08", "035");
		dataMap.put("13", "035");
		dataMap.put("15", "038");
		dataMap.put("17", "036");
		dataMap.put("18", "038");
		dataMap.put("85", "038");
		dataMap.put("19", "032");
		dataMap.put("20", "035");
		dataMap.put("27", "034");
		dataMap.put("23", "034");
		dataMap.put("25", "031");
		dataMap.put("11", "031");
		dataMap.put("94", "038");
		dataMap.put("44", "035");
		dataMap.put("95", "038");
		dataMap.put("41", "038");
		dataMap.put("47", "035");
		dataMap.put("50", "038");
		dataMap.put("52", "032");
		dataMap.put("54", "037");
		dataMap.put("86", "038");
		dataMap.put("63", "036");
		dataMap.put("66", "036");
		dataMap.put("88", "038");
		dataMap.put("68", "037");
		dataMap.put("70", "035");
		dataMap.put("73", "038");
		dataMap.put("76", "032");
		dataMap.put("97","038");
		dataMap.put("99", "038");
		dataMap.put("0", "034");
	}
}
