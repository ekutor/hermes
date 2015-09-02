package com.co.iatech.crm.sugarmovil.model.converters;

import com.co.iatech.crm.sugarmovil.model.LanguageType;

public interface Converter {
	public String Transform(LanguageType language,String value);
	
}
