package com.co.iatech.crm.sugarmovil.model;

import com.co.iatech.crm.sugarmovil.model.converters.*;

public abstract class GenericBean {
	protected enum ConverterType {PRIORITY,STATUS};
	public String validate(String data){
		data =  validateNull(data);
		return this.validateDefaultValue(data);
	}
	
	protected String validateNull(String data){
		String d = "";
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
		}
		
		return converter.Transform(language, value);
	}
	
	
}
