package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;

import com.co.iatech.crm.sugarmovil.model.converters.*;

public abstract class GenericBean {
	protected enum ConverterType {PRIORITY,STATUS,CALL_STATUS};
	public String validate(String data){
		data =  validateNull(data);
		return this.validateDefaultValue(data);
	}
	
	protected String validateNull(String data){
		String d = "";
		data = data.trim();
		if(!"null".equalsIgnoreCase(data)){
			d = data;
		}
		return d;
	}
	
	protected String validateDefaultValue(String data){
		String d = "";
		if(!"SELECCIONE".equalsIgnoreCase(data)  && !"SELECCIONAR".equalsIgnoreCase(data) ){
			d = data;
		}
		return d;
	}
	
	public String convert(LanguageType language, String value, ConverterType type){
		Converter converter = null;
		switch(type){
			case  PRIORITY: 
				converter = new PriorityConverter();
				break;
			case  STATUS: 
				converter = new StatusConverter();
				break;
			case  CALL_STATUS: 
				converter = new CallsStatusConverter();
				break;
		}
		
		return converter.Transform(language, value);
	}
	
	
	public abstract Map<String,String> getDataBean();
	
}
