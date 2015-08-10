package com.co.iatech.crm.sugarmovil.model;

public abstract class GenericBean {
	
	public String validate(String data){
		return validateNull(data);
	}
	
	protected String validateNull(String data){
		String d = "";
		if(!"null".equalsIgnoreCase(data)){
			d = data;
		}
		return d;
	}
}
