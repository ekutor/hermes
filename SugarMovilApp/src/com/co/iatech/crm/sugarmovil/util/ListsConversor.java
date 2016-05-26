package com.co.iatech.crm.sugarmovil.util;

import java.util.List;

import com.co.iatech.crm.sugarmovil.model.converters.lists.*;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;


public class ListsConversor {
    public enum ConversorsType { ZONE,DPTO,CHANNEL,OPPORTUNITY_MEDIUM, OPPORTUNITY_PROYECT,OPPORTUNITY_SOURCE,
    	OPPORTUNITY_STAGE,OPPORTUNITY_COMUNICATIONS, OPPORTUNITY_ENERGY,OPPORTUNITY_ILUM,
    	OPPORTUNITY_CURRENCY,CALLS_STATUS,CALLS_DIRECTION,CALLS_MINS_DURATION,CALLS_RESULT,
    	TASKS_STATUS, TASKS_TYPE, TASKS_PRIORITY }
    
    private static ListConverter create(ConversorsType type){
    	ListConverter converter = null;
    	switch(type){
			case  DPTO: 
				converter = ListDptoConverter.getInstance();
				break;
			case  CHANNEL: 
				converter = ListChannelConverter.getInstance();
				break;
			case  ZONE: 
				converter = ListZoneConverter.getInstance();
				break;
			case  OPPORTUNITY_MEDIUM: 
				converter = ListOppMediumConverter.getInstance();
				break;
			case  OPPORTUNITY_SOURCE: 
				converter = ListOppSourceConverter.getInstance();
				break;
			case  OPPORTUNITY_PROYECT: 
				converter = ListOppProyTypeConverter.getInstance();
				break;
			case  OPPORTUNITY_STAGE: 
				converter = ListOppProyStageConverter.getInstance();
				break;
			case OPPORTUNITY_ENERGY:
				converter = ListOppEnergyConverter.getInstance();
			break;
			case OPPORTUNITY_COMUNICATIONS:
				converter = ListOppComunicationsConverter.getInstance();
			break;
			case OPPORTUNITY_ILUM:
				converter = ListOppIluminationConverter.getInstance();
			break;
			case OPPORTUNITY_CURRENCY:
				converter = ListOppCurrencyConverter.getInstance();
			break;
			case CALLS_STATUS:
				converter = ListCallStatusConverter.getInstance();
			break;
			case CALLS_DIRECTION:
				converter = ListCallDirectionConverter.getInstance();
			break;
			case CALLS_MINS_DURATION:
				converter = ListCallDurationConverter.getInstance();
			break;
			case CALLS_RESULT:
				converter = ListCallResultConverter.getInstance();
			break;
			case TASKS_STATUS:
				converter = ListTasksStatusConverter.getInstance();
			break;
			case TASKS_TYPE:
				converter = ListTasksRelationsConverter.getInstance();
			break;
			case TASKS_PRIORITY:
				converter = ListTasksPriorityConverter.getInstance();
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
		boolean encontrado = false;
		for(String value: list){
			cont++;
			if(value.equals(valueItem)){
				encontrado = true;
				break;
			}
			
		}
		if( !encontrado ){
			cont = 0;
		}
		return cont;
	}
	
	
	public static List<String> getKeysList(ConversorsType type) {
		ListConverter converter = create(type);
		return converter.getList(DataToGet.CODE);
	}

}
