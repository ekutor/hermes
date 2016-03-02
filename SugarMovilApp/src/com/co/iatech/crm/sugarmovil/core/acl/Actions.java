package com.co.iatech.crm.sugarmovil.core.acl;

public enum Actions {
	view,list,edit,delete,access;
	
	public static Actions getAction(String type) {
		for(Actions t:Actions.values()){
			if(t.name().equals(type)){
				return t;
			}
		}
		return null;
	}
}
