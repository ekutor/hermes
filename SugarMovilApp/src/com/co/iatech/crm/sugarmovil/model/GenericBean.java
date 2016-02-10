package com.co.iatech.crm.sugarmovil.model;

import java.util.Map;


public abstract class GenericBean {
	protected static final String specialSugarChar = "^";
	protected enum ConverterType {PRIORITY,STATUS,CALL_STATUS};
	public String validate(String data){
		data =  validateNull(data);
		return this.validateDefaultValue(data);
	}
	
	protected String deleteSpecialChar(String value){
		if(value != null && value.contains(specialSugarChar)){
			value = value.replace(specialSugarChar, "");
        }
		return value;
	}
	
	protected String addSpecialChar(String value){
		if(value != null && !value.equals("") && !value.equalsIgnoreCase("null")){
			value = specialSugarChar+value+specialSugarChar;
		}
		return value;
	}
	
	protected String validateNull(String data){
		String d = "";
		if(data != null && !"null".equalsIgnoreCase(data)){
			data = data.trim();
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

	
	
	public abstract Map<String,String> getDataBean();
	
}
