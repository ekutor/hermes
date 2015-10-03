package com.co.iatech.crm.sugarmovil.util;

import java.util.List;

import com.co.iatech.crm.sugarmovil.model.converters.lists.*;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;


public class ListsConversor {
    public enum ConversorsType { ZONE,DPTO,CHANNEL,OPPORTUNITY_MEDIUM, OPPORTUNITY_PROYECT,
    	OPPORTUNITY_STAGE,OPPORTUNITY_COMUNICATIONS, OPPORTUNITY_ENERGY,OPPORTUNITY_ILUM,
    	OPPORTUNITY_CURRENCY}
    
    private static ListConverter create(ConversorsType type){
    	ListConverter converter = null;
    	switch(type){
			case  DPTO: 
				converter = new ListDptoConverter();
				break;
			case  CHANNEL: 
				converter = new ListChannelConverter();
				break;
			case  ZONE: 
				converter = new ListZoneConverter();
				break;
			case  OPPORTUNITY_MEDIUM: 
				converter = new ListOppMediumConverter();
				break;
			case  OPPORTUNITY_PROYECT: 
				converter = new ListOppProyTypeConverter();
				break;
			case  OPPORTUNITY_STAGE: 
				converter = new ListOppProyStageConverter();
				break;
			case OPPORTUNITY_ENERGY:
				converter = new ListOppEnergyConverter();
			break;
			case OPPORTUNITY_COMUNICATIONS:
				converter = new ListOppComunicationsConverter();
			break;
			case OPPORTUNITY_ILUM:
				converter = new ListOppIluminationConverter();
			break;
			case OPPORTUNITY_CURRENCY:
				converter = new ListOppCurrencyConverter();
			break;
			default: 
				break;
		}
    	return converter;
    	
    }
	
	public static String convert(ConversorsType type, String value, DataToGet convType){
		ListConverter converter = create(type);
		return converter.convert(value, convType);
	}
	
	public static List<String> getValuesList(ConversorsType type) {
		ListConverter converter = create(type);
		return converter.getList(DataToGet.VALUE);
	}
	
	public static int getPosItemOnList(ConversorsType type, String idItem) {
		ListConverter converter = create(type);
		List<String> list = converter.getList(DataToGet.VALUE);
		String valueItem = converter.convert(idItem, DataToGet.VALUE);
		int cont = -1;
		for(String value: list){
			cont++;
			if(value.equals(valueItem)){
				break;
			}
			
		}
		return cont;
	}
	
	
	public static List<String> getKeysList(ConversorsType type) {
		ListConverter converter = create(type);
		return converter.getList(DataToGet.CODE);
	}

}
