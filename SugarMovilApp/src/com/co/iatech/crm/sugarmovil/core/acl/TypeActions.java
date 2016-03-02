package com.co.iatech.crm.sugarmovil.core.acl;

public enum TypeActions {
	OWNER,ALL,GROUP;

	public static TypeActions getType(String access_type) {
		for(TypeActions t:TypeActions.values()){
			if(t.name().equals(access_type)){
				return t;
			}
		}
		return OWNER;
	}
}
